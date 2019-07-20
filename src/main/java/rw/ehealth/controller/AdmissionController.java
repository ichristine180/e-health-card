/*
 * Copyright (c)  2018. Irembo
 *
 * All rights reserved.
 */

package rw.ehealth.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import rw.ehealth.service.user.UserService;
import rw.ehealth.utils.AdmissionDto;
import rw.ehealth.utils.DoctorData;

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
	public String registerPatient(Model model, @ModelAttribute @Valid Patient patient,Principal principal) {
		String username = principal.getName();
		Doctor doctor = userService.findDoctor(username);
		Hospital hospital = doctor.getHospital();
		String hospitalName = hospital.getHospitalName();
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
				newPatient.setRegisteredDate(LocalDate.now().toString());
				newPatient.setHospital(hospitalName);
				newPatient.setPatientNumber(this.generatePatientNumber(patient));

				Patient registeredPatient = patientService.savePatientInfo(newPatient);

				model.addAttribute("patientId", registeredPatient.getPatientNumber());
				model.addAttribute("patient", registeredPatient);
				boolean registration = true;
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
			doc.setTimestamp(LocalDate.now().toString());
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
	
	/**
	 * @param patient
	 * @return
	 */
	private String generatePatientNumber(Patient patient) {
		// TODO Revise it to make it more complex
		return RandomStringUtils.randomAlphanumeric(12).toUpperCase();
	}

	@SuppressWarnings("unused")
	@PostMapping("/patient/admission")
	public String admitPatient(Model model, @ModelAttribute AdmissionDto admission, Principal principal) {

		String username = principal.getName();
		Doctor user = userService.findUserByUsername(username);
		AdmissionInfo newadmission = new AdmissionInfo();
		newadmission.setBloodPressure(admission.getBloodPressure());
		newadmission.setHeartRate(admission.getHeartRate());
		newadmission.setHeight(admission.getHeight());
		newadmission.setWeight(admission.getWeight());
		newadmission.setTemperature(admission.getTemperature());
		newadmission.setDepartement(admission.getDepartement());
		newadmission.setAdmissionDate(LocalDate.now().toString());
		newadmission.setAdmittedPatient(patientService.findPatientByPatientNumber(admission.getPatientNumber()));
		newadmission.setPatientTrackingNumber(this.generateTrackingNumber());
		newadmission.setDoctor(user);

		System.out.println(newadmission.toString() + " THis is the admisssion to be saved");

		AdmissionInfo savedadmission = admissionService.createNewPatientAdmission(newadmission);

		// Update the patient admission status
		Patient patient = savedadmission.getAdmittedPatient();

		patient.setAdmissionStatus(true);
		patientService.updatePatient(patient);

		if (savedadmission != null) {
			model.addAttribute("admission", savedadmission);
			boolean showAdmission = true;
			model.addAttribute("admissions", showAdmission);
			model.addAttribute("patients", savedadmission.getAdmittedPatient());
			model.addAttribute("message", "Admission is successful for " + savedadmission.getPatientTrackingNumber());
			return "redirect:/";

		}
		model.addAttribute("error", true);
		model.addAttribute("message", "The Admission Failed! Try Again");
		return "redirect:/";

	}

	@GetMapping("/admit/patient/{patientNumber}")
	public String admitPatient(Model model, @PathVariable String patientNumber, Principal principal) {
		if (patientNumber != null) {
			Patient result = patientService.findPatientByPatientNumber(patientNumber);
			// if the patient is found, we proceed with admission
			if (result != null) {
				model.addAttribute("patient", result);
				boolean patientresult = true;
				model.addAttribute("found", patientresult);
				AdmissionInfo admission = new AdmissionInfo();
				model.addAttribute("admission", admission);
				System.out.println("We reach this page");
				return "admit";
			}
			// If not found, go to registration page to register a patient first
			Patient patient = new Patient();
			model.addAttribute("patient", patient);
			return "registration";
		}
		return "reception";

	}
	@GetMapping("/details/patient/{patientNumber}")
	public String showInformation(Model model, @PathVariable String patientNumber, Principal principal) {
		if (patientNumber != null) {
			Patient result = patientService.findPatientByPatientNumber(patientNumber);
			// if the patient is found, we proceed with admission
			if (result != null) {
				boolean patientInfo =true;
				model.addAttribute("patientInfo",patientInfo);
				model.addAttribute("patient", result);
		return "reception";
			}
		}
			
			return "redirect:/";
		
	}
	@GetMapping("/details/admission/{patientNumber}")
	public String showAdnissionInformation(Model model, @PathVariable String patientNumber, Principal principal) {
		String username = principal.getName();
		Doctor doctor = userService.findDoctor(username);
		Hospital hospital = doctor.getHospital();
		String hospitalName = hospital.getHospitalName();
		if (patientNumber != null) {
			long admissionNumber = admissionService.countAdmissionBypatient(patientNumber,hospitalName);
			boolean admissionInfo =true;
			List<AdmissionInfo> results =admissionService.listAdmissionInfosByPatients(patientNumber,hospitalName);
			model.addAttribute("admissionList", results);
			model.addAttribute("admissionInfo",admissionInfo);
			model.addAttribute("admissionNumber",admissionNumber);
		return "reception";
			}
		
			
			return "redirect:/";
		
	}
	/**
	 * @return
	 */
	private String generateTrackingNumber() {
		return RandomStringUtils.randomNumeric(10).toUpperCase();
	}
}
