
package rw.ehealth.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.Doctor;
import rw.ehealth.report.AdmissionReport;
import rw.ehealth.service.admission.IAdmissionService;
import rw.ehealth.service.user.UserService;

@RestController
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

	@GetMapping("/report/reception/report/male")
	public ResponseEntity<List<AdmissionReport>> getAdmissionByGender(Model model, Principal principal) {
		// String username = principal.getName();
		// Doctor doctor = userService.findDoctor(username);
		boolean gender = true;
		model.addAttribute("gender", gender);
		List<AdmissionReport> results = admissionService.findByGender("MALE");
		model.addAttribute("gender", gender);

		// model.addAttribute("doctor", doctor);
		model.addAttribute("maleAdmision", results);

		return new ResponseEntity<>(results, HttpStatus.OK);
		// return "report";
	}

	@GetMapping("/admission/month")
	public String getAdmisionsReport(Model model, Principal principal) {
		List<AdmissionInfo> results = admissionService.getAdmissionsPerMonth(7);
		model.addAttribute("monthadmission", results);
		return "report";
	}

	@PostMapping("/report/admission/month")
	public ResponseEntity<List<AdmissionInfo>> getAdmisionsReport(@RequestParam int month) {
		List<AdmissionInfo> results = admissionService.getAdmissionsPerMonth(month);
		return new ResponseEntity<>(results, HttpStatus.OK);

	}
}
