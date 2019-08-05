
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
		List<AdmissionInfo> admissions = admissionService.findBydoctor(doctor.getEmail());
		model.addAttribute("doctor", doctor);
		model.addAttribute("admission", admissions);
		return "report";
	}

	@GetMapping("/reception/report/male")
	public String getAdmissionByGender(Model model, Principal principal) {
		String username = principal.getName();
		Doctor doctor = userService.findDoctor(username);
		boolean gender = true;
		model.addAttribute("gender", gender);
		List<AdmissionInfo> admissions = admissionService.findByGender("MALE");
		model.addAttribute("doctor", doctor);
		model.addAttribute("maleAdmision", admissions);
		return "report";
	}

	@PostMapping("/report/admission/month")
	public ResponseEntity<List<AdmissionInfo>> getAdmisionsReport(@RequestParam int month) {
		System.out.println(month + " the month we're looking for");
		List<AdmissionInfo> results = admissionService.getAdmissionsPerMonth(month);
		return new ResponseEntity<>(results, HttpStatus.OK);

	}
}
