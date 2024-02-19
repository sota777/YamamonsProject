package jp.KEN.yamamons.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.KEN.yamamons.dao.ItemsDao;
import jp.KEN.yamamons.dao.ManagerDao;
import jp.KEN.yamamons.dao.RentalHistoryDao;
import jp.KEN.yamamons.entity.Items;
import jp.KEN.yamamons.entity.Order;
import jp.KEN.yamamons.entity.RentalHistory;
import jp.KEN.yamamons.model.AdminModel;
import jp.KEN.yamamons.model.CheckboxForm;


@SessionAttributes("loginModel")
@Controller
public class AdminController {

	@Autowired
	private ItemsDao itemsDao;

	@Autowired
	private ManagerDao ManagerDao;
	@Autowired
	private  RentalHistoryDao rentalHistoryDao;

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

	//商品追加
	@RequestMapping(value = "/newItem", method = RequestMethod.GET)
	public String toNerItem(Model model) {
		model.addAttribute("adminModel", new AdminModel());
		return "newItem7";
	}

	@RequestMapping(value = "/newItem", method = RequestMethod.POST)
	public String NerItem(Model model,@Validated @ModelAttribute AdminModel adminModel,
			BindingResult result) {

		if(result.hasErrors()) {
			model.addAttribute("headline", "会員登録");
			return "newItem7";
		}


		Items items = new Items();
		items.setItemName(adminModel.getItemName());
		items.setItemQuantity(adminModel.getItemQuanity());
		items.setGenreNo(adminModel.getGenreNo());
		items.setDirector(adminModel.getDirector());
		items.setTypeNo(adminModel.getTypeNo());
		items.setItemPicture(adminModel.getItemPicture());

		int numberOfRow = ManagerDao.insertItem(items);
		if (numberOfRow == 0){
			model.addAttribute("message", "登録に失敗しました。");
			model.addAttribute("headline", "商品登録");
			return "newItem7";
		}
		model.addAttribute("message","在庫追加しました");
		return "newItem7";
	}


	//返却処理ページ
	@RequestMapping(value = "/rentalStatus", method = RequestMethod.GET)
	public String toRentalStatus(@ModelAttribute("checkboxForm") CheckboxForm checkboxForm,Model model) {
		List<RentalHistory> notReturnItems = rentalHistoryDao.getOrderListNotReturn();
		if (notReturnItems.isEmpty()) {
			model.addAttribute("message", "全て返却されています");
		}
		model.addAttribute("notReturnItems", notReturnItems);
		model.addAttribute("message", "返却処理を行うものにチェックしてください");

		return "RentalStatus10";
	}

	//返却処理実行
	@RequestMapping(value = "/rentalStatus", method = RequestMethod.POST)
	public String changeRentalStatus(@ModelAttribute("checkboxForm") CheckboxForm checkboxForm, Model model){

		 // 選択されたorderNoを取得
        List<String> selectedOrderNos = checkboxForm.getSelectedOrderNos();
        if (selectedOrderNos == null) {
        	model.addAttribute("message", "商品を選択してください");
    		return "RentalStatus10";
        }

        for (String orderNo : selectedOrderNos) {
        	int numberOfRow =itemsDao.changeReturnStatusNo(orderNo);
        	if (numberOfRow == 0) {
        		model.addAttribute("message", "返却処理に失敗しました。");
        		return "RentalStatus10";
        	}
        	String itemNo = itemsDao.getItemNoFromOrderNo(orderNo);
        	if (itemNo.isEmpty()) {
        		model.addAttribute("message", "返却処理(商品Id取得)に失敗しました。");
        		return "RentalStatus10";
        	}
        	int numberOfRow1 = itemsDao.increaseItemQuantity(itemNo);
        	if (numberOfRow1 == 0) {
        		model.addAttribute("message", "在庫の更新に失敗しました。");
        		return "RentalStatus10";
        	}
        }

        List<RentalHistory> notReturnItems = rentalHistoryDao.getOrderListNotReturn();
		model.addAttribute("notReturnItems", notReturnItems);
		model.addAttribute("message", "返却処理が完了しました。");


		return "RentalStatus10";


	}



}
