package com.nerdyninja.college.bp.be;

import javax.validation.Valid;

import com.nerdyninja.college.bp.be.db.BPMongoData;
import com.nerdyninja.college.bp.be.db.BPMongoRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@CommonsLog
public class WebController implements WebMvcConfigurer {
	@Autowired
	BPMongoRepository mongoRepo;
	BPMongoData mongoData;
	
	@GetMapping("/")
	public String showBPForm(Model model) {
		model.addAttribute("bloodPressureData", new BloodPressureData());
		return "form";
	}

	@PostMapping("/")
	public String checkPersonInfo(@Valid @ModelAttribute BloodPressureData bloodPressureData, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "form";
		}
		log.info("Using Web Form in BE");
		bloodPressureData.getCategoryType();

		mongoData = new BPMongoData(bloodPressureData);
		mongoRepo.save(mongoData);
		log.info("Data saved to the database");

		model.addAttribute("bloodPressureData", bloodPressureData);
		model.addAttribute("userdb", mongoRepo.findAllByEmail(bloodPressureData.getEmail()));

		return "form";
	}
}
