package jp.KEN.yamamons.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.KEN.yamamons.Group.GroupOrder;
import jp.KEN.yamamons.dao.MembersDao;
import jp.KEN.yamamons.entity.Members;
import jp.KEN.yamamons.model.MembersModel;

@Controller
public class RegistrationController {
	@Autowired
	private MembersDao membersDao;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String toRegistration(Model model) {
		model.addAttribute("membersModel", new MembersModel());
		model.addAttribute("headline", "会員登録");
		return "registration2";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registMembers(Model model,@Validated(GroupOrder.class)@ModelAttribute MembersModel membersModel,
			BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("エラーだよ");
			model.addAttribute("headline", "会員登録");
			return "registration2";
		}


		Members members = new Members();
		members.setCustomerName(membersModel.getName());
		members.setAddress(membersModel.getAddress());
		members.setTel(membersModel.getPhoneNumber());
		members.setMail(membersModel.getMail());
		members.setCreditNo(membersModel.getCredit());
		members.setPlanNo(membersModel.getPlanNo());
		members.setPassword(membersModel.getPassword());


		int numberOfRow = membersDao.insertCus(members);
		if (numberOfRow == 0){
			model.addAttribute("message", "登録に失敗しました。");
			model.addAttribute("headline", "会員登録");
			return "registration2";
		}

		return "redirect:/form";

	}

	@RequestMapping(value = "/complete", method = RequestMethod.GET)
	public String toComplete(Model model) {
		model.addAttribute("headline", "会員登録完了");
		return "membersRegistrationComplete";
	}


}
