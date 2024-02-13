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
import jp.KEN.yamamons.dao.MembersDao;
import jp.KEN.yamamons.dao.RentalHistoryDao;
import jp.KEN.yamamons.entity.Items;
import jp.KEN.yamamons.entity.Members;
import jp.KEN.yamamons.entity.Order;
import jp.KEN.yamamons.model.CartModel;
import jp.KEN.yamamons.model.DeleteModel;
import jp.KEN.yamamons.model.LoginModel;

@Controller
@SessionAttributes({ "loginModel", "cModel" })
public class ConfirmController {

	@Autowired
	private ItemsDao itemsDao; //Item系のDaoクラス名で設定
	@Autowired
	private RentalHistoryDao rentalHistoryDao;
	@Autowired
	private MembersDao membersDao;

	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String toConfirm(@ModelAttribute("cModel") CartModel cModel, LoginModel loginModel, Model model) {
		ArrayList<String> cart = null;
		ArrayList<Items> cartItems = new ArrayList<Items>();


		//cModelに要素が入っていた場合、ArrayListのcartに配列を代入する
		if (cModel != null) {
			cart = cModel.getCart();
		}

		//cartの要素(ItemNo)を一つずつ取り出し、商品情報をItemsDaoからとってくる
		//取ってきた商品情報をcartItemsに入れていく
		String message = null;
		String dupMessage = null;
		if (cart != null && !cart.isEmpty()) {
			cartItems = toGetCartItems(cart);
			message = "カートに" + cart.size() + "個の商品が入っています";
			System.out.println(message);
			/*
			 * ArrayList<String> rentalHistory = itemsDao.getHistoryByCustomerId(loginModel.getCustomerName());

			if(cart.contains(rentalHistory)) {

			}
			*/
			System.out.println("loginData;"+loginModel.getLoginMail());
			//顧客情報を取り出し、顧客IDからその人のレンタル履歴を取り出す
			String cusMail = loginModel.getLoginMail();
			Members loginCusData = membersDao.getCusDataByMail(cusMail);
			if (loginCusData == null) {
				message = "顧客情報取得エラーです。再度ログインしてください。";
				model.addAttribute("message", message);
				return "rental_cart4";
			}

			System.out.println("loginCusDataのDAO実行");
			System.out.println("loginCusData:"+loginCusData.getCustomerId());
			Items item = null;
			for (int i = 0; i < cart.size(); i++) {
				Order orderHistory = rentalHistoryDao.getHistoryByCustomerId(loginCusData.getCustomerId(),cart.get(i));
				System.out.println("顧客IDから商品履歴のDAO実行");
				//カートに入れた商品がレンタル履歴と一致する場合、メッセージで前借りたよーと教える
				if (orderHistory != null) {
					item = itemsDao.getItemsByNo(Integer.parseInt(orderHistory.getItemNo()));
					if (dupMessage == null) {
						dupMessage = item.getItemName();
					}else {
						dupMessage += ","+ item.getItemName();
					}
				}
			}
			if (dupMessage != null) {
				dupMessage += "は以前レンタルした事があります。";
			}


		} else {
			message = "カートは空です";
		}
		DeleteModel dModel = new DeleteModel();
		model.addAttribute("message", message);
		model.addAttribute("alertMessage",dupMessage);
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
	public String toOrderComplete(@ModelAttribute("cModel") CartModel cModel, Model model) {
		ArrayList<String> cart = null;
		String errormessage = "null";
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
				errormessage = "在庫が足りない為貸出が出来ません";
				model.addAttribute("errormessage", errormessage);
				cartItems = toGetCartItems(cart);
				model.addAttribute("cartItems", cartItems);
				return "rental_cart4";
			}
			int order = itemsDao.reduceItemQuantity(cart.get(i));

			if (order == 0) {
				errormessage = "貸出に失敗しました";
				return "rental_cart4";
			}
		}

		itemsDao.deleteItem2();

		return "comRental5";
	}

	@RequestMapping(value = "/orderComplete", method = RequestMethod.POST)
	public String toRedirectOrder(@ModelAttribute("cModel") CartModel cModel, Model model) {
		return "redirect;/orderComplete";
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
