/*
 * Copyright (c)  2018. Irembo
 *
 * All rights reserved.
 */

package rw.ehealth.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import rw.ehealth.model.Patient;

@Controller
public class AdmissionController {

	@GetMapping("/registration")
	public String registerPatient(Model model) {
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		return "registration";
	}

	@PostMapping("/registration")
	public String registerPatient(Model model, @ModelAttribute @Valid Patient patient) {
		System.out.println(" Proof og concept" + patient.toString());
		return "/homepage";
	}

}
