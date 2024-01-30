package jp.KEN.yamamons.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.KEN.yamamons.dao.MembersDao;
import jp.KEN.yamamons.entity.Members;
import jp.KEN.yamamons.model.LoginModel;

@Controller
@RequestMapping("login")
@SessionAttributes("loginModel")
public class LoginController {

	@Autowired
	private MembersDao membersDao;

	@ModelAttribute("loginModel")
	public LoginModel setupLoginForm() {
		return new LoginModel();
	}

	@RequestMapping(value = "/login",method=RequestMethod.GET)
	public String toLogin() {
		return "login";
	}

	@RequestMapping(method= RequestMethod.POST)
	public String toRegist(@Validated @ModelAttribute LoginModel lModel, BindingResult result, Model model) {
		String cusMail = lModel.getLoginMail();
		Members loginCusData = membersDao.getCusDataByMail(cusMail);
		//エラーチェック
		if (result.hasErrors()) {
			return "login";

		//顧客が入力したMailとパスワードがデータベースと一致するか確認するメソッド
		//一致すれば商品選択ページに飛ぶ
		}else if (lModel.getLoginMail().equals(loginCusData.getMail()) && lModel.getPassword().equals(loginCusData.getPassword())) {
			return "redirect:/form";
		}else {
			model.addAttribute("errorMessage", "ログインIDもしくはパスワードが間違っています。");
			return "login";
		}

	}


}

