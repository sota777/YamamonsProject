//COMMIT用

package jp.KEN.yamamons.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.KEN.yamamons.dao.MembersDao;
import jp.KEN.yamamons.dao.RentalHistoryDao;
import jp.KEN.yamamons.entity.Members;
import jp.KEN.yamamons.entity.OrderItems;
import jp.KEN.yamamons.model.LoginModel;


@SessionAttributes("loginModel")
@Controller
public class RentalHistoryController {

	@Autowired
	private  RentalHistoryDao rentalHistoryDao;
	@Autowired
	private  MembersDao membersDao;

		//rental_cart4.jspで「レンタル履歴」を押したとき
		@RequestMapping(value="/history", method= RequestMethod.GET)
			public String toRentalHistory(@ModelAttribute LoginModel loginModel, Model model) {
			Members members = membersDao.getCusDataByMail(loginModel.getLoginMail());
			String customerId = members.getCustomerId();
			List<OrderItems> rentalHistories = rentalHistoryDao.getHistoryByCustomerIdOnly(customerId);
			model.addAttribute("rentalHistories", rentalHistories);
			System.out.println("エラー出る"+rentalHistories);
			return "rental_history6";

		}
	}
