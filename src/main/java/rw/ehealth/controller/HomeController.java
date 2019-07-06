
package rw.ehealth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String homepage() {
		return "homepage";
	}

	@GetMapping("/patient")
	public String newPatientInfo() {
		return "patient";
	}

}
