package jp.KEN.yamamons.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@SessionAttributes("loginModel")
@Controller
public class AdminController {

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String toAdmin(Model model) {
		return "stock6";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String Admin( Model model){
		return "stock6";
	}


}
