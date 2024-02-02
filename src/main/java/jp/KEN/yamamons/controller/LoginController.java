package jp.KEN.yamamons.controller;

import javax.servlet.http.HttpSession;

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

	//ログイン情報をセッションに登録
	@ModelAttribute("loginModel")
	public LoginModel setupLoginForm() {
		return new LoginModel();
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin() {
		return "login2";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String toRegist(@Validated @ModelAttribute LoginModel loginModel, BindingResult result, Model model,HttpSession session) {
		String cusMail = loginModel.getLoginMail();
		Members loginCusData = membersDao.getCusDataByMail(cusMail);

		//入力したMailアドレスがDBと一致しない場合
		if (loginCusData == null) {
			model.addAttribute("errorMessage", "ログインIDもしくはパスワードが間違っています。");
			return "login2";
		}

		//エラーチェック
		if (result.hasErrors()) {
			return "login2";


			//顧客が入力したMailとパスワードがデータベースと一致するか確認するメソッド
			//一致すれば商品選択ページに飛ぶ
		} else if (loginModel.getLoginMail().equals("kanri@kenschool.com")
				&& loginModel.getPassword().equals("admin")) {
			return "redirect:/admin";
		} else if (loginModel.getLoginMail().equals(loginCusData.getMail())
				&& loginModel.getPassword().equals(loginCusData.getPassword())) {
			session.setAttribute("CusData", loginCusData);
			return "redirect:/form";
		} else {
			model.addAttribute("errorMessage", "ログインIDもしくはパスワードが間違っています。");
			return "login2";
		}

	}

}
