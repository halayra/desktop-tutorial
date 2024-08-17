package com.BaiTapLab.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.BaiTapLab.Entity.Account;

import jakarta.servlet.http.HttpSession;

@RequestMapping("home")
@Controller
public class HomeController {

	@GetMapping("")
	public String home(HttpSession session, Model model) {
	    Account loggedAccount = (Account) session.getAttribute("loggedAccount");
	    if (loggedAccount != null) {
	        model.addAttribute("username", loggedAccount.getUsername());
	        model.addAttribute("hinhAnh", loggedAccount.getHinhAnh());
	    } else {
	        model.addAttribute("username", "Guest");
	    }
	    return "home/index";
	}

	@GetMapping("/test")
	public String getMethodName() {
		return "test";
	}

	@GetMapping("/dangbai/test")
	public String getPost() {
		return "post/test";
	}

}
