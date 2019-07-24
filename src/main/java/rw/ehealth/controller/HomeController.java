
package rw.ehealth.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import rw.ehealth.model.Doctor;
import rw.ehealth.service.admission.AdmissionService;
import rw.ehealth.service.medical.HospitalService;
import rw.ehealth.service.patient.PatientService;
import rw.ehealth.service.user.UserService;
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

	@GetMapping("/")
	public String homepage(Model model) {
			long doctorsize = userService.countDoctor();
			long hospitals = hospitalService.countHospital();
			model.addAttribute("doctorsize", doctorsize);
			model.addAttribute("hospitals", hospitals);
			model.addAttribute("doctors", userService.finDoctors());
		return "homepage";
	}

	@GetMapping("/recptionist")
	public String Receptionist(Model model, Principal principal) {
		String username = principal.getName();
		long patients = patientservice.countPatient();
		Doctor activeUser = userService.findDoctor(principal.getName());
		model.addAttribute("patientsSize", patients);
		Doctor doctor = userService.findDoctor(username);
		Long hospitalId = doctor.getHospital().getHospitalId();
		model.addAttribute("admission", admissionService.allAdmissionsPerHospital(hospitalId, true));
		long admissions = admissionService.countAdmission(activeUser.getHospital().getHospitalId(), true);
		model.addAttribute("admissions", admissions);
		// Load all patients for easy access to admission
		model.addAttribute("patients", patientservice.findAll());
		String department = activeUser.getDepertment();
		model.addAttribute("department", department);
		model.addAttribute("docAdmissions", admissionService.AdmissionInfos(activeUser.getHospital().getHospitalId(),
				true, activeUser.getDepertment()));

		return "homepage";
	}
	@GetMapping("/gdoctor")
	public String gDoctor(Model model, Principal principal) {
		String username = principal.getName();
		Doctor activeUser = userService.findDoctor(principal.getName());
		Doctor doctor = userService.findDoctor(username);
		Long hospitalId = doctor.getHospital().getHospitalId();
		model.addAttribute("admission", admissionService.allAdmissionsPerHospital(hospitalId, true));
		String department = activeUser.getDepertment();
		model.addAttribute("department", department);
		model.addAttribute("docAdmissions", admissionService.AdmissionInfos(activeUser.getHospital().getHospitalId(),
				true, activeUser.getDepertment()));

		return "homepage";
	}
	@GetMapping("/labodoctor")
	public String laboString(Model model, Principal principal) {

		return "homepage";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

}
