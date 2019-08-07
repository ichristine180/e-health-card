
package rw.ehealth.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.Doctor;
import rw.ehealth.report.AdmissionReport;
import rw.ehealth.service.admission.IAdmissionService;
import rw.ehealth.service.user.UserService;

@Controller
public class ReportController {

	@Autowired
	private IAdmissionService admissionService;

	@Autowired
	private UserService userService;

	@GetMapping("/reception/report")
	public String Receptionist(Model model, Principal principal) {
		String username = principal.getName();
		Doctor doctor = userService.findDoctor(username);
		List<AdmissionReport> admissions = admissionService.findBydoctor(doctor.getEmail());
		model.addAttribute("doctor", doctor);
		model.addAttribute("admission", admissions);
		return "report";
	}

	@GetMapping("/reception/reports/male")
	public String getAdmissionByGender(Model model, Principal principal) {
		String username = principal.getName();
		Doctor doctor = userService.findDoctor(username);
		List<AdmissionReport> results = admissionService.findByGender("MALE",doctor.getHospital().getHospitalId());
		model.addAttribute("maleAdmision", results);
		return "report";
	}
	@GetMapping("/reception/reports/female")
	public String getAdmissionByFemale(Model model, Principal principal) {
		String username = principal.getName();
		Doctor doctor = userService.findDoctor(username);
		List<AdmissionReport> results = admissionService.findByGender("FEMALE",doctor.getHospital().getHospitalId());
		model.addAttribute("femaleAdmision", results);
		return "report";
	}

	@GetMapping("reception/reports/month")
	public String getAdmisionsReport(Model model, Principal principal)  {
		String username = principal.getName();
		Doctor doctor = userService.findDoctor(username);
		List<AdmissionInfo> results = admissionService.getAdmissionsPerMonth(7);
		model.addAttribute("monthAdmision", results);
		return "report";
	}
}
