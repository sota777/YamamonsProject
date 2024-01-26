package jp.KEN.yamamons.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.KEN.yamamons.Group.Group1;
import jp.KEN.yamamons.entity.Members;
import jp.KEN.yamamons.model.MembersModel;

public class RegistrationController {

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String toRegistration(Model model) {
		model.addAttribute("membersModel", new MembersModel());
		model.addAttribute("headline", "会員登録");
		return "membersRegistration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registMembers(Model model,@Validated(Group1.class)@ModelAttribute MembersModel membersModel,
			BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("headline", "会員登録");
			return "membersRegistration";
		}

		Members members = new Members();
		members.setName(membersModel.getName());
		members.setEmail(membersModel.getEmail());
		members.setPhoneNumber(membersModel.getPhoneNumber());
		members.setAddres(membersModel.getAddres());
		members.setCredit(membersModel.getCredit());
		members.setPassword(membersModel.getPassword());


		int numberOfRow = membersDao.insert(members);
		if (numberOfRow == 0){
			model.addAttribute("message", "登録に失敗しました。");
			model.addAttribute("headline", "会員登録");
			return "membersRegistration";
		}

		return "redirect:/complete";

	}

	@RequestMapping(value = "/complete", method = RequestMethod.GET)
	public String toComplete(Model model) {
		model.addAttribute("headline", "会員登録完了");
		return "membersRegistrationComplete";
	}


}
