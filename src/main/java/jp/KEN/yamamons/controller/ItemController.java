package jp.KEN.yamamons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

//import jp.ken.session.model.CartModel;

@Controller
@SessionAttributes({ "loginModel", "cModel" })
public class ItemController {
	/*
	public CartModel setupCart() {
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
		return "seafoodList";
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
		return "seafoodList";
	}
	*/

}
