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
import jp.KEN.yamamons.model.DeleteModel;

@Controller
@SessionAttributes({ "loginModel", "cModel" })
public class ConfirmController {

	@Autowired
	private ItemsDao itemsDao; //Item系のDaoクラス名で設定

	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String toConfirm(@ModelAttribute("cModel") CartModel cModel, Model model) {
		ArrayList<String> cart = null;
		ArrayList<Items> cartItems = new ArrayList<Items>();

		//cModelに要素が入っていた場合、ArrayListのcartに配列を代入する
		if (cModel != null) {
			cart = cModel.getCart();
		}

		//cartの要素(ItemNo)を一つずつ取り出し、商品情報をItemsDaoからとってくる
		//取ってきた商品情報をcartItemsに入れていく
		String message = null;
		if (cart != null && !cart.isEmpty()) {
				cartItems = toGetCartItems(cart);
				message = "カートに" + cart.size() + "個の商品が入っています";
		} else {
			message = "カートは空です";
		}
		DeleteModel dModel = new DeleteModel();
		model.addAttribute("message", message);
		model.addAttribute("cartItems", cartItems);
		model.addAttribute(dModel);
		return "rental_cart4";
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public String toDelete(@ModelAttribute("cModel") CartModel cModel, DeleteModel dModel, Model model) {
		String paramIndex = null;
		ArrayList<String> cart = null;
		if (cModel != null) {
			cart = cModel.getCart();

			System.out.println(cart.size());
			paramIndex = dModel.getIndex();
			cart.remove(Integer.parseInt(paramIndex) - 1);
			System.out.println(cart.size());
		}
		return "redirect:/confirm";
	}

	@RequestMapping(value = "/orderComplete", method = RequestMethod.GET)
	public String toOderComplete(@ModelAttribute("cModel") CartModel cModel, Model model) {
		ArrayList<String> cart = null;
		String message = "null";
		ArrayList<Items> cartItems = null;

		//cModelに要素が入っていた場合、ArrayListのcartに配列を代入する
		if (cModel != null) {
			cart = cModel.getCart();
		} else {
			//nullの時は確認画面に戻る
			return "redirect:/confirm";
		}
		for (int i = 0; i < cart.size(); i++) {
			//注文前の在庫数を確認する
			int quantity = itemsDao.toGetItemQuantity(cart.get(i));
			if(quantity < 1) {
				message = "在庫が足りない為貸出が出来ません";
				model.addAttribute("message", message);
				cartItems = toGetCartItems(cart);
				model.addAttribute("cartItems", cartItems);
				return "rental_cart4";
			}
			int order = itemsDao.reduceItemQuantity(cart.get(i));
			if (order == 0) {
				message = "貸出に失敗しました";
				return "rental_cart4";
			}
		}
		return "comRental5";
	}

	//カートに入っている商品情報一覧を取り出すためのメソッド
	public ArrayList<Items> toGetCartItems (ArrayList<String> cart){
		ArrayList<Items> cartItems = new ArrayList<Items>();
		for (int i = 0; i < cart.size(); i++) {
			//ID検索
			Integer itemNo = new Integer(Integer.parseInt(cart.get(i)));
			//商品情報の取り出し
			Items items = itemsDao.getItemsByNo(itemNo);
			cartItems.add(items);
		}
		return cartItems;
	}

}
