package jp.KEN.yamamons.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.KEN.yamamons.dao.GenresDao;
import jp.KEN.yamamons.dao.ItemsDao;
import jp.KEN.yamamons.entity.Genres;
import jp.KEN.yamamons.entity.Items;

@Controller
public class SearchController {

	@Autowired
	private ItemsDao itemsDao;

	@Autowired
	private GenresDao genresDao;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String toSearch(Model model) {
		model.addAttribute("items", new Items());
		model.addAttribute("genres", new Genres());
		List<Genres> genresList = genresDao.getGenresList();
		Genres select = new Genres();
		select.setGenre("選択してください");
		select.setGenreNo("-1");
		genresList.add(0, select);
		model.addAttribute("genresList", genresList);
		return "search";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchItems(@ModelAttribute Items items,@ModelAttribute Genres genres, Model model) {
		boolean itemNameIsEmpty = items.getItemName().isEmpty();
		int searchGenre = Integer.parseInt(genres.getGenreNo());


		List<Genres> genresList = genresDao.getGenresList();
		Genres select = new Genres();
		select.setGenre("選択してください");
		select.setGenreNo("-1");
		genresList.add(0, select);
		model.addAttribute("genresList", genresList);

		//ジャンル検索
		if(itemNameIsEmpty && searchGenre != -1) {
			Integer genreNo = Integer.parseInt(genres.getGenreNo());
			List<Items> aaList = genresDao.getByGenre(genreNo);
			model.addAttribute("itemsList", aaList);
			return "search";
		//ジャンルAND名前検索
		} else if(!itemNameIsEmpty && searchGenre != -1) {
			Integer genreNo = Integer.parseInt(genres.getGenreNo());
			List<Items> bbList = genresDao.getListByNameAndNo(items.getItemName(),genreNo);
			model.addAttribute("itemsList", bbList);
			
			if(bbList.isEmpty()) {
				model.addAttribute("message", "該当データはありません");
			}
		}
	
		
		//全件検索
		if(itemNameIsEmpty && searchGenre == -1) {
			List<Items> itemsList = itemsDao.getItemsList();
			model.addAttribute("itemsList", itemsList);
		//作品名検索
		}else if(!itemNameIsEmpty && searchGenre == -1 ) {
			List<Items> itemsList = itemsDao.getListByName(items.getItemName());
			if(itemsList.isEmpty()) {
				model.addAttribute("message", "該当データはありません");
			} else {
				model.addAttribute("itemsList", itemsList);
			}
		}
		return "search";
		
	}
}
