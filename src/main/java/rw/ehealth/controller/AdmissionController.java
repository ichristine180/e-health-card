/*
 * Copyright (c)  2018. Irembo
 *
 * All rights reserved.
 */

package rw.ehealth.controller;

import javax.validation.Valid;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import rw.ehealth.model.Patient;
import rw.ehealth.service.patient.IPatientService;

@Controller
public class AdmissionController {

	@Autowired
	private IPatientService patientService;

	@GetMapping("/registration")
	public String registerPatient(Model model) {
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		return "registration";
	}

	@PostMapping("/registration")
	public String registerPatient(Model model, @ModelAttribute @Valid Patient patient) {

		if (patient.getIdentificationNumber() != null) {
			Patient existingPatient = patientService
					.findPatientByIdentificationNumber(patient.getIdentificationNumber());

			if (existingPatient != null) {
				model.addAttribute("message", "Patient already registered");
				model.addAttribute("patient", patient);

				return "homepage";
			}
			Patient newPatient = new Patient();

			newPatient.setAddress(patient.getAddress());
			newPatient.setDateOfBirth(patient.getDateOfBirth());
			newPatient.setFname(patient.getFname());
			newPatient.setLname(patient.getLname());
			newPatient.setGender(patient.getGender());
			newPatient.setIdentificationNumber(patient.getIdentificationNumber());
			newPatient.setPatientNumber(this.generatePatientNumber(patient));

			Patient registeredPatient = patientService.savePatientInfo(newPatient);

			model.addAttribute("patientId", registeredPatient.getPatientNumber());
			model.addAttribute("patient", registeredPatient);

			return "registrationSuccess";
		}

		model.addAttribute("error", "Invalid Patient Data");
		model.addAttribute("patient", patient);
		return "homepage";

	}

	@PostMapping("/search/patient")
	public String searchPatientInformation(Model model, @RequestParam String patientNumber) {
		if (patientNumber != null) {
			Patient result = patientService.findPatientByPatientNumber(patientNumber);
			// if the patient is found, we proceed with admission
			if (result != null) {
				model.addAttribute("patient", result);
				return "admit";
			}
			// If not found, go to registration page to register a patient first
			Patient patient = new Patient();
			model.addAttribute("patient", patient);
			return "registration";
		}
		return "reception";
	}

	/**
	 * @param patient
	 * @return
	 */
	private String generatePatientNumber(Patient patient) {
		// TODO Revise it to make it more complex
		return RandomStringUtils.randomAlphanumeric(6).toUpperCase();
	}

}
