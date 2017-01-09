package com.example;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("userLogin")
public class UserController {
	@Autowired
	private UserRepository repository;
	
    @PostConstruct
    public void populateUserRepository() {
		User userAdmin = new User();
		userAdmin.setEmail("admin@admin.pl");
		userAdmin.setLogin("admin");
		userAdmin.setPassword("admin123");
		userAdmin.setPermissions(2);
		repository.save(userAdmin);
		User userSklep = new User();
		userSklep.setEmail("super@sklep.pl");
		userSklep.setLogin("Super Sklep");
		userSklep.setPassword("ss");
		repository.save(userSklep);
		User userA = new User();
		userA.setEmail("a@a.a");
		userA.setLogin("a");
		userA.setPassword("a");
		repository.save(userA);
		User userB = new User();
		userB.setEmail("b@b.b");
		userB.setLogin("b");
		userB.setPassword("b");
		repository.save(userB);
    }
	
	@PostMapping("/login")
	public ModelAndView loginForm(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session){
		ModelAndView modelAndView = new ModelAndView();
		User user = repository.findOne(username);
		if(user != null){
			if(user.getPassword().equals(password)){
				 session.setAttribute("login", user.getLogin());
				 session.setAttribute("email", user.getEmail());
				 session.setAttribute("firstName", user.getFirstName());
				 session.setAttribute("lastName", user.getLastName());
				 session.setAttribute("permissions", user.getPermissions());
				 session.setAttribute("bids", user.getBidList());
				 modelAndView.setViewName("redirect:/");
			}
		}
		return modelAndView;
	}
	
	@GetMapping("/logout")
	public ModelAndView logutPage(HttpSession session){
		session.setAttribute("login", null);
		session.setAttribute("email", null);
		session.setAttribute("firstName", null);
		session.setAttribute("lastName", null);
		session.setAttribute("permissions", null);
		return new ModelAndView("redirect:/");
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
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping("/admin_users")
	public String getAdminOffer(Model model){
		model.addAttribute("users", repository.findAll());
        return "admin_users";
    }
	
	@PostMapping("/user/{id}/delete")
	public ModelAndView postDeleteOffer(@PathVariable String id, HttpSession session){
		if(!session.getAttribute("login").equals(id)){
			repository.delete(id);
		}
		return new ModelAndView("redirect:/");
    }
	//@PostMapping(value="/login")
//	public ModelAndView loginSubmit(Model model){
//		model.addAttribute(arg0);
//		return new ModelAndView("redirect:/");
//	}
}
