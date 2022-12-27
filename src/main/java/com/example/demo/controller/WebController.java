package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.service.Link;
import com.example.demo.service.LinkService;
import com.example.demo.service.ServiceException;

@Controller
@RequestMapping("/")
public class WebController {
		
	@Autowired
	private LinkService linkService;
	
	@GetMapping("/RedirectionFailed")
	String getRedirectionFailure() {
		return "RedirectionFailed";
	}
	
	@GetMapping("/home")
	String getHome(Model model) {
		return "home";
	}
	
	@PostMapping("/home")
	String processLink(@RequestParam("redirectLink") String redirectLink, Model model, @RequestHeader String host ) {
		if (HTTPLinkManipulator.validate(redirectLink)) {
			String HTTPRedirectLink = HTTPLinkManipulator.convertStringToHTTPSForm(redirectLink);
			Link link = linkService.createLink(HTTPRedirectLink);
			model.addAttribute("requestLink", host + "/" + link.getRequestLink());
		}
		else {
			model.addAttribute("errorMessage", "not a valid link");
		}
		return "home";
	}
	
	@RequestMapping("/{requestLink}")
	String getRequestLink(@PathVariable("requestLink") String requestLink) {
		try {
			String redirectString = linkService.getRedirectLinkAndDelete(requestLink);
			return "redirect:" + redirectString;
		}
		catch (ServiceException e) {
			return "/RedirectionFailed";
		}
	}
}
