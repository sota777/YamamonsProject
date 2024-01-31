package jp.KEN.yamamons.controller;

import java.util.ArrayList;

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

@Controller
@SessionAttributes({ "loginModel", "cModel" })
public class ConfirmController {

	@Autowired
	private ItemsDao itemsDao; //Item系のDaoクラス名で設定

	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String toConfirm(@ModelAttribute CartModel cModel, Model model) {
		ArrayList<String> cart = null;
		ArrayList<Items> cartItems = new ArrayList<Items>();

		if (cModel != null) {
			cart = cModel.getCart();
		}
		String message;
		if (cart != null && !cart.isEmpty()) {
			for (int i = 0; i < cart.size(); i++) {
				//ID検索
				Integer itemNo = new Integer(Integer.parseInt(cart.get(i)));
				Items items = itemsDao.getItemsByNo(itemNo);
				cartItems.add(items);
			}
		} else {
			message = "カートは空です";
		}
		model.addAttribute("message", message);

		/*
		if (members == null) {
			model.addAttribute("message", "該当データはありません");
		} else {
			List<Members> membersList = new ArrayList<Members>();
			membersList.add(members);
			model.addAttribute("membersList", membersList);
		}
		} catch (NumberFormatException e) {
		model.addAttribute("message", "IDが不正です");
		}
		*/

		return "rental_cart4";

	}

}
