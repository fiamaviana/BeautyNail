package com.beautynail.web;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.beautynail.domain.Booking;
import com.beautynail.domain.Manicure;
import com.beautynail.domain.Users;
import com.beautynail.repositories.ManicureRepository;

import javassist.NotFoundException;

@Controller
public class ManicureController {
	
	@Autowired
	private ManicureRepository manicureRepo;
	
	@GetMapping("/manicure")
	public String manicure(ModelMap model) {
		List<Manicure> manicure = manicureRepo.findAll();
		model.put("manicure", manicure);
		return "manicure";
	}
	
	@PostMapping("/manicure")
	public String createManicure() {
		Manicure manicure = new Manicure();
		manicureRepo.save(manicure);
		return "redirect:/manicure/"+manicure.getManicureID();
	}
	
	@GetMapping("/manicure/{manicureId}")
	public String getManicure(@PathVariable Integer manicureId, ModelMap model, HttpServletResponse response) throws IOException {	
		//used optional in case the id is null 
		Optional<Manicure> manicureOpt = manicureRepo.findById(manicureId);
		if(manicureOpt.isPresent()) {
			Manicure manicure = manicureOpt.get();
			model.put("manicure",manicure);
		}else {
			
			response.sendError(HttpStatus.NOT_FOUND.value(), "Manicure with id" + manicureId + "was not found");
			
		}
		return "manicureForm";
		
	}
	

	//updating or deleting a manicure service
	@RequestMapping(method = RequestMethod.POST, value="/manicure/{manicureId}")
	public String edit(@ModelAttribute ModelMap model,
			@RequestParam(value="action",required=true)String action,
			@PathVariable Integer manicureId,Manicure manicure) {
		if(action.equals("save")){
			manicure = manicureRepo.save(manicure);
		}
		if(action.equals("delete")) {
			manicureRepo.deleteById(manicureId);	
		}
				
		return "redirect:/manicure"; 
	}
	
	
	
	
	@PostMapping("/manicureForm")
	public String editManicure() {
		return "manicureForm";
	}
	

}
