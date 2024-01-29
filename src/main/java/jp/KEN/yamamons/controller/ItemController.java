package jp.KEN.yamamons.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.KEN.yamamons.dao.ItemsDao;
import jp.KEN.yamamons.entity.Items;
import jp.KEN.yamamons.model.LoginModel;

//import jp.ken.session.model.CartModel;

@Controller
@SessionAttributes({ "loginModel", "cModel" })
public class ItemController {


		@Autowired
		private ItemsDao itemsDao;


		//「○○さんログイン中」的な感じでJSP表示するためセッション登録
		@ModelAttribute("loginModel")
		public LoginModel setupLoginModel() {
			return new LoginModel();
		}

		//画面遷移時もカート内容を引き継ぐためセッション登録
		@ModelAttribute("cModel")
		public CartModel setupCartModel() {
			return new CartModel();
		}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String toForm(@ModelAttribute CartModel cModel, Model model) {
		ArrayList<String> cart = null;
		if (cModel != null) {
			cart = cModel.getCart();
		}
		String message;
		if (cart != null && !cart.isEmpty()) {
			message = "カートに" + cart.size() + "個の商品が入っています";
		} else {
			message = "商品を選んでください";
		}
		model.addAttribute("message", message);

		//データベースの内容をList型で取得し、JSPで表示できるようaddAttribute
		List<Items> itemsList = itemsDao.getItemsList();
		model.addAttribute("itemsList", itemsList);
		return "rental_form";

	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String toAddCart(@ModelAttribute("cModel") CartModel cModel, Model model) {

		String message;
		String cartInNo = cModel.getItemNo();
		cModel.addCart(cartInNo);

		System.out.println("cModel"+cModel.getCart());

		if (cModel.getCart().isEmpty()) {
			message="商品を選んでください";
		}else {
			message="カートに"+cModel.getCart().size()+"個の商品が入っています";
		}

		model.addAttribute("message",message);

		List<Items> itemsList = itemsDao.getItemsList();
		model.addAttribute("itemsList", itemsList);
		return "rental_form";

	}

}
