
package rw.ehealth.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import rw.ehealth.service.patient.IPatientService;
import rw.ehealth.service.prescription.PrescriptionService;
import rw.ehealth.service.record.IMedicalRecordService;
import rw.ehealth.service.user.IUserService;
import rw.ehealth.utils.ConsultationDto;
import rw.ehealth.utils.ExamDto;
import rw.ehealth.utils.IDGenerator;
import rw.ehealth.utils.PrescriptionsDto;
import rw.ehealth.utils.report.MedicalRecordUtil;

@Controller
public class HospitalController {

	@Autowired
	private IMedicalRecordService medicalRecordService;

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

	@Autowired
	private IPatientService patientService;

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
		Consultation consult = consultationService.findByPatientTruckingNumber(patientTrackingNumber);
		if (patientTrackingNumber != null) {
			boolean admissionInfo = true;
			Admission results = admissionService.findByPatientTruckingNumber(patientTrackingNumber);
			String department = activeUser.getDepertment().getName();
			ConsultationDto consultation = new ConsultationDto();
			model.addAttribute("department", department);
			model.addAttribute("consultation", consultation);
			model.addAttribute("consultations", consult);
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
	public String pConsulatation(Model model, @ModelAttribute @Valid ConsultationDto consultationDto,
			BindingResult result, Principal principal) {

		Employee activeUser = userService.findDoctor(principal.getName());

		if (result.hasErrors()) {
			System.out.println("Validation Errors occured:" + result.getFieldErrors().toString());
			boolean admissionInfo = true;
			Admission results = admissionService
					.findByPatientTruckingNumber(consultationDto.getPatientTrackingNumber());
			String department = activeUser.getDepertment().getName();
			Consultation consult = consultationService
					.findByPatientTruckingNumber(consultationDto.getPatientTrackingNumber());
			model.addAttribute("department", department);
			model.addAttribute("consultations", consult);
			model.addAttribute("admission", results);
			model.addAttribute("admissionInfo", admissionInfo);

			return "consult";
		}
		if (consultationDto.getPatientTrackingNumber() != null) {

			Admission admit = admissionService.findByPatientTruckingNumber(consultationDto.getPatientTrackingNumber());
			Consultation consultation = new Consultation();

			if (result.hasErrors()) {
				Admission results = admissionService
						.findByPatientTruckingNumber(consultationDto.getPatientTrackingNumber());
				String department = activeUser.getDepertment().getName();
				model.addAttribute("department", department);
				model.addAttribute("admission", results);
				model.addAttribute("admissionInfo", true);

				return "consult";
			}
			consultation.setDescription(consultationDto.getDescription());
			consultation.setStatus("PENDING");
			consultation.setDoctor(activeUser);
			consultation.setAdmission(admit);
			consultation.setHospital(activeUser.getHospital());
			consultation.setDateTaken(LocalDate.now().toString());

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
				return "consultationD";
			}

			boolean admissionInfo = true;
			Admission results = admissionService
					.findByPatientTruckingNumber(consultationDto.getPatientTrackingNumber());
			String department = activeUser.getDepertment().getName();
			model.addAttribute("department", department);
			model.addAttribute("admission", results);
			model.addAttribute("admissionInfo", admissionInfo);
			model.addAttribute("message", "already consulted!");
			return "consult";

		}
		return "redirect:/gdoctor";
	}

	@GetMapping("/details/show/{patientTrackingNumber}")
	public String showdetails(Model model, @PathVariable String patientTrackingNumber, Principal principal) {
		if (patientTrackingNumber != null) {
			Admission results = admissionService.findByPatientTruckingNumber(patientTrackingNumber);
			model.addAttribute("admissionList", results);
			return "information";
		}

		return "redirect:/gdoctor";

	}

