
package rw.ehealth.controller;

import java.security.Principal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.Consultation;
import rw.ehealth.model.Doctor;
import rw.ehealth.model.ExamRecords;
import rw.ehealth.model.Exams;
import rw.ehealth.model.Patient;
import rw.ehealth.service.admission.IAdmissionService;
import rw.ehealth.service.medical.ExamService;
import rw.ehealth.service.medical.IconsultationService;
import rw.ehealth.service.medical.IexamRecordService;
import rw.ehealth.service.patient.PatientService;
import rw.ehealth.service.user.IUserService;
import rw.ehealth.utils.ConsultationDto;
import rw.ehealth.utils.ExamDto;

@Controller
public class HospitalController {

	@Autowired
	private IAdmissionService admissionService;

	@Autowired
	private IUserService userService;
	@Autowired
	private ExamService examService;
	@Autowired
	private IconsultationService consultationService;
	@Autowired
	private IexamRecordService examRecordRepo;

	@GetMapping("/admissions")
	public String getActiveAdmissions(Model model, Principal principal) {

		Doctor activeUser = userService.findDoctor(principal.getName());
		List<AdmissionInfo> admissionList = admissionService
				.allAdmissionsPerHospital(activeUser.getHospital().getHospitalId(), true);
		long admissionSize = admissionService.countAdmission(activeUser.getHospital().getHospitalId(), true);
		model.addAttribute("admissionList", admissionList);
		model.addAttribute("admissionSize", admissionSize);
		boolean admissionInfo = false;
		model.addAttribute("admissionInfo", admissionInfo);

		return "admissions";
	}

	@GetMapping("/details/admissions/{patientTrackingNumber}")
	public String showAdnissionInformation(Model model, @PathVariable String patientTrackingNumber,
			Principal principal) {

		if (patientTrackingNumber != null) {
			boolean admissionInfo = true;
			AdmissionInfo results = admissionService.findByPatientTruckingNumber(patientTrackingNumber);
			model.addAttribute("admission", results);
			model.addAttribute("admissionInfo", admissionInfo);

			return "admissions";
		}

		return "redirect:/";

	}

	@GetMapping("/consultation/{patientTrackingNumber}")
	public String consultation(Model model, @PathVariable String patientTrackingNumber, Principal principal) {
		Doctor activeUser = userService.findDoctor(principal.getName());

		if (patientTrackingNumber != null) {
			boolean admissionInfo = true;
			AdmissionInfo results = admissionService.findByPatientTruckingNumber(patientTrackingNumber);
			String department = activeUser.getDepertment();
			Consultation consultation = new Consultation();
			model.addAttribute("department", department);
			model.addAttribute("consultation", consultation);
			model.addAttribute("admission", results);
			model.addAttribute("admissionInfo", admissionInfo);

			return "consult";
		}

		return "redirect:/";

	}

	@PostMapping("/patient/consultation")
	public String pConsulatation(Model model, @ModelAttribute ConsultationDto consultationDto, Principal principal) {
		Doctor activeUser = userService.findDoctor(principal.getName());
		AdmissionInfo admit = admissionService.findByPatientTruckingNumber(consultationDto.getPatientTrackingNumber());
		Consultation consultation = new Consultation();

		consultation.setDescription(consultationDto.getDescription());
		consultation.setDoctor(activeUser);
		consultation.setAdmissionInfo(admit);
		Consultation consultated = consultationService.createConsultation(consultation);
		if (consultated != null) {
			boolean admissionInfo = true;
			AdmissionInfo results = admissionService
					.findByPatientTruckingNumber(consultationDto.getPatientTrackingNumber());
			String department = activeUser.getDepertment();
			model.addAttribute("department", department);
			model.addAttribute("consultation", consultation);
			model.addAttribute("admission", results);
			model.addAttribute("admissionInfo", admissionInfo);
			return "consult";
		}

		return "redirect:/";

	}

	@GetMapping("/details/consultation/{patientTrackingNumber}")
	public String viewPatientInfo(Model model, @PathVariable String patientTrackingNumber, Principal principal) {
		Doctor activeUser = userService.findDoctor(principal.getName());
		AdmissionInfo admitedP = admissionService.findByPatientTruckingNumber(patientTrackingNumber);
		Patient patient = admitedP.getAdmittedPatient();
		if (patientTrackingNumber.isEmpty() == false) {
			boolean admissionInfo = true;
			List<Consultation> results = consultationService.findAllInfoByPatient(patient.getPatientNumber());
			String department = activeUser.getDepertment();
			Consultation consultation = new Consultation();
			model.addAttribute("department", department);
			model.addAttribute("consultation", consultation);
			model.addAttribute("docAdmissions", results);
			model.addAttribute("admissionInfo", admissionInfo);

			return "information";
		}

		return "redirect:/";

	}

	@GetMapping("/consultation/sendToLabo/{patientTrackingNumber}")
	public String sendToLabo(Model model, @PathVariable String patientTrackingNumber, Principal principal) {
		Doctor activeUser = userService.findDoctor(principal.getName());
		AdmissionInfo results = admissionService.findByPatientTruckingNumber(patientTrackingNumber);
		if (patientTrackingNumber.isEmpty() == false) {
			boolean labo = true;
			model.addAttribute("labo", labo);
			boolean admissionInfo = true;
			String department = activeUser.getDepertment();
			Consultation consultation = new Consultation();
			ExamRecords examRecord = new ExamRecords();
			model.addAttribute("examRecord", examRecord);
			model.addAttribute("department", department);
			model.addAttribute("admission", results);
			model.addAttribute("consultation", consultation);
			model.addAttribute("admissionInfo", admissionInfo);
			model.addAttribute("examss", examService.findExams());

			return "consult";
		}

		return "redirect:/";

	}

	@PostMapping("/sendLabo")
	public String sendExamString(@RequestParam(value = "examId", required = false) int[] examId,
			@ModelAttribute ExamDto examDto, Model model) {
		if (examId != null) {
			List<Exams> selectedExams = new ArrayList<Exams>();
			for (int i = 0; i < examId.length; i++) {
				Exams exam = examService.findHospitalById((long) examId[i]);

				selectedExams.add(exam);
			}
			// Do whatever you want with these hospitals from UI(eg:printing)
			for (Exams exam : selectedExams) {
				System.out.println(exam.getName() + " This came from the UI");
				exam.getExamId();
				ExamRecords examrecords = new ExamRecords();
				AdmissionInfo admitedp = admissionService
						.findByPatientTruckingNumber(examDto.getPatientTrackingNumber());
				examrecords.setExams(exam);
				examrecords.setAdmissionInfo(admitedp);
				examrecords.setDatetaken(LocalDate.now().toString());
				examRecordRepo.creaExamRecords(examrecords);
			}

		}
		return "redirect:/gdoctor";
	}
}
