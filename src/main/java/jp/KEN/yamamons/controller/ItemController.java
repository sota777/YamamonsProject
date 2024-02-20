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
import jp.KEN.yamamons.model.CartModel;
import jp.KEN.yamamons.model.LoginModel;

@Controller
@SessionAttributes({ "loginModel", "cModel" })
public class ItemController {

	@Autowired
	private ItemsDao itemsDao;

	//画面遷移時もカート内容を引き継ぐためセッション登録
	@ModelAttribute("cModel")
	public CartModel setupCartModel() {
		return new CartModel();
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String toForm(@ModelAttribute("cModel") CartModel cModel, Model model) {
		ArrayList<String> cart = null;
		String message;

		//cModelに商品番号が入っている場合は、ArrayListのcartに
		//カートに入れた商品の番号を配列で入れていく。
		if (cModel != null) {
			cart = cModel.getCart();
		}

		//カートに入っている商品の数を表示するmessage作成
		if (cart != null && !cart.isEmpty()) {
			message = "カートに" + cart.size() + "個の商品が入っています";
		} else {
			message = "";
		}
		model.addAttribute("message", message);

		List<Items> itemsList = itemsDao.getItemsExceptCart(cart);
		model.addAttribute("itemsList", itemsList);
		return "rental_form3";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String toAddCart(@ModelAttribute("cModel") CartModel cModel, Model model) {
		String message;
		String cartInNo = cModel.getItemNo();
		if (cModel.getCart() == null) {
			ArrayList<String> str1 = new ArrayList<String>();
			str1.add(cartInNo);
			cModel.setCart(str1);
		} else {
			cModel.addCart(cartInNo);
		}

		//カートに入っている商品の数を表示するmessage作成
		if (cModel.getCart().isEmpty()) {
			message = "商品を選んでください";
		} else {
			message = "カートに" + cModel.getCart().size() + "個の商品が入っています";
		}
		model.addAttribute("message", message);

		List<Items> itemsList = itemsDao.getItemsExceptCart(cModel.getCart());
		model.addAttribute("itemsList", itemsList);
		return "rental_form3";

	}

	@RequestMapping(value = "/clear", method = RequestMethod.GET)
	public String toClearCart(@ModelAttribute("cModel") CartModel cModel, Model model, LoginModel loginModel) {

		ArrayList<String> cart = null;
		cModel.setCart(cart);

		return "redirect:/form";
	}

}
