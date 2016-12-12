package com.example;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OfferController {

	@Autowired
	private OfferRepository repository;
	
	@Autowired
    private HttpServletRequest request;

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

	@GetMapping("/add")
	public String addOffer(){
        return "add";
    }
	
	@PostMapping("/add")
	public ModelAndView postOffer(@RequestParam("name") String name,
							@RequestParam("file") MultipartFile photo,
							@RequestParam("price") String price,
							@RequestParam("desc") String desc){
		
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
		
		Offer offer = new Offer(name, photo.getOriginalFilename(), desc);
		
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
		return new ModelAndView("redirect:/offer/"+id);
    }
	
	@PostMapping("/offer/{id}/delete")
	public ModelAndView postDeleteOffer(@PathVariable long id){
		repository.delete(id);
		return new ModelAndView("redirect:/");
    }
	
}
