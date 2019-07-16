/*
 * Copyright (c)  2018. Irembo
 *
 * All rights reserved.
 */

package rw.ehealth.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.Doctor;
import rw.ehealth.model.Hospital;
import rw.ehealth.model.Patient;
import rw.ehealth.model.User;
import rw.ehealth.model.security.Role;
import rw.ehealth.model.security.UserRole;
import rw.ehealth.service.admission.AdmissionService;
import rw.ehealth.service.medical.HospitalService;
import rw.ehealth.service.patient.IPatientService;
import rw.ehealth.service.patient.PatientService;
import rw.ehealth.service.user.UserService;
import rw.ehealth.utils.DoctorData;
import rw.ehealth.utils.HopitaData;

@Controller
public class AdmissionController {
	@Autowired
	private UserService userService;
	@Autowired
	private HospitalService hospitalService;
	@Autowired
	private AdmissionService admissionService;
	@Autowired
	private IPatientService patientService;

	@GetMapping("/registration")
	public String registerPatient(Model model) {
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		return "registration";
	}

	@GetMapping("/docregistration")
	public String registerDoctor(Model model) {
		DoctorData doctor = new DoctorData();
		Iterable<Role> role = userService.findAll();
		Iterable<Hospital> hospitals = hospitalService.findAllHospitals();
		model.addAttribute("hospitals", hospitals);
		model.addAttribute("role", role);
		model.addAttribute("doctor", doctor);
		boolean doctors = true;
		model.addAttribute("doctors", doctors);
		return "registration";
	}

	@GetMapping(value = "/hospregistration")
	public String registerHospital(Model model) {
		Hospital newhospital = new Hospital();
		model.addAttribute("hospital", newhospital);
		boolean hospitals = true;
		model.addAttribute("hospitals", hospitals);
		return "registration";
	}

	@PostMapping("/registration")
	public String registerPatient(Model model, @ModelAttribute @Valid Patient patient) {
		if (patient.getIdentificationNumber().isEmpty() == false) {
			if (patient.getIdentificationNumber() != null) {
				Patient existingPatient = patientService
						.findPatientByIdentificationNumber(patient.getIdentificationNumber());

				if (existingPatient != null) {
					model.addAttribute("message", "Patient already registered");
					model.addAttribute("patient", patient);

					return "registration";
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
				boolean registration=true;
				model.addAttribute("registration", registration);

				return "registrationSuccess";
			}
		}
		model.addAttribute("error", "Invalid Patient Data");
		model.addAttribute("patient", patient);
		return "registration";

	}

	@PostMapping("/hospregistration")
	public String registerhospital(Model model, @ModelAttribute @Valid Hospital hospital) {
		if (hospital.getHospitalName().isEmpty() == false) {
			Hospital existingHospital = hospitalService.findByHospitalname(hospital.getHospitalName());

			if (existingHospital != null) {
				model.addAttribute("message", "Hospital already registered");
				model.addAttribute("hospital", hospital);

				return "redirect:/hospregistration";
			}
			Hospital newhospital = new Hospital();
			newhospital.setHospitalName(hospital.getHospitalName());
			newhospital.setType(hospital.getType());
			newhospital.setAddress(hospital.getAddress());
			hospitalService.createHospital(newhospital);
			return "redirect:/";

		}
		model.addAttribute("error", "invalide hospital data");
		model.addAttribute("hospital", hospital);
		boolean hospitals = true;
		model.addAttribute("hospitals", hospitals);
		return "registration";

	}

	@PostMapping("/docregistration")
	public String adddoctor(@ModelAttribute("user") @Valid DoctorData user, Model model) {
		if (user.getHospitalname().isEmpty() == false && user.getEmail().isEmpty() == false) {
			Doctor doc = new Doctor();
			doc.setEmail(user.getEmail());
			doc.setFname(user.getFname());
			doc.setLname(user.getLname());
			doc.setTimestamp(LocalDateTime.now().toString());
			doc.setDepertment(user.getDepertment());
			doc.setPhone(user.getPhone());
			Hospital hospitals = hospitalService.findByHospitalname(user.getHospitalname());
			doc.setHospital(hospitals);

			if (userService.checkUsernameExists(user.getEmail())) {
				if (userService.checkUsernameExists(user.getEmail())) {
					model.addAttribute("emailExists", true);
					System.out.println("email exists");
				}
				return "redirect:/docregistration";
			}
			User myuser = new User();

			myuser.setUsername(user.getEmail());
			myuser.setPassword("pass");
			Set<UserRole> userRoles = new HashSet<>();
			userRoles.add(new UserRole(myuser, userService.findByName(user.getRoleName())));
			myuser.setDoctor(doc);
			doc.setUser(myuser);
			userService.createUser(myuser);
			userService.createUser(myuser, userRoles);
			return "redirect:/";
		} else
			return "redirect:/docregistration";
	}

	@PostMapping("/search/patient")
	public String searchPatientInformation(Model model, @RequestParam String patientNumber) {
		if (patientNumber != null) {
			Patient result = patientService.findPatientByPatientNumber(patientNumber);
			// if the patient is found, we proceed with admission
			if (result != null) {
				model.addAttribute("patient", result);
				boolean patientresult = true;
				model.addAttribute("found", patientresult);
				AdmissionInfo admission = new AdmissionInfo();
				model.addAttribute("admission", admission);
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

	@PostMapping("/admission")
	public String admitPatient(@ModelAttribute("admission") @Valid AdmissionInfo admission, Model model,
			@RequestParam String patientNumber, Principal principal) {
		String username = principal.getName();
		Doctor user = userService.findUserByUsername(username);
		Patient patients = patientService.findPatientByPatientNumber(patientNumber);
		AdmissionInfo newadmission = new AdmissionInfo();
		newadmission.setBloodPressure(admission.getBloodPressure());
		newadmission.setHeartRate(admission.getHeartRate());
		newadmission.setHeight(admission.getHeight());
		newadmission.setWeight(admission.getWeight());
		newadmission.setTemperature(admission.getTemperature());
		newadmission.setAdmissionDate(LocalDateTime.now().toString());
		newadmission.setAdmittedPatient(patients);

		newadmission.setDoctor(user);
		AdmissionInfo savedadmission = admissionService.createNewPatientAdmission(newadmission);
		if(savedadmission!=null) {
			model.addAttribute("admission",savedadmission);
			boolean showAdmission = true;
			model.addAttribute("admissions",showAdmission);
			model.addAttribute("patients",patients);
		return "registrationSuccess";

	}else
		return "redirect:/admission";
}
}
