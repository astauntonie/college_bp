package com.nerdyninja.college.bp.fe;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nerdyninja.college.bp.fe.db.BPMongoData;
import com.nerdyninja.college.bp.fe.db.BPMongoRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${bp.backend.host}")
	String backendHost;

	@Value("${bp.backend.port}")
	String backendPort;

	BPBERequest beRquest = new BPBERequest();
	String jsonData;

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

		bloodPressureData.getCategoryType();
		beRquest.init(backendHost,backendPort);
		try {
			jsonData = beRquest.runHttpQuery(bloodPressureData.toJSON());
		}
		catch (Exception e){
			log.error("An error occurred when getting the response from the BE:\n" + e);
		}

		ObjectMapper objectMapper = new ObjectMapper();
		BloodPressureData beData;
		try {
			beData = objectMapper.readValue(jsonData, BloodPressureData.class);
			model.addAttribute("bloodPressureData", beData);
		}
		catch(Exception e){
			log.error("An error occurred converting the response from JSON:\n" + e);
		}

		model.addAttribute("userdb", mongoRepo.findAllByEmail(bloodPressureData.getEmail()));

		return "form";
	}
}
