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
import org.springframework.web.bind.support.SessionStatus;

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

	@ModelAttribute("loginModel")
	public LoginModel setupLoginForm() {
		return new LoginModel();
	}

	//rental_form3.jspで「カートを確認」を押したとき
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String toConfirm(@ModelAttribute("cModel") CartModel cModel, LoginModel loginModel, Model model) {
		ArrayList<String> cart = null;
		ArrayList<Items> cartItems = new ArrayList<Items>();
		String message = null;
		String loginError = null;
		String dupMessage = null;

		//ログインされていない場合はエラーメッセージを出す
		if (loginModel.getLoginMail() == null) {
			loginError = "顧客情報取得エラーです。再度ログインしてください。";
			model.addAttribute("loginError", loginError);
			return "rental_cart4";
		}
		//cModelに要素が入っていた場合、ArrayListのcartに配列を代入する
		if (cModel != null) {
			cart = cModel.getCart();
		}

		//cartの要素(ItemNo)を一つずつ取り出し、商品情報をItemsDaoからとってくる
		//取ってきた商品情報をcartItemsに入れていく
		if (cart != null && !cart.isEmpty()) {
			cartItems = toGetCartItems(cart);
			message = "カートに" + cart.size() + "個の商品が入っています";

			//顧客情報を取り出し、顧客IDからその人のレンタル履歴を取り出す
			String cusMail = loginModel.getLoginMail();
			Members loginCusData = membersDao.getCusDataByMail(cusMail);
			if (loginCusData == null) {
				loginError = "顧客情報取得エラーです。再度ログインしてください。";
				model.addAttribute("loginError", loginError);
				return "rental_cart4";
			}

			Items item = null;
			for (int i = 0; i < cart.size(); i++) {
				Order orderHistory = rentalHistoryDao.getHistoryByCustomerId(loginCusData.getCustomerId(), cart.get(i));
				//カートに入れた商品がレンタル履歴と一致する場合、メッセージで前借りたよーと教える
				if (orderHistory != null) {
					item = itemsDao.getItemsByNo(Integer.parseInt(orderHistory.getItemNo()));
					if (dupMessage == null) {
						dupMessage = item.getItemName();
					} else {
						dupMessage += "," + item.getItemName();
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
		model.addAttribute("alertMessage", dupMessage);
		model.addAttribute("cartItems", cartItems);
		model.addAttribute(dModel);
		return "rental_cart4";
	}

	//rental_cart4.jspで「削除」を押したとき
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public String toDelete(@ModelAttribute("cModel") CartModel cModel, DeleteModel dModel, LoginModel loginModel,
			Model model) {
		String paramIndex = null;
		ArrayList<String> cart = null;
		if (cModel != null) {
			cart = cModel.getCart();
			paramIndex = dModel.getIndex();
			int delItem = Integer.parseInt(cart.get(Integer.parseInt(paramIndex) - 1));
			cart.remove(Integer.parseInt(paramIndex) - 1);
			itemsDao.deleteFromItem2(delItem);
		}
		return "redirect:/confirm";
	}

	//rental_cart4.jspで「RENTAL」を押したとき
	@RequestMapping(value = "/orderComplete", method = RequestMethod.GET)
	public String toOrderComplete(@ModelAttribute("cModel") CartModel cModel,
			@ModelAttribute("loginModel") LoginModel loginModel, SessionStatus status,Model model) {
		ArrayList<String> cart = null;
		String errormessage = null;
		List<Items> stockShortage = null;
		List<Items> cartItems = null;

		//cModelに要素が入っていた場合、ArrayListのcartに配列を代入する
		if(cModel.getCart() == null) {
			//nullの時は確認画面に戻る
			errormessage ="商品を選択してください。";
			model.addAttribute("errormessage", errormessage);
			return "rental_cart4";
		}else if (!cModel.getCart().isEmpty()){
			cart = cModel.getCart();
		}else {
			System.out.println("cModelがempty");
			errormessage ="商品を選択してください。";
			model.addAttribute("errormessage", errormessage);
			return "rental_cart4";
		}

		//カートに入れた商品の在庫が1つ未満のものがあれば取得する
		stockShortage = itemsDao.stockCheck();
		if (!stockShortage.isEmpty()) {
			for (int i = 0; i < stockShortage.size(); i++) {
				String str = stockShortage.get(i).getItemName();
				if (errormessage == null) {
					errormessage = str;
				} else {
					errormessage += "," + str;
				}
			}
			errormessage += "の在庫が足りない為貸出が出来ません";
			model.addAttribute("errormessage", errormessage);
			cartItems = toGetCartItems(cart);
			model.addAttribute("cartItems", cartItems);
			return "rental_cart4";
		}

		//在庫がある場合、商品の在庫数を1つ減らす
		for (int i = 0; i < cart.size(); i++) {
			int order = itemsDao.reduceItemQuantity(cart.get(i));
			if (order == 0) {
				errormessage = "貸出に失敗しました";
				return "rental_cart4";
			}
		}


		List<Order> orderList = new ArrayList<Order>();

		for (String roop : cart) {
			Order order = new Order();
			order.setItemNo(roop);
			Members members = membersDao.getCusDataByMail(loginModel.getLoginMail());
			String customerId = members.getCustomerId();
			order.setCustomerId(customerId);
			order.setOrderQuantity("1");
			order.setRentalStatusNo("1");
			orderList.add(order);
		}

		rentalHistoryDao.addOrder(orderList);

		itemsDao.deleteItem2();

		status.setComplete();

		return "comRental5";
	}

	//在庫が足りませんという表示が出た画面で、「削除」を押したとき
	@RequestMapping(value = "/orderComplete", method = RequestMethod.POST)
	public String toRedirectOrder(@ModelAttribute("cModel") CartModel cModel, Model model, DeleteModel dModel) {

		String paramIndex = null;
		ArrayList<String> cart = null;
		if (cModel != null) {
			cart = cModel.getCart();
			paramIndex = dModel.getIndex();
			int delItem = Integer.parseInt(cart.get(Integer.parseInt(paramIndex) - 1));
			cart.remove(Integer.parseInt(paramIndex) - 1);
			itemsDao.deleteFromItem2(delItem);
		}
		return "redirect:/confirm";
	}

	//カートに入っている商品情報一覧を取り出すためのメソッド
	public ArrayList<Items> toGetCartItems(ArrayList<String> cart) {
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
