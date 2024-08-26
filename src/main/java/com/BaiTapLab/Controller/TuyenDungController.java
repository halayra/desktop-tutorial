package com.BaiTapLab.Controller;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.BaiTapLab.Entity.Account;
import com.BaiTapLab.Entity.TuyenDung;
import com.BaiTapLab.Repository.TuyenDungRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class TuyenDungController {
	@Autowired
	HttpSession session;
	
	@Autowired
	TuyenDungRepository tuyendungRepo;
	
	@GetMapping("/tuyendung")
	public String getTuyenDung(Model model) {
		Account loggedAccount = (Account) session.getAttribute("loggedAccount");
	    if (loggedAccount != null) {
	        model.addAttribute("username", loggedAccount.getUsername());
	        //model.addAttribute("hinhAnh", loggedAccount.getHinhAnh());
	    } else {
	        model.addAttribute("username", "Guest");
	    }
		return "home/tuyendung";
	}
	
	@GetMapping("/tuyendung/list")
	public String getList(Model model) {
		Account loggedAccount = (Account) session.getAttribute("loggedAccount");
	    if (loggedAccount != null) {
	        model.addAttribute("username", loggedAccount.getUsername());
	        //model.addAttribute("hinhAnh", loggedAccount.getHinhAnh());
	    } else {
	        model.addAttribute("username", "Guest");
	    }
		return "home/tuyendungList";
	}
	
	@GetMapping("/tuyendung/detail/{matd}")
	public String detail(@PathVariable("matd") Integer matd, Model model) {
		TuyenDung tuyendung = tuyendungRepo.findById(matd).orElse(null);
		try {
			model.addAttribute("listTuyenDung", tuyendung);
		} catch (NoSuchElementException e) {
			throw e;
		}
		return "home/detailTuyenDung";
	}
}
