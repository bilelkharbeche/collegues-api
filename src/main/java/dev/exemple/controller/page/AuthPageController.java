package dev.exemple.controller.page;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import dev.exemple.InfosAuthentification;
import dev.exemple.controller.AuthUtil;

@Controller
public class AuthPageController {

	@Autowired
	private AuthUtil authUtil;

	@GetMapping("/page/auth")
	public String auth(Model model) {
		model.addAttribute("infos", new InfosAuthentification());
		return "index";
	}

	@PostMapping("/page/auth")
	public String post(@ModelAttribute("infos") InfosAuthentification infos, HttpServletResponse response) {
		Optional<String> token = authUtil.authPartial(infos);

		if (token.isPresent()) {
			response.addHeader("Set-Cookie", token.get());
		}
		return "home";
	}

}