	@GetMapping("/details/consultation/{patientTrackingNumber}")
	public String viewPatientInfo(Model model, @PathVariable String patientTrackingNumber, Principal principal) {
		if (!patientTrackingNumber.isEmpty()) {
			ViewRecordRequest resultRequest = rService.findPRequest(patientTrackingNumber);
			Employee activeUser = userService.findDoctor(principal.getName());
			Admission admitedP = admissionService.findByPatientTruckingNumber(patientTrackingNumber);
			Patient patient = admitedP.getAdmittedPatient();
			String department = activeUser.getDepertment().getName();

			if (resultRequest == null) {
				ViewRecordRequest request = new ViewRecordRequest();
				request.setAdmission(admitedP);
				request.setRequestedBy(activeUser);
				request.setActive(true);
				request.setRequestStatus(EViewRequestStatus.PENDING);
				request.setAccessCode(IDGenerator.generateAccessCode());
				request.setRequestDate(LocalDate.now().toString());
				ViewRecordRequest vrequest = rService.createRequest(request);
				model.addAttribute("Message",
						"Tell the patient to use the following code to grant you access his/her medical records  "
								+ vrequest.getAccessCode());
				model.addAttribute("patientTrackingNumber", patientTrackingNumber);
				return "vRecordAccess";
			}
			EViewRequestStatus status = resultRequest.getRequestStatus();
			if (status.equals(EViewRequestStatus.PENDING)) {
				model.addAttribute("Message",
						"please remind the patient to use the following code to allow you viewing his/her record:   "
								+ resultRequest.getAccessCode());
				model.addAttribute("patientTrackingNumber", patientTrackingNumber);
				return "vRecordAccess";
			} else if (status.equals(EViewRequestStatus.APPROVED)) {

				List<MedicalRecordUtil> personalRecordList = medicalRecordService.findPersonalMedicalRecord(patient);
				Patient result = patientService.findPatientByPatientNumber(patient.getPatientNumber());

				RecordHistoryLog vHistory = new RecordHistoryLog();
				vHistory.setViewer(activeUser);
				vHistory.setPatient(patient);
				vHistory.setHospital(activeUser.getHospital());
				vHistory.setViewOn(LocalDate.now().toString());
				vService.create(vHistory);

				// Send data to UI
				model.addAttribute("personalRecordList", personalRecordList);
				// Old Ones!
				model.addAttribute("department", department);
				model.addAttribute("patient", result);
				model.addAttribute("results", examRecordService.findExamrecords(patientTrackingNumber));
				ViewRecordRequest resultR = rService.findPRequest(patientTrackingNumber);
				resultR.setRequestStatus(EViewRequestStatus.CLOSED);
				resultR.setActive(false);
				rService.update(resultR);
				return "information";
			} else if (status.equals(EViewRequestStatus.DENIED)) {
				resultRequest.setRequestStatus(EViewRequestStatus.PENDING);
				ViewRecordRequest vrequest = rService.update(resultRequest);
				model.addAttribute("Message",
						"Tell the patient to use the following code to grant you access his/her medical records  "
								+ vrequest.getAccessCode());
				model.addAttribute("patientTrackingNumber", patientTrackingNumber);
				return "vRecordAccess";
			}
			return "redirect:/gdoctor";

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
		model.addAttribute("messagee", "Consultate First!");
		return "consult";

	}

	@GetMapping("/prescription/{patientTrackingNumber}")
	public String prescription(Model model, @PathVariable String patientTrackingNumber, Principal principal) {
		Employee activeUser = userService.findDoctor(principal.getName());
		Admission results = admissionService.findByPatientTruckingNumber(patientTrackingNumber);
		if (patientTrackingNumber.isEmpty() == false) {
			Consultation consult = consultationService.findByPatientTruckingNumber(patientTrackingNumber);
			if (consult != null) {
				String department = activeUser.getDepertment().getName();
				Boolean patientprescription = true;
				Prescription prescriptions = new Prescription();
				model.addAttribute("patientprescription", patientprescription);
				model.addAttribute("prescriptions", prescriptions);
				model.addAttribute("patientTrackingNumber", patientTrackingNumber);
				model.addAttribute("department", department);
				model.addAttribute("admission", results);
				model.addAttribute("consultation", consult);
				model.addAttribute("examss", examRecordService.findErecords(patientTrackingNumber));

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
		model.addAttribute("messagee", "Consultate First!");
		return "consult";
	}

	@PostMapping("/patient/prescription")
	public String pPrescriptions(Model model, @ModelAttribute @Valid PrescriptionsDto prescriptionsDto,
			BindingResult results, Principal principal) {
		if (results.hasErrors()) {
			Employee activeUser = userService.findDoctor(principal.getName());
			String department = activeUser.getDepertment().getName();
			Boolean patientprescription = true;
			Consultation consult = consultationService
					.findByPatientTruckingNumber(prescriptionsDto.getPatientTrackingNumber());
			Admission result = admissionService
					.findByPatientTruckingNumber(prescriptionsDto.getPatientTrackingNumber());
			model.addAttribute("patientTrackingNumber", prescriptionsDto.getPatientTrackingNumber());
			model.addAttribute("department", department);

			model.addAttribute("patientprescription", patientprescription);
			model.addAttribute("admission", result);
			model.addAttribute("consultation", consult);
			model.addAttribute("examss", examRecordService.findErecords(prescriptionsDto.getPatientTrackingNumber()));

			return "consultationD";
		}
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
			Consultation consult = consultationService
					.findByPatientTruckingNumber(prescriptionsDto.getPatientTrackingNumber());
			consult.setStatus("COMPLETE");
			admit.setStatus("COMPLETE");
			consultationService.update(consult);
			admissionService.update(admit);
			return "redirect:/gdoctor";
		}
		boolean admissionInfo = true;

		String department = activeUser.getDepertment().getName();
		model.addAttribute("department", department);
		model.addAttribute("admission", admit);
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
				examrecords.setExamRequesterDoctor(activeUser);
				examRecordService.creaExamRecords(examrecords);
			}
			Consultation consult = consultationService.findByPatientTruckingNumber(examDto.getPatientTrackingNumber());
			consult.setStatus("MIDLE");
			consultationService.update(consult);

			return "redirect:/gdoctor";
		}
		Admission results = admissionService.findByPatientTruckingNumber(examDto.getPatientTrackingNumber());
		Consultation consult = consultationService.findByPatientTruckingNumber(examDto.getPatientTrackingNumber());
		String department = activeUser.getDepertment().getName();
		ExamRecord examRecord = new ExamRecord();
		model.addAttribute("examRecord", examRecord);
		model.addAttribute("department", department);
		model.addAttribute("admission", results);
		model.addAttribute("examss", examService.findExams());
		model.addAttribute("department", department);
		model.addAttribute("consultation", consult);
		model.addAttribute("admission", results);
		model.addAttribute("messageE", "please select exam!");
		return "consultationD";
	}

