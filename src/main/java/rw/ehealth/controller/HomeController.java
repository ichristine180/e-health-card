
package rw.ehealth.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.Doctor;
import rw.ehealth.model.Hospital;
import rw.ehealth.model.Patient;
import rw.ehealth.model.User;
import rw.ehealth.model.security.Role;
import rw.ehealth.model.security.UserRole;
import rw.ehealth.service.admission.AdmissionService;
import rw.ehealth.service.medical.HospitalService;
import rw.ehealth.service.patient.PatientService;
import rw.ehealth.service.user.UserService;
import rw.ehealth.utils.AdmissionData;
import rw.ehealth.utils.DoctorData;
import rw.ehealth.utils.HopitaData;
import rw.ehealth.utils.PatientData;

@Controller
public class HomeController {
	@Autowired
	private UserService userService;
	@Autowired
	private HospitalService hospitalService;
	@Autowired
	private PatientService patientservice;
	@Autowired
	private AdmissionService admissionService;

	@RequestMapping("/")
	public String homepage(Model model, Principal principal) {
		long doctorsize = userService.countDoctor();
		long hospitals = hospitalService.countHospital();
		long patients = patientservice.countPatient();
		String username = principal.getName();
		long admissions = admissionService.countAdmission(username);
		model.addAttribute("admissions", admissions);
		model.addAttribute("doctorsize", doctorsize);
		model.addAttribute("hospitals", hospitals);
		model.addAttribute("patientsSize", patients);
		Doctor doctor = userService.findDoctor(username);
		if(username!="admin@health.com") {
		String hospitalName = doctor.getHospital().getHospitalName();
		model.addAttribute("admission", admissionService.allAdmissionInfos(hospitalName));
		}
		// Load all patients for easy access to admission
		model.addAttribute("patients", patientservice.findAll());
		model.addAttribute("doctors", userService.finDoctors());
		
		return "homepage";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping(value = "/patient")
	public String Patient(Model model) {
		PatientData newpatient = new PatientData();
		model.addAttribute("patient", newpatient);
		boolean patients = true;
		model.addAttribute("patients", patients);
		return "reception";
	}

	@GetMapping(value = "/admission")
	public String adimssion(Model model) {
		boolean admissions = true;
		model.addAttribute("admissions", admissions);
		AdmissionData admission = new AdmissionData();
		model.addAttribute("admission", admission);
		return "reception";
	}

}
