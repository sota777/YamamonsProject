
package jp.KEN.yamamons.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.KEN.yamamons.dao.ItemsDao;
import jp.KEN.yamamons.entity.Items;


@Controller
public class SearchController {

	@Autowired
	private ItemsDao itemsDao;
	private Integer quantity;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String toSearch(Model model) {
		model.addAttribute("items", new Items());
        model.addAttribute("genres", itemsDao.getGenreList()); // itemsDaoからジャンルデータを取得
		return "search";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchItems(@ModelAttribute Items items, Model model) {
		boolean itemNameIsEmpty = items.getItemName().isEmpty();
		boolean itemGenreIsEmpty = items.getGenreNo().isEmpty();
		model.addAttribute("genre", itemsDao.getItemsList() );


		if(itemNameIsEmpty && itemGenreIsEmpty) {
			List<Items> itemsList = itemsDao.getItemsList();
			model.addAttribute("itemsList", itemsList);
		} else if(itemNameIsEmpty && !itemGenreIsEmpty) {
			int genre = itemsDao.getByGenre(quantity);

			System.out.println(genre);
			if (genre == 0) {
				model.addAttribute("message", "該当データはありません");
			} else {
				List<Items> itemsList = new ArrayList<Items>();
				model.addAttribute("itemsList", itemsList);
			}
		}else if(!itemNameIsEmpty && itemGenreIsEmpty) {
			List<Items> itemsList = itemsDao.getListByName(items.getItemName());
			if(itemsList.isEmpty()) {
				model.addAttribute("message", "該当データはありません");
			} else {
				model.addAttribute("itemsList", itemsList);
			}


		} else {
			model.addAttribute("message", "作品名を入力してください");
		}
		return "search";
	}
}
