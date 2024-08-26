package com.BaiTapLab.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.BaiTapLab.Entity.TuyenDung;
import com.BaiTapLab.Repository.TuyenDungRepository;

@Controller
public class TransferController {
	@Autowired
	TuyenDungRepository tuyendungRepo;
//	@GetMapping("/list/transfer")
//	public String getList() {
//		return "home/transfer";
//	}
	@GetMapping("/list/transfer/{maTD}")
	public String getList(@PathVariable("maTD") Integer maTD, Model model) {
		TuyenDung tuyendung = tuyendungRepo.findById(maTD).orElse(null);
		model.addAttribute("tuyenDungTrans", tuyendung);
		return "home/transfer";
	}
}
