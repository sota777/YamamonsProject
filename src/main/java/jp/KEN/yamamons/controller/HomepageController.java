package jp.KEN.yamamons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomepageController {

	@RequestMapping(value="/homepage1", method= RequestMethod.GET)
		public String toHomepage() {
			return "/homepage1";
		}

	@RequestMapping(value="/homepage1", method= RequestMethod.POST)
		public String homepage() {

	}
	}



