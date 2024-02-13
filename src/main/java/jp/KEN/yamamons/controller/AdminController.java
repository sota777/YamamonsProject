package jp.KEN.yamamons.controller;
import java.util.ArrayList;
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
import jp.KEN.yamamons.model.AdminModel;


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

		AdminModel aModel = new AdminModel();
		List<String> orderStatusList = new ArrayList<String>();

		for(int i = 0;i <= itemsList.size();i++) {
			aModel.setItemNo(i);
			//for(Items itemNo : itemsList) {
				List<Order> orderList = ManagerDao.getOrderItemNo(aModel.getItemNo());
				//System.out.println(orderList.size());
				orderStatusList.add(String.valueOf(orderList.size()));
			//}
		}

		model.addAttribute("orderList",orderStatusList);


		return "stock6";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String Admin( Model model){
		return "stock6";
	}

	@RequestMapping(value = "/newItem", method = RequestMethod.GET)
	public String toNerItem(Model model) {
		return "newItem7";
	}

	@RequestMapping(value = "/newItem", method = RequestMethod.POST)
	public String NerItem(Model model) {
		return "newItem7";
	}

	@RequestMapping(value = "/rentalStatus", method = RequestMethod.GET)
	public String toRentalStatus(Model model) {
		return "RentalStatus8";
	}

	@RequestMapping(value = "/rentalStatus", method = RequestMethod.POST)
	public String RentalStatus(Model model) {
		return "RentalStatus8";
	}



}
