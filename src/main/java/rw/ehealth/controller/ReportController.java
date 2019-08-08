
package rw.ehealth.controller;

import java.security.Principal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.Doctor;
import rw.ehealth.report.AdmissionReport;
import rw.ehealth.report.ExamReport;
import rw.ehealth.service.admission.IAdmissionService;
import rw.ehealth.service.medical.IconsultationService;
import rw.ehealth.service.medical.IexamRecordService;
import rw.ehealth.service.user.UserService;

@Controller
public class ReportController {

	@Autowired
	private IAdmissionService admissionService;
	@Autowired
	private IconsultationService cService;
	@Autowired
	private IexamRecordService examRecordService;
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

	@GetMapping("/consultation/report")
	public String gDoctor(Model model, Principal principal) {
		String username = principal.getName();
		Doctor doctor = userService.findDoctor(username);
		Long id = doctor.getHospital().getHospitalId();
		Long malePatients = cService.countPatientByGender(id, "Male");
		Long femalePatients = cService.countPatientByGender(id, "FEMale");
		List<ExamReport> results = examRecordService.countByExamName(id);
		model.addAttribute("exam", results);
		model.addAttribute("male", malePatients);
		model.addAttribute("female", femalePatients);
		return "report";
	}
	@GetMapping("/report/labo")
	public String laboreport(Model model, Principal principal) {
		String username = principal.getName();
		Doctor doctor = userService.findDoctor(username);
		Long id = doctor.getHospital().getHospitalId();
		Long patientExam = examRecordService.countPatient(id);
		List<ExamReport> results = examRecordService.countByExamName(id);
		model.addAttribute("patientExam", patientExam);
		model.addAttribute("exam", results);
		return "report";
	}

	@GetMapping("/reception/reports/male")
	public String getAdmissionByGender(Model model, Principal principal) {
		String username = principal.getName();
		Doctor doctor = userService.findDoctor(username);
		List<AdmissionReport> results = admissionService.findByGender("MALE", doctor.getHospital().getHospitalId());
		model.addAttribute("maleAdmision", results);
		return "report";
	}

	@GetMapping("/reception/reports/female")
	public String getAdmissionByFemale(Model model, Principal principal) {
		String username = principal.getName();
		Doctor doctor = userService.findDoctor(username);
		List<AdmissionReport> results = admissionService.findByGender("FEMALE", doctor.getHospital().getHospitalId());
		model.addAttribute("femaleAdmision", results);
		return "report";
	}

	@GetMapping("reception/reports/month")
	public String getAdmisionsReport(Model model, Principal principal) {
		String username = principal.getName();
		Doctor doctor = userService.findDoctor(username);
		List<AdmissionInfo> results = admissionService.getAdmissionsPerMonth(7);
		model.addAttribute("monthAdmision", results);
		return "report";
	}
}
