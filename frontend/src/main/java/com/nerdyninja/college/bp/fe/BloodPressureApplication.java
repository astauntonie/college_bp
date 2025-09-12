package com.nerdyninja.college.bp.fe;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@CommonsLog
public class BloodPressureApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloodPressureApplication.class, args);
	}

}
