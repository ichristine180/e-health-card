package rw.ehealth.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.Doctor;
import rw.ehealth.service.admission.AdmissionService;
import rw.ehealth.service.medical.HospitalService;
import rw.ehealth.service.patient.PatientService;
import rw.ehealth.service.user.UserService;
import rw.ehealth.utils.AdmissionReportData;
import rw.ehealth.utils.IndividualReportData;
import rw.ehealth.utils.PinfoListResponse;

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
	//@GetMapping("/reception/report")
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
		IndividualReportData response = new IndividualReportData();
		String username = principal.getName();
		Doctor doctor = userService.findDoctor(username);
		boolean gender = true;
		model.addAttribute("gender",gender);
		List<AdmissionInfo> results = admissionService.findByGender("MALE");
			response.setAdmissionInfos(results);

			List<AdmissionInfo> admission = new ArrayList<AdmissionInfo>();
			admission.addAll(results);
			response.setAdmissionInfos(admission);
		model.addAttribute("doctor", doctor);
		model.addAttribute("maleAdmision", response);
		return "report";
	}
	@GetMapping("/admission/month")
	public String getAdmisionsReport(Model model, Principal principal) {
		List<AdmissionInfo> results = admissionService.getAdmissionsPerMonth(7);
		model.addAttribute("monthadmission",results);
		return  "report";
}
}
