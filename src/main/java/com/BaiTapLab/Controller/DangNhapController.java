package com.BaiTapLab.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.BaiTapLab.Entity.Account;
import com.BaiTapLab.Service.AccountService;
import com.BaiTapLab.Service.SessionService;

import jakarta.servlet.http.HttpSession;


@Controller
public class DangNhapController {
	@Autowired
	AccountService accountService;

	@Autowired
	SessionService sessionService;

	@Autowired
	HttpSession session;

	@GetMapping("/login")
	public String dangnhap(@ModelAttribute("account") Account account, Model model) {
		return "home/login";
	}


	@PostMapping("/login/save")
	public String save(@ModelAttribute("account") Account account, Model model) {
	    return accountService.findByUsernameAndPassword(account.getUsername(), account.getPassword())
	        .map(loggedAccount -> {
	            session.setAttribute("loggedAccount", loggedAccount);
	            model.addAttribute("username", loggedAccount.getUsername());
	            model.addAttribute("hinhAnh", loggedAccount.getHinhAnh());
	            model.addAttribute("hovaten", loggedAccount.getHovaten());
	            // sessionService.set("account", loggedAccount);
	            return "admin".equalsIgnoreCase(loggedAccount.getRole()) ? "home/dashboard" : "home/index";
	        })
	        .orElseGet(() -> {
	            model.addAttribute("loginError", "Invalid username or password");
	            return "home/login";
	        });
	}
	
	@GetMapping("/logout")
	public String logout() {
	    // Xóa thông tin tài khoản khỏi session
	    session.invalidate();
	    
	    // Chuyển hướng đến trang đăng nhập sau khi đăng xuất
	    return "redirect:/login";
	}

//	@PostMapping("/login/save")
//  public String save(@ModelAttribute("account") Account account, Model model) {
//      Optional<Account> optionalAccount = accountService.findByUsernameAndPassword(account.getUsername(), account.getPassword());
//      if(optionalAccount.isPresent()) {
//          Account loggedAccount = optionalAccount.get();
//          session.setAttribute("loggedAccount", loggedAccount);
////          sessionService.set("account", loggedAccount);
//          if("admin".equalsIgnoreCase(loggedAccount.getRole())) {
//              return "home/index";
//          } else {
//              return "redirect:/home";
//          }
//      } else {
//          model.addAttribute("loginError", "Invalid username or password");
//          return "home/login";
//      }
//  }
}
