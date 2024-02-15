//COMMIT用

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
import jp.KEN.yamamons.dao.MembersDao;
import jp.KEN.yamamons.dao.RentalHistoryDao;
import jp.KEN.yamamons.entity.Items;
import jp.KEN.yamamons.entity.Members;
import jp.KEN.yamamons.entity.Order;
import jp.KEN.yamamons.entity.RentalHistory;
import jp.KEN.yamamons.model.LoginModel;

@SessionAttributes("loginModel")
@Controller
public class RentalHistoryController {

	@Autowired
	private RentalHistoryDao rentalHistoryDao;
	@Autowired
	private MembersDao membersDao;
	@Autowired
	private ItemsDao itemsDao;

	@ModelAttribute("loginModel")
	public LoginModel setupLoginForm() {
		return new LoginModel();
	}

	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public String toRentalHistory(@ModelAttribute LoginModel loginModel, Model model) {
		//ログインModelから顧客Idを取得する
		Members members = membersDao.getCusDataByMail(loginModel.getLoginMail());
		String customerId = members.getCustomerId();
		//顧客IDからその人のレンタル履歴を取得する
		List<Order> rentalHis = rentalHistoryDao.getOrderHisByCusId(customerId);

		//取得したレンタル履歴を、表示用の履歴一覧に入れ込む
		ArrayList<RentalHistory> rentalHistories = new ArrayList<RentalHistory>();
		for (int i = 0; i<rentalHis.size(); i++) {
			RentalHistory rentalHistory = new RentalHistory();
		    rentalHistory.setOrderDate(rentalHis.get(i).getOrderDate());
		    rentalHistory.setCustomerId(rentalHis.get(i).getCustomerId());
		    rentalHistory.setItemNo(rentalHis.get(i).getItemNo());
		    //履歴のItemNoからt_itemの情報を取得する
		    String itemNo = rentalHis.get(i).getItemNo();
		    Items item = itemsDao.getItemsByNo(Integer.parseInt(itemNo));
		    rentalHistory.setItemName(item.getItemName());
		    rentalHistory.setGenreNo(item.getGenreNo());
		    rentalHistory.setDirector(item.getDirector());
		    rentalHistory.setItemPicture(item.getItemPicture());

		    String check = rentalHis.get(i).getRentalStatusNo();
		    if(check.equals("1")) {
		    	rentalHistory.setRentalStatusNo("貸出中");
		    }else {
		    	rentalHistory.setRentalStatusNo("返却済");
		    }
		    //1件分のレンタル履歴を表示用のListに追加する
		    rentalHistories.add(rentalHistory);
		}

		model.addAttribute("rentalHistories", rentalHistories);
		return "rental_history6";

	}
}
