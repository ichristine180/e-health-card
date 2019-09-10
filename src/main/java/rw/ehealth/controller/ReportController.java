
package rw.ehealth.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import rw.ehealth.model.Admission;
import rw.ehealth.model.Employee;
import rw.ehealth.model.Hospital;
import rw.ehealth.report.AdmissionReport;
import rw.ehealth.report.ExamReport;
import rw.ehealth.service.admission.IAdmissionService;
import rw.ehealth.service.consultation.IConsultationService;
import rw.ehealth.service.exams.IExamRecordService;
import rw.ehealth.service.patient.IPatientService;
import rw.ehealth.service.user.UserService;

@Controller
public class ReportController {

	@Autowired
	private IAdmissionService admissionService;

	@Autowired
	private IConsultationService cService;

	@Autowired
	private IExamRecordService examRecordService;

	@Autowired
	private UserService userService;

	@GetMapping("/reception/report")
	public String Receptionist(Model model, Principal principal) {
		String username = principal.getName();
		Employee doctor = userService.findDoctor(username);
		Hospital hospital = doctor.getHospital();
		List<AdmissionReport> admissions = admissionService.findBydoctor(doctor.getEmail());
		model.addAttribute("admissionCount", admissionService.countAllAdmission(hospital.getHospitalId()).size());
		model.addAttribute("DadmissionCount", admissionService.findBydoctor(doctor.getEmail()).size());
		model.addAttribute("MaleAdmissionCount",admissionService.findByGender("MALE",hospital.getHospitalId()).size());
		model.addAttribute("FeMaleAdmissionCount",admissionService.findByGender("FEMALE",hospital.getHospitalId()).size());
		model.addAttribute("hospitalName", hospital.getHospitalName());
		model.addAttribute("admissions", hospital.getAdmissions());
		model.addAttribute("doctor", doctor);
		model.addAttribute("admission", admissions);
		return "report/ReceptionReport";
	}

	@GetMapping("/consultation/report")
	public String gDoctor(Model model, Principal principal) {
		String username = principal.getName();
		Employee doctor = userService.findDoctor(username);
		Long id = doctor.getHospital().getHospitalId();
		Long malePatients = cService.countPatientByGender(id, "Male");
		Long femalePatients = cService.countPatientByGender(id, "FEMale");
		List<ExamReport> results = examRecordService.countByExamName(id);
		model.addAttribute("exam", results);
		model.addAttribute("male", malePatients);
		Hospital hospital = doctor.getHospital();
		model.addAttribute("hospitalName", hospital.getHospitalName());
		model.addAttribute("female", femalePatients);
		model.addAttribute("allconsultation", cService.countByHospital(hospital));
		model.addAttribute("Dconsultation", cService.findByDoctor(doctor).size());
		return "report/ConsultationReport";
	}

	@GetMapping("/report/labo")
	public String laboreport(Model model, Principal principal) {
		String username = principal.getName();
		Employee doctor = userService.findDoctor(username);
		Long id = doctor.getHospital().getHospitalId();
		List<ExamReport> results = examRecordService.countByExamName(id);
		model.addAttribute("patientExam", examRecordService.countRecievedPatient(id));
		model.addAttribute("DocpatientExam", examRecordService.countRecievedPatientByDoctor(id,doctor.getEmployeeId()));
		model.addAttribute("exam", results);
		return "report/LaboReport";
	}
}
