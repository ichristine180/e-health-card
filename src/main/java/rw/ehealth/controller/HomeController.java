
package rw.ehealth.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import rw.ehealth.model.Consultation;
import rw.ehealth.model.Employee;
import rw.ehealth.service.admission.IAdmissionService;
import rw.ehealth.service.consultation.IConsultationService;
import rw.ehealth.service.exams.IExamRecordService;
import rw.ehealth.service.hospital.IHospitalService;
import rw.ehealth.service.patient.IPatientService;
import rw.ehealth.service.user.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private IHospitalService hospitalService;

	@Autowired
	private IPatientService patientservice;

	@Autowired
	private IAdmissionService admissionService;

	@Autowired
	private IExamRecordService examRecordService;
	@Autowired
	private IConsultationService consultationService;

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
		model.addAttribute("patientsSize", patients);
		Employee doctor = userService.findDoctor(username);
		Long hospitalId = doctor.getHospital().getHospitalId();
		model.addAttribute("admission", admissionService.allAdmissionsPerHospital(hospitalId));
		long admissions = admissionService.countAdmission(hospitalId);
		model.addAttribute("admissions", admissions);
		// Load all patients for easy access to admission
		model.addAttribute("patients", patientservice.findAll());

		return "homepage";
	}

	@GetMapping("/gdoctor")
	public String gDoctor(Model model, Principal principal) {
		String username = principal.getName();
		Employee doctor = userService.findDoctor(username);
		Long hospitalId = doctor.getHospital().getHospitalId();
		String department = doctor.getDepertment().getName();
		List<Consultation> consultedp = consultationService.findConsuledPatient(hospitalId,
				doctor.getDepertment().getDepartmentId());
		System.out.println(consultedp.size() + "jfjfjjfjj");
		model.addAttribute("department", department);
		long admissions = admissionService.countAdmissionfodoctor(hospitalId);
		long patients = consultationService.countConsultation(hospitalId,doctor.getDepertment().getDepartmentId());
		model.addAttribute("docAdmissions",
				admissionService.Admissions(hospitalId, doctor.getDepertment().getDepartmentId()));
		model.addAttribute("consultedpatients", consultedp);

		model.addAttribute("patientsSize", patients);
		model.addAttribute("admissions", admissions);
		return "homepage";
	}

	@GetMapping("/showadmission")
	public String shownewadmission(Model model, Principal principal) {
		String username = principal.getName();
		Employee doctor = userService.findDoctor(username);
		Long hospitalId = doctor.getHospital().getHospitalId();
		String department = doctor.getDepertment().getName();
		model.addAttribute("department", department);
		model.addAttribute("docAdmissions",
				admissionService.Admissions(hospitalId, doctor.getDepertment().getDepartmentId()));

		model.addAttribute("docAdmissions",
				admissionService.Admissions(hospitalId, doctor.getDepertment().getDepartmentId()));

		return "consultationD";
	}

	@GetMapping("/consultedpatients")
	public String showConsulted(Model model, Principal principal) {
		String username = principal.getName();
		Employee doctor = userService.findDoctor(username);
		Long hospitalId = doctor.getHospital().getHospitalId();
		String department = doctor.getDepertment().getName();
		model.addAttribute("department", department);
		model.addAttribute("consultedpatients",
				admissionService.midleAdmissions(hospitalId, doctor.getDepertment().getDepartmentId()));

		return "consultationD";
	}

	@GetMapping("/results")
	public String examResults(Model model, Principal principal) {
		String username = principal.getName();
		Employee doctor = userService.findDoctor(username);
		Long hospitalId = doctor.getHospital().getHospitalId();
		String department = doctor.getDepertment().getName();
		model.addAttribute("department", department);
		model.addAttribute("results", examRecordService.findResults(hospitalId));

		return "consultationD";
	}

	@GetMapping("/labodoctor")
	public String laboString(Model model, Principal principal) {
		Employee activeUser = userService.findDoctor(principal.getName());
		String department = activeUser.getDepertment().getName();
		model.addAttribute("department", department);
		long patients = examRecordService.countPatient(activeUser.getHospital().getHospitalId());
		long presults = examRecordService.countresults(activeUser.getHospital().getHospitalId());
		model.addAttribute("pcount",patients);
		model.addAttribute("countresults",presults);
		model.addAttribute("pExam", examRecordService.findActiveExamRecords(activeUser.getHospital()));
		model.addAttribute("presults", examRecordService.findResults(activeUser.getHospital().getHospitalId()));
		return "homepage";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

}
