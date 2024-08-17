package com.BaiTapLab.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
	@GetMapping("/post")
	public String get() {
		return "post/post";
	}
}
