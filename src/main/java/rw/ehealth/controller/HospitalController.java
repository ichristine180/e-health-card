
package rw.ehealth.controller;

import java.security.Principal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import rw.ehealth.model.PatientRecordsViewHistory;
import rw.ehealth.model.Prescription;
import rw.ehealth.model.Request;
import rw.ehealth.service.admission.IAdmissionService;
import rw.ehealth.service.medical.ExamService;
import rw.ehealth.service.medical.IconsultationService;
import rw.ehealth.service.medical.IexamRecordService;
import rw.ehealth.service.medical.PatientRecordsViewHistoryService;
import rw.ehealth.service.medical.PrescriptionService;
import rw.ehealth.service.medical.RequestService;
import rw.ehealth.service.user.IUserService;
import rw.ehealth.utils.ConsultationDto;
import rw.ehealth.utils.ExamDto;
import rw.ehealth.utils.ExamRecordsDto;
import rw.ehealth.utils.PrescriptionsDto;

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
	private IexamRecordService examRecordService;
	@Autowired
	private PrescriptionService prescriptionService;
	@Autowired
	private RequestService rService;
	@Autowired
	private PatientRecordsViewHistoryService vService;

	@GetMapping("/admissions")
	public String getActiveAdmissions(Model model, Principal principal) {

		Doctor activeUser = userService.findDoctor(principal.getName());
		List<AdmissionInfo> admissionList = admissionService
				.allAdmissionsPerHospital(activeUser.getHospital().getHospitalId());
		long admissionSize = admissionService.countAdmission(activeUser.getHospital().getHospitalId());
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
			String department = activeUser.getDepertment().getName();
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
		consultation.setHospital(activeUser.getHospital());
		Consultation consultated = consultationService.createConsultation(consultation);
		if (consultated != null) {
			boolean admissionInfo = true;
			AdmissionInfo results = admissionService
					.findByPatientTruckingNumber(consultationDto.getPatientTrackingNumber());
			String department = activeUser.getDepertment().getName();
			model.addAttribute("department", department);
			model.addAttribute("consultation", consultation);
			model.addAttribute("admission", results);
			model.addAttribute("admissionInfo", admissionInfo);
			return "consult";
		}

		return "redirect:/gdoctor";

	}

	@GetMapping("/details/consultation/{patientTrackingNumber}")
	public String viewPatientInfo(Model model, @PathVariable String patientTrackingNumber, Principal principal) {
		Doctor activeUser = userService.findDoctor(principal.getName());
		AdmissionInfo admitedP = admissionService.findByPatientTruckingNumber(patientTrackingNumber);
		Patient patient = admitedP.getAdmittedPatient();
		if (patientTrackingNumber.isEmpty() == false) {
			boolean admissionInfo = true;
			Request resultRequest = rService.findPRequest(patient.getPatientNumber(), LocalDate.now().toString());
			if (resultRequest != null) {
				List<Consultation> results = consultationService.findAllInfoByPatient(patient.getPatientNumber());
				if (results != null) {
					PatientRecordsViewHistory vHistory = new PatientRecordsViewHistory();
					vHistory.setDoctor(activeUser);
					vHistory.setPatient(patient);
					vHistory.setHospital(activeUser.getHospital());
					vHistory.setDate(LocalDate.now().toString());
					vService.create(vHistory);
					String department = activeUser.getDepertment().getName();
					Consultation consultation = new Consultation();
					model.addAttribute("department", department);
					model.addAttribute("consultation", consultation);
					model.addAttribute("docAdmissions", results);
					model.addAttribute("admissionInfo", admissionInfo);

					return "information";
				}
			}
			Request request = new Request();
			request.setPatient(patient);
			request.setDoctor(activeUser);
			request.setStatus("PENDING");
			request.setRequestDate(LocalDate.now().toString());
			rService.createRequest(request);
			return "information";

		}
		return "redirect:/gdoctor";
	}

	@GetMapping("/consultation/sendToLabo/{patientTrackingNumber}")
	public String sendToLabo(Model model, @PathVariable String patientTrackingNumber, Principal principal) {
		Doctor activeUser = userService.findDoctor(principal.getName());
		AdmissionInfo results = admissionService.findByPatientTruckingNumber(patientTrackingNumber);
		if (patientTrackingNumber.isEmpty() == false) {
			boolean labo = true;
			model.addAttribute("labo", labo);
			boolean admissionInfo = true;
			String department = activeUser.getDepertment().getName();
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

		return "redirect:/gdoctor";

	}

	@GetMapping("/prescription/{patientTrackingNumber}")
	public String prescription(Model model, @PathVariable String patientTrackingNumber, Principal principal) {
		Doctor activeUser = userService.findDoctor(principal.getName());
		AdmissionInfo results = admissionService.findByPatientTruckingNumber(patientTrackingNumber);
		if (patientTrackingNumber.isEmpty() == false) {
			boolean prescription = true;
			model.addAttribute("prescription", prescription);
			boolean admissionInfo = true;
			String department = activeUser.getDepertment().getName();

			Prescription prescriptions = new Prescription();
			model.addAttribute("prescriptions", prescriptions);
			model.addAttribute("patientTrackingNumber", patientTrackingNumber);
			model.addAttribute("department", department);
			model.addAttribute("admission", results);
			model.addAttribute("admissionInfo", admissionInfo);
			model.addAttribute("examss", examRecordService.findErecords(patientTrackingNumber));

			return "consult";
		}

		return "redirect:/gdoctor";

	}

	@PostMapping("/patient/prescription")
	public String pPrescriptions(Model model, @ModelAttribute PrescriptionsDto prescriptionsDto, Principal principal) {
		Doctor activeUser = userService.findDoctor(principal.getName());
		AdmissionInfo admit = admissionService.findByPatientTruckingNumber(prescriptionsDto.getPatientTrackingNumber());
		Prescription prescriptions = new Prescription();
		prescriptions.setDescription(prescriptionsDto.getDescription());
		prescriptions.setName(prescriptionsDto.getName());
		prescriptions.setDoctor(activeUser);
		prescriptions.setAdmissionInfo(admit);
		prescriptions.setHospital(activeUser.getHospital());
		Prescription pres = prescriptionService.createPrescription(prescriptions);
		if (pres != null) {

			return "redirect:/gdoctor";
		}
		boolean admissionInfo = true;
		AdmissionInfo results = admissionService
				.findByPatientTruckingNumber(prescriptionsDto.getPatientTrackingNumber());
		String department = activeUser.getDepertment().getName();
		model.addAttribute("department", department);
		model.addAttribute("admission", results);
		model.addAttribute("admissionInfo", admissionInfo);
		return "consult";

	}

	@PostMapping("/sendLabo")
	public String sendExamString(@RequestParam(value = "examId", required = false) int[] examId,
			@ModelAttribute ExamDto examDto, Model model, Principal principal) {
		Doctor activeUser = userService.findDoctor(principal.getName());
		if (examId != null) {
			List<Exams> selectedExams = new ArrayList<Exams>();
			for (int i = 0; i < examId.length; i++) {
				Exams exam = examService.findHospitalById(examId[i]);

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
				examrecords.setHospital(activeUser.getHospital());
				examRecordService.creaExamRecords(examrecords);
			}

		}
		return "redirect:/gdoctor";
	}

	@GetMapping("/labo/{patientTrackingNumber}")
	public String viewExam(Model model, @PathVariable String patientTrackingNumber, Principal principal) {
		if (patientTrackingNumber.isEmpty() == false) {
			ExamRecords examRecord = new ExamRecords();
			model.addAttribute("examRecord", examRecord);
			model.addAttribute("examss", examRecordService.findErecords(patientTrackingNumber));
			model.addAttribute("patientTrackingNumber", patientTrackingNumber);

			return "labo";
		}

		return "redirect:/labodoctor";

	}

	@PostMapping("/results")
	public String saveResults(@RequestParam(value = "id", required = false) int[] id,
			@RequestParam(value = "results", required = true) String[] results, @ModelAttribute ExamRecordsDto examDto,
			Model model, Principal principal) {
		for (int i = 0; i < id.length; i++) {
			ExamRecords records = examRecordService.findExamRecordByExamId(id[i]);
			System.out.println(records.toString() + " not updated");
			records.setResults(results[i]);
			ExamRecords savedWithResults = examRecordService.update(records);
			System.out.println(savedWithResults.toString() + " ");
		}
		return "redirect:/labodoctor";
	}
}
