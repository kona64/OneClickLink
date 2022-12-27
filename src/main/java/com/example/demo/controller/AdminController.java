package com.example.demo.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.LinkService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private LinkService linkService;
	
	@GetMapping("/links")
	public String getLinks(Model model) {
		model.addAttribute("links", linkService.getAllLinks());
		return "admin/links";
	}
	
	@PostMapping("/deleteLink")
	public String deleteLink(@RequestParam("linkID") int linkID) {
		linkService.deleteLink(linkID);
		return "redirect:links";
	}
	@GetMapping("/login")
	public String getLogin(Model model, @RequestParam(value="error", required=false) String error) {
		if (!Objects.isNull(error)) {
			model.addAttribute("error", "wrong password");
		}
		return "admin/login";
	}
}
