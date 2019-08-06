<<<<<<< HEAD
package rw.ehealth.controller;

import java.security.Principal;
import java.util.ArrayList;
=======

package rw.ehealth.controller;

import java.security.Principal;
>>>>>>> origin/master
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

>>>>>>> origin/master
import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.Doctor;
import rw.ehealth.service.admission.IAdmissionService;
import rw.ehealth.service.user.UserService;
import rw.ehealth.utils.PinfoListResponse;

@Controller
public class ReportController {
	@Autowired
	private IAdmissionService admissionService;
	@Autowired
<<<<<<< HEAD
	private AdmissionService admissionService;
	//@GetMapping("/reception/report")
=======
	private UserService userService;

	@GetMapping("/reception/report")
>>>>>>> origin/master
	public String Receptionist(Model model, Principal principal) {
		String username = principal.getName();
		Doctor doctor = userService.findDoctor(username);
		List<AdmissionInfo> admissions = admissionService.findBydoctor(doctor.getEmail());
		model.addAttribute("doctor", doctor);
		model.addAttribute("admission", admissions);
		return "report";
	}
<<<<<<< HEAD
	
	@GetMapping("/reception/reports/male")
=======

	@GetMapping("/reception/report/male")
>>>>>>> origin/master
	public String getAdmissionByGender(Model model, Principal principal) {
		String username = principal.getName();
		Doctor doctor = userService.findDoctor(username);
		boolean gender = true;
<<<<<<< HEAD
		model.addAttribute("gender",gender);
		List<AdmissionInfo> results = admissionService.findByGender("MALE");
			
			List<AdmissionInfo> admission = new ArrayList<AdmissionInfo>();
			admission.addAll(results);
			
=======
		model.addAttribute("gender", gender);
		List<AdmissionInfo> admissions = admissionService.findByGender("MALE");
>>>>>>> origin/master
		model.addAttribute("doctor", doctor);
		model.addAttribute("maleAdmision", results);
		return "report";
	}
<<<<<<< HEAD
	@GetMapping("/admission/month")
	public String getAdmisionsReport(Model model, Principal principal) {
		List<AdmissionInfo> results = admissionService.getAdmissionsPerMonth(7);
		model.addAttribute("monthadmission",results);
		return  "report";
}
=======

	@PostMapping("/report/admission/month")
	public ResponseEntity<List<AdmissionInfo>> getAdmisionsReport(@RequestParam int month) {
		System.out.println(month + " the month we're looking for");
		List<AdmissionInfo> results = admissionService.getAdmissionsPerMonth(month);
		return new ResponseEntity<>(results, HttpStatus.OK);

	}
>>>>>>> origin/master
}
