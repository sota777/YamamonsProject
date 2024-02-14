package jp.KEN.yamamons.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

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

	//login2.jspで「Login」を押したとき
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String toForm(@ModelAttribute CartModel cModel, Model model) {
		ArrayList<String> cart = null;
		String message;
		itemsDao.deleteItem2();

		//cModelに商品番号が入っている場合は、ArrayListのcartに
		//カートに入れた商品の番号を配列で入れていく。
		if (cModel != null) {
			cart = cModel.getCart();
		}

		//カートに入っている商品の数を表示するmessage作成
		if (cart != null && !cart.isEmpty()) {
			message = "カートに" + cart.size() + "個の商品が入っています";
		} else {
			message = "商品を選んでください";
		}
		model.addAttribute("message", message);

		//データベースの内容をList型で取得し、JSPで表示できるようaddAttribute
		List<Items> itemsList = itemsDao.getItemsList();
		model.addAttribute("itemsList", itemsList);
		return "rental_form3";

	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String toAddCart(@ModelAttribute("cModel") CartModel cModel, Model model) {
		String message;
		String cartInNo = cModel.getItemNo();
		cModel.addCart(cartInNo);

		//カートに入っている商品の数を表示するmessage作成
		if (cModel.getCart().isEmpty()) {
			message="商品を選んでください";
		}else {
			message="カートに"+cModel.getCart().size()+"個の商品が入っています";
		}
		model.addAttribute("message",message);


		//カートに入れた商品をt_item2に追加する

		Integer itemNo = new Integer(Integer.parseInt(cartInNo));
		Items items = itemsDao.getItemsByNo(itemNo);
		itemsDao.insertItem2(items);

		//データベースの内容をList型で取得し、JSPで表示できるようaddAttribute
		List<Items> itemsList = itemsDao.getItemsExceptCart(cModel.getCart());
		model.addAttribute("itemsList", itemsList);


		return "rental_form3";

	}

	@RequestMapping(value = "/clear", method = RequestMethod.GET)
	public String toClearCart(@ModelAttribute("cModel")CartModel cModel, Model model, LoginModel loginModel, SessionStatus status,HttpSession httpSession ){
		//カートのsession情報を破棄する
		httpSession.removeAttribute("cModel");
		System.out.println(loginModel.getCustomerName());
		System.out.println(cModel.getItemNo());
		//status.setComplete();
		 // loginModelは保持したままで良いので、再度modelに登録
	    //model.addAttribute("loginModel",loginModel);
		return "redirect:/form";
	}

}
