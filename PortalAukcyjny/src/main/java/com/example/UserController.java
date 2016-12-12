package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("user")
public class UserController {
	@Autowired
	private UserRepository repository;
	
	@PostMapping("/login")
	public ModelAndView loginForm(@RequestParam("username") String username, @RequestParam("password") String password){
		ModelAndView modelAndView = new ModelAndView();
		User user = repository.findOne(username);
		if(user != null){
			if(user.getPassword().equals(password)){
				 modelAndView.addObject("user", user);
				 modelAndView.setViewName("/");
			}
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/login")
	public ModelAndView loginPage() {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/register")
	public ModelAndView registerPage() {
		return new ModelAndView("register");
	}
	
	@PostMapping(value = "/register")
	public ModelAndView register(@RequestParam("email") String email, @RequestParam("username") String username,@RequestParam("password") String password) {
		User user = new User();
		user.setEmail(email);
		user.setLogin(username);
		user.setPassword(password);
		repository.save(user);
		return new ModelAndView("register");
	}
	//@PostMapping(value="/login")
//	public ModelAndView loginSubmit(Model model){
//		model.addAttribute(arg0);
//		return new ModelAndView("redirect:/");
//	}
}