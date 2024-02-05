package jp.KEN.yamamons.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.KEN.yamamons.dao.ItemsDao;
import jp.KEN.yamamons.dao.ManagerDao;
import jp.KEN.yamamons.entity.Items;
import jp.KEN.yamamons.entity.Order;


@SessionAttributes("loginModel")
@Controller
public class AdminController {

	@Autowired
	private ItemsDao itemsDao;

	@Autowired
	private ManagerDao ManagerDao;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String toAdmin(Model model) {

		//itemリストから基本情報を取得
		//データベースの内容をList型で取得し、JSPで表示できるようaddAttribute
		List<Items> itemsList = itemsDao.getItemsList();
		model.addAttribute("itemsList", itemsList);
		for(Items itemNo : itemsList) {
			List<Order> orderList = ManagerDao.getOrderItemNo(itemNo);
			System.out.println(orderList.size());
		}


		return "stock6";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String Admin( Model model){
		return "stock6";
	}


}
