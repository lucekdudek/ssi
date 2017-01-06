package com.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OfferController {

	@Autowired
	private OfferRepository repository;
	
	@Autowired
    private HttpServletRequest request;
	
    @PostConstruct
    public void populateOfferRepository() {
//    	Offer offer1 = new Offer();
//    	offer1.setUserLogin("Super Sklep");
//    	offer1.setPrice(10);
//    	offer1.setName("Playbook");
//    	offer1.setDesc("goodlike playbook");
//    	//offer1.setData("unkonow");
//		repository.save(offer1);
    }

	@GetMapping("/")
	public String getOffers(Model model){
		model.addAttribute("products", repository.findAll());
	    return "offers";
	}

	@GetMapping("/offer/{id}")
	public String getOffer(@PathVariable long id, Model model){
		model.addAttribute("product", repository.findOne(id));
        return "offer";
    }
	
	@GetMapping("/admin_offers")
	public String getAdminOffer(Model model){
		model.addAttribute("products", repository.findAll());
        return "admin_offers";
    }

	@GetMapping("/add")
	public String addOffer(){
        return "add";
    }
	
	@PostMapping("/add")
	public ModelAndView postOffer(@RequestParam("name") String name,
							@RequestParam("file") MultipartFile photo,
							@RequestParam("price") String price,
							@RequestParam("desc") String desc,
							@RequestParam("date") String date, HttpSession session){
		
		if(!photo.isEmpty()){
			String uploadsDir = "/uploads/";
	        String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
	        if(! new File(realPathtoUploads).exists())
	        {
	            new File(realPathtoUploads).mkdir();
	        }
	        System.out.println(request.getServletContext().getRealPath(uploadsDir));
	        
	        File file = new File(realPathtoUploads + photo.getOriginalFilename());
			try {
				photo.transferTo(file);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}        
		
		Offer offer = new Offer(name, photo.getOriginalFilename(), Integer.parseInt(price), desc, date);
		offer.setUserLogin(session.getAttribute("login").toString());
		repository.save(offer);
		
		return new ModelAndView("redirect:/");
	    
    }
	
	@GetMapping("/offer/{id}/edit")
	public String getEditOffer(@PathVariable long id, Model model){
		model.addAttribute("product", repository.findOne(id));
        return "editoffer";
    }
	
	@PostMapping("/offer/{id}/edit")
	public ModelAndView postEditOffer(@PathVariable long id,
			@RequestParam("name") String name,
			@RequestParam("price") String price,
			@RequestParam("desc") String desc){
		
		Offer offer = repository.findOne(id);
		offer.setDesc(desc);
		offer.setName(name);
		//TODO change min price
		repository.save(offer);
		return new ModelAndView("redirect:/");
    }
	
	@PostMapping(value="/offer/{id}/bid")
    @ResponseBody
	public String postBidOffer(@PathVariable long id,
			@RequestParam("price") String price, HttpSession session){
		Offer offer = repository.findOne(id);
		int oldPrice = offer.getPrice();
		if(oldPrice < Integer.parseInt(price) && session.getAttribute("login")!=offer.getUserLogin() && Integer.parseInt(session.getAttribute("permissions").toString())==1){
			offer.setPrice(Integer.parseInt(price));
			String buyerId = offer.getBuyerId();
			
			ArrayList<String> biddersList = offer.getBiddersList();
			biddersList.add(session.getAttribute("login").toString() + " - " + Integer.parseInt(price));
			offer.setBiddersList(biddersList);
			System.out.println(biddersList);
			
			offer.setBuyerId(session.getAttribute("login").toString()); // TODO wpisać tu nazwę usera który licytował
		}
		//TODO change min price
		repository.save(offer);
		return String.valueOf(offer.getPrice());
    }
	
	@PostMapping("/offer/{id}/delete")
	public ModelAndView postDeleteOffer(@PathVariable long id){
		repository.delete(id);
		return new ModelAndView("redirect:/");
    }
	
}