	@GetMapping("/labo/{patientTrackingNumber}")
	public String viewExam(Model model, @PathVariable String patientTrackingNumber, Principal principal) {
		if (!patientTrackingNumber.isEmpty()) {
			ExamRecord examRecord = new ExamRecord();
			model.addAttribute("examRecord", examRecord);
			model.addAttribute("examss", examRecordService.findErecords(patientTrackingNumber));
			model.addAttribute("patientTrackingNumber", patientTrackingNumber);

			return "labo/labo";
		}

		return "redirect:/labodoctor";

	}

	@PostMapping("/results")
	public String saveResults(@RequestParam(value = "id", required = false) int[] id,
			@RequestParam(value = "results", required = true) String[] results,
			@RequestParam String patientTrackingNumber, Model model, Principal principal) {

		if (id.length != results.length) {
			ExamRecord examRecord = new ExamRecord();
			model.addAttribute("examRecord", examRecord);
			model.addAttribute("examss", examRecordService.findErecords(patientTrackingNumber));
			model.addAttribute("patientTrackingNumber", patientTrackingNumber);
			model.addAttribute("errors", null);
			model.addAttribute("errorFound", true);
			return "labo/labo";
		}

		boolean errorFound = false;

		List<ExamRecord> recordsWithError = new ArrayList<>();
		for (int i = 0; i < id.length; i++) {
			if (results[i].isEmpty() || results[i] == null) {
				ExamRecord record = examRecordService.findExamRecordByExamId(id[i]);
				recordsWithError.add(record);

			}
		}

		if (errorFound || !recordsWithError.isEmpty()) {
			ExamRecord examRecord = new ExamRecord();
			model.addAttribute("examRecord", examRecord);
			model.addAttribute("examss", examRecordService.findErecords(patientTrackingNumber));
			model.addAttribute("patientTrackingNumber", patientTrackingNumber);
			model.addAttribute("errors", recordsWithError);
			model.addAttribute("errorFound", errorFound);
			return "labo/labo";
		}
		Employee employee = userService.findDoctor(principal.getName());
		Admission admission = new Admission();
		for (int i = 0; i < id.length; i++) {
			ExamRecord records = examRecordService.findExamRecordByExamId(id[i]);
			records.setResults(results[i]);
			records.setClosedWithResult(true);
			records.setExamResponseEmployee(employee);
			ExamRecord updatedRecord = examRecordService.update(records);
			admission = updatedRecord.getAdmissionInfo();
		}
		Consultation consult = consultationService.findByPatientTruckingNumber(admission.getPatientTrackingNumber());
		consult.setStatus("COMPLETE");
		consultationService.update(consult);
		model.addAttribute("examRecords", examRecordService.findExamRecordByAddmission(admission));
		model.addAttribute(employee.getDepertment().getName());
		return "labo/exam_result_summary";
	}

	@GetMapping("/showresults/{patientTrackingNumber}")
	public String showresults(Model model, @PathVariable String patientTrackingNumber, Principal principal) {
		if (patientTrackingNumber != null) {
			Employee employee = userService.findDoctor(principal.getName());
			Admission results = admissionService.findByPatientTruckingNumber(patientTrackingNumber);
			model.addAttribute("examRecords", examRecordService.findExamRecordByAddmission(results));
			model.addAttribute(employee.getDepertment().getName());
			return "labo/exam_result_summary";
		}

		return "redirect:/labodoctor";

	}

}
