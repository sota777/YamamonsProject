package jp.KEN.yamamons.controller;

public class RegistrationController {

	/*@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String toRegistration(Model model) {
		model.addAttribute("membersModel", new MembersModel());
		model.addAttribute("headline", "会員登録");
		return "membersRegistration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registMembers(Model model,@Validated(GroupOrder.class)@ModelAttribute MembersModel membersModel,
			BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("headline", "会員登録");
			return "membersRegistration";
		}

		Members members = new Members();
		members.setName(membersModel.getName());
		members.setEmail(membersModel.getEmail());
		members.setPhoneNumber(membersModel.getPhoneNumber());
		members.setBirthday(Members.parseData(membersModel.getBirthday()));

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
*/

}
