<<<<<<< HEAD

package rw.ehealth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.service.admission.IAdmissionService;

@RestController
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private IAdmissionService admissionService;

	@PostMapping("/admission/month")
	public ResponseEntity<List<AdmissionInfo>> getAdmisionsReport(@RequestParam int month) {
		System.out.println(month + " the month we're looking for");
		List<AdmissionInfo> results = admissionService.getAdmissionsPerMonth(month);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}

=======
package rw.ehealth.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.Doctor;
import rw.ehealth.service.admission.AdmissionService;
import rw.ehealth.service.medical.HospitalService;
import rw.ehealth.service.patient.PatientService;
import rw.ehealth.service.user.UserService;

@Controller
public class ReportController {
	@Autowired
	private UserService userService;
	@Autowired
	private HospitalService hospitalService;
	@Autowired
	private PatientService patientservice;
	@Autowired
	private AdmissionService admissionService;
	@GetMapping("/reception/report")
	public String Receptionist(Model model, Principal principal) {
		String username = principal.getName();
		Doctor doctor = userService.findDoctor(username);
		List<AdmissionInfo> admissions = admissionService.findBydoctor(doctor.getEmail());
		model.addAttribute("doctor", doctor);
		model.addAttribute("admission", admissions);
		return "report";
	}
	@GetMapping("/reception/reports/male")
	public String getAdmissionByGender(Model model, Principal principal) {
		String username = principal.getName();
		Doctor doctor = userService.findDoctor(username);
		boolean gender = true;
		model.addAttribute("gender",gender);
		List<AdmissionInfo> admissions = admissionService.findByGender("MALE");
		model.addAttribute("doctor", doctor);
		model.addAttribute("maleAdmision", admissions);
		return "report";
	}
>>>>>>> dc87a1d13edcf3ef8b928bfeea08f0964ecd2f95
}
