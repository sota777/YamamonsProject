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

	//homepage1.jspで「新規登録」を押したとき
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String toRegistration(Model model) {
		model.addAttribute("membersModel", new MembersModel());
		model.addAttribute("headline", "会員登録");
		return "registration2";
	}

	//registration2.jspで「registration」を押したとき
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registMembers(Model model,@Validated(GroupOrder.class)@ModelAttribute MembersModel membersModel,
			BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("headline", "会員登録");
			return "registration2";
		}

		String mail = membersModel.getMail();
		Members check = membersDao.getCusDataByMail(mail);
		String errorMessage = null;
		if (check != null) {
			errorMessage="既に登録されているメールアドレスです";
			model.addAttribute("errorMessage",errorMessage);
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

		return "redirect:/complete";

	}
	//registration2.jspで「registration」を押して問題なしのとき
	@RequestMapping(value = "/complete", method = RequestMethod.GET)
	public String toComplete(Model model) {
		model.addAttribute("headline", "会員登録完了");
		return "comRegistration2.5";
	}


}
