//レンタル画面履歴表示のコントローラー

package jp.KEN.yamamons.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.KEN.yamamons.dao.RentalHistoryDao;
import jp.KEN.yamamons.entity.Items;
import jp.KEN.yamamons.model.LoginModel;


@SessionAttributes("loginModel")
@Controller
public class RentalHistoryController {

	@Autowired
	private  RentalHistoryDao rentalHistoryDao;

	@ModelAttribute("loginModel")
	public LoginModel setupLoginForm() {
		return new LoginModel();
	}

		@RequestMapping(value="/history", method= RequestMethod.GET)
			public String toRentalHistory(@ModelAttribute LoginModel loginModel, Model model) {
				List<Items> rentalHistory = rentalHistoryDao.getHistoryByCustomerName(loginModel.getCustomerName());
				model.addAttribute("rentalHistory", rentalHistory);
				return "rental_history6";

		}
	}