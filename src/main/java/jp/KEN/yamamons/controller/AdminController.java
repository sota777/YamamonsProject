package jp.KEN.yamamons.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.KEN.yamamons.dao.ItemsDao;
import jp.KEN.yamamons.entity.Items;


@SessionAttributes("loginModel")
@Controller
public class AdminController {

	@Autowired
	private ItemsDao itemsDao;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String toAdmin(Model model) {

		List<Items> itemsList = itemsDao.getItemsList();
		model.addAttribute("itemsList", itemsList);

		return "stock6";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String Admin( Model model){
		return "stock6";
	}


}
