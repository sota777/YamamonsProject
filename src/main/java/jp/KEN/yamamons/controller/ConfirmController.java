/*package jp.KEN.yamamons.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes({ "loginModel", "cModel" })
public class ConfirmController {

	@Autowired
		private Dao dao;	//Item系のDaoクラス名で設定

	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String toConfirm(@ModelAttribute CartModel cModel, Model model) {
		return "rental_cart4";

	}

}*/
