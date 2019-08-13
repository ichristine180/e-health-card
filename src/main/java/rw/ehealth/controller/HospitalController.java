
package rw.ehealth.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

import rw.ehealth.enums.EViewRequestStatus;
import rw.ehealth.model.Admission;
import rw.ehealth.model.Consultation;
import rw.ehealth.model.Employee;
import rw.ehealth.model.ExamRecord;
import rw.ehealth.model.MedicalExam;
import rw.ehealth.model.Patient;
import rw.ehealth.model.Prescription;
import rw.ehealth.model.RecordHistoryLog;
import rw.ehealth.model.ViewRecordRequest;
import rw.ehealth.service.admission.IAdmissionService;
import rw.ehealth.service.consultation.IConsultationService;
import rw.ehealth.service.exams.ExamService;
import rw.ehealth.service.exams.IExamRecordService;
import rw.ehealth.service.medical.IViewRecordHistoryService;
import rw.ehealth.service.medical.IViewRequestService;
import rw.ehealth.service.prescription.PrescriptionService;
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
	private IConsultationService consultationService;

	@Autowired
	private IExamRecordService examRecordService;

	@Autowired
	private PrescriptionService prescriptionService;

	@Autowired
	private IViewRequestService rService;

	@Autowired
	private IViewRecordHistoryService vService;

	@GetMapping("/admissions")
	public String getActiveAdmissions(Model model, Principal principal) {

		Employee activeUser = userService.findDoctor(principal.getName());
		List<Admission> admissionList = admissionService
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
			Admission results = admissionService.findByPatientTruckingNumber(patientTrackingNumber);
			model.addAttribute("admission", results);
			model.addAttribute("admissionInfo", admissionInfo);

			return "admissions";
		}

		return "redirect:/";

	}

	@GetMapping("/consultation/{patientTrackingNumber}")
	public String consultation(Model model, @PathVariable String patientTrackingNumber, Principal principal) {
		Employee activeUser = userService.findDoctor(principal.getName());

		if (patientTrackingNumber != null) {
			boolean admissionInfo = true;
			Admission results = admissionService.findByPatientTruckingNumber(patientTrackingNumber);
			String department = activeUser.getDepertment().getName();
			Consultation consultation = new Consultation();
			model.addAttribute("department", department);
			model.addAttribute("consultation", consultation);
			model.addAttribute("admission", results);
			model.addAttribute("admissionInfo", admissionInfo);

			return "consult";
		}

		return "redirect:/gdoctor";

	}

	@GetMapping("/details/{patientTrackingNumber}")
	public String consultationdetails(Model model, @PathVariable String patientTrackingNumber, Principal principal) {
		Employee activeUser = userService.findDoctor(principal.getName());

		if (patientTrackingNumber != null) {
			boolean admissionInfo = true;
			Admission results = admissionService.findByPatientTruckingNumber(patientTrackingNumber);
			String department = activeUser.getDepertment().getName();
			Consultation consultation = consultationService.findByPatientTruckingNumber(patientTrackingNumber);
			model.addAttribute("department", department);
			model.addAttribute("consultation", consultation);
			model.addAttribute("admission", results);
			model.addAttribute("admissionInfo", admissionInfo);

			return "consultationD";
		}

		return "redirect:/gdoctor";

	}

	@PostMapping("/patient/consultation")
	public String pConsulatation(Model model, @ModelAttribute ConsultationDto consultationDto, Principal principal) {
		Employee activeUser = userService.findDoctor(principal.getName());
		Admission admit = admissionService.findByPatientTruckingNumber(consultationDto.getPatientTrackingNumber());
		Consultation consultation = new Consultation();

		consultation.setDescription(consultationDto.getDescription());
		consultation.setDoctor(activeUser);
		consultation.setAdmission(admit);
		consultation.setHospital(activeUser.getHospital());
		Consultation consultated = consultationService.createConsultation(consultation);
		if (consultated != null) {
			boolean admissionInfo = true;
			Admission results = admissionService
					.findByPatientTruckingNumber(consultationDto.getPatientTrackingNumber());
			results.setStatus("MIDLE");
			admissionService.update(results);
			String department = activeUser.getDepertment().getName();
			model.addAttribute("department", department);
			model.addAttribute("consultation", consultation);
			model.addAttribute("admission", results);
			model.addAttribute("admissionInfo", admissionInfo);
			model.addAttribute("message", "The Consultation Successed!");
			return "consult";
		} else {
			boolean admissionInfo = true;
			Admission results = admissionService
					.findByPatientTruckingNumber(consultationDto.getPatientTrackingNumber());
			String department = activeUser.getDepertment().getName();
			model.addAttribute("department", department);
			model.addAttribute("consultation", consultation);
			model.addAttribute("admission", results);
			model.addAttribute("admissionInfo", admissionInfo);
			model.addAttribute("message", "Consultation Failed! Try Again");
			return "consult";
		}

	}

	@GetMapping("/details/consultation/{patientTrackingNumber}")
	public String viewPatientInfo(Model model, @PathVariable String patientTrackingNumber, Principal principal) {
		Employee activeUser = userService.findDoctor(principal.getName());
		Admission admitedP = admissionService.findByPatientTruckingNumber(patientTrackingNumber);
		Patient patient = admitedP.getAdmittedPatient();
		if (patientTrackingNumber.isEmpty() == false) {
			boolean admissionInfo = true;
			ViewRecordRequest resultRequest = rService.findPRequest(patient.getPatientNumber(),
					LocalDate.now().toString());
			if (resultRequest != null) {
				List<Consultation> results = consultationService.findAllInfoByPatient(patient.getPatientNumber());
				if (results != null) {
					RecordHistoryLog vHistory = new RecordHistoryLog();
					vHistory.setViewer(activeUser);
					vHistory.setPatient(patient);
					vHistory.setHospital(activeUser.getHospital());
					vHistory.setViewOn(LocalDateTime.now());
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
			ViewRecordRequest request = new ViewRecordRequest();
			request.setPatient(patient);
			request.setRequestedBy(activeUser);
			request.setRequestStatus(EViewRequestStatus.PENDING);
			request.setActive(true);
			request.setRequestDate(LocalDate.now().toString());
			rService.createRequest(request);
			return "information";

		}
		return "redirect:/gdoctor";
	}

	@GetMapping("/consultation/sendToLabo/{patientTrackingNumber}")
	public String sendToLabo(Model model, @PathVariable String patientTrackingNumber, Principal principal) {
		Employee activeUser = userService.findDoctor(principal.getName());
		Admission results = admissionService.findByPatientTruckingNumber(patientTrackingNumber);
		if (patientTrackingNumber.isEmpty() == false) {
			Consultation consult = consultationService.findByPatientTruckingNumber(patientTrackingNumber);
			if (consult != null) {
				String department = activeUser.getDepertment().getName();
				ExamRecord examRecord = new ExamRecord();
				model.addAttribute("examRecord", examRecord);
				model.addAttribute("department", department);
				model.addAttribute("admission", results);
				model.addAttribute("examss", examService.findExams());
				model.addAttribute("department", department);
				model.addAttribute("consultation", consult);
				model.addAttribute("admission", results);
				return "consultationD";
			}
		}
		String department = activeUser.getDepertment().getName();
		model.addAttribute("department", department);
		model.addAttribute("admission", results);
		boolean admissionInfo = true;
		Consultation consultation = new Consultation();
		model.addAttribute("department", department);
		model.addAttribute("consultation", consultation);
		model.addAttribute("admissionInfo", admissionInfo);
		model.addAttribute("message", "Consultate First!");
		return "consult";

	}

	@GetMapping("/prescription/{patientTrackingNumber}")
	public String prescription(Model model, @PathVariable String patientTrackingNumber, Principal principal) {
		Employee activeUser = userService.findDoctor(principal.getName());
		Admission results = admissionService.findByPatientTruckingNumber(patientTrackingNumber);
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
		Employee activeUser = userService.findDoctor(principal.getName());
		Admission admit = admissionService.findByPatientTruckingNumber(prescriptionsDto.getPatientTrackingNumber());
		Prescription prescriptions = new Prescription();
		prescriptions.setDescription(prescriptionsDto.getDescription());
		prescriptions.setName(prescriptionsDto.getName());
		prescriptions.setPrescribedBy(activeUser);
		prescriptions.setAdmission(admit);
		prescriptions.setHospital(activeUser.getHospital());
		Prescription pres = prescriptionService.createPrescription(prescriptions);
		if (pres != null) {

			return "redirect:/gdoctor";
		}
		boolean admissionInfo = true;
		Admission results = admissionService.findByPatientTruckingNumber(prescriptionsDto.getPatientTrackingNumber());
		String department = activeUser.getDepertment().getName();
		model.addAttribute("department", department);
		model.addAttribute("admission", results);
		model.addAttribute("admissionInfo", admissionInfo);
		return "consult";

	}

	@PostMapping("/sendLabo")
	public String sendExamString(@RequestParam(value = "examId", required = false) int[] examId,
			@ModelAttribute ExamDto examDto, Model model, Principal principal) {
		Employee activeUser = userService.findDoctor(principal.getName());

		if (examId != null) {
			List<MedicalExam> selectedMedicalExam = new ArrayList<MedicalExam>();
			for (int i = 0; i < examId.length; i++) {
				MedicalExam exam = examService.findHospitalById(examId[i]);
				selectedMedicalExam.add(exam);
			} // Do whatever you want with these hospitals from UI(eg:printing)
			for (MedicalExam exam : selectedMedicalExam) {
				System.out.println(exam.getName() + " This came from the UI");
				exam.getExamId();
				ExamRecord examrecords = new ExamRecord();
				Admission admitedp = admissionService.findByPatientTruckingNumber(examDto.getPatientTrackingNumber());
				examrecords.setMedicalExam(exam);
				examrecords.setAdmissionInfo(admitedp);
				examrecords.setDatetaken(LocalDate.now().toString());
				examrecords.setHospital(activeUser.getHospital());
				examRecordService.creaExamRecords(examrecords);
			}

			return "consultationD";
		}
		return "redirect:/gdoctor";
	}

	@GetMapping("/labo/{patientTrackingNumber}")
	public String viewExam(Model model, @PathVariable String patientTrackingNumber, Principal principal) {
		if (patientTrackingNumber.isEmpty() == false) {
			ExamRecord examRecord = new ExamRecord();
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
			ExamRecord records = examRecordService.findExamRecordByExamId(id[i]);
			System.out.println(records.toString() + " not updated");
			records.setResults(results[i]);
			ExamRecord savedWithResults = examRecordService.update(records);
			System.out.println(savedWithResults.toString() + " ");
		}
		return "redirect:/labodoctor";
	}
}
