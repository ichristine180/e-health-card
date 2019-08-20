
package rw.ehealth.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rw.ehealth.enums.EHealthFacilityType;
import rw.ehealth.enums.EViewRequestStatus;
import rw.ehealth.model.Admission;
import rw.ehealth.model.Consultation;
import rw.ehealth.model.Department;
import rw.ehealth.model.ExamRecord;
import rw.ehealth.model.Hospital;
import rw.ehealth.model.MedicalExam;
import rw.ehealth.model.Patient;
import rw.ehealth.model.Prescription;
import rw.ehealth.model.RecordHistoryLog;
import rw.ehealth.model.ViewRecordRequest;
import rw.ehealth.service.admission.IAdmissionService;
import rw.ehealth.service.consultation.IConsultationService;
import rw.ehealth.service.exams.IExamRecordService;
import rw.ehealth.service.exams.IExamService;
import rw.ehealth.service.hospital.IHospitalService;
import rw.ehealth.service.medical.IViewRecordHistoryService;
import rw.ehealth.service.medical.IViewRequestService;
import rw.ehealth.service.patient.PatientService;
import rw.ehealth.service.prescription.IPrescriptionService;
import rw.ehealth.service.user.IDepartemtService;
import rw.ehealth.utils.PatientListResponse;
import rw.ehealth.utils.PinfoListResponse;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private PatientService pservice;

	@Autowired
	private IExamRecordService eService;

	@Autowired
	private IAdmissionService aService;

	@Autowired
	private IConsultationService cService;

	@Autowired
	private IHospitalService hospitalService;
	@Autowired
	private IPrescriptionService pService;

	@Autowired
	private IViewRequestService rService;

	@Autowired
	private IViewRecordHistoryService vService;

	@Autowired
	private IDepartemtService departmentService;

	@Autowired
	private IExamService eexamService;

	@PostMapping("/pIn")
	public ResponseEntity<PatientListResponse> getPatientInfo(@RequestParam String patientNumber) {
		PatientListResponse response = new PatientListResponse();
		System.out.println("Hitting here");
		Patient results = pservice.findPatientByPatientNumber(patientNumber);
		if (results != null) {
			response.setError(false);
			response.setMessage("patient found");
			response.setPatients(results);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		response.setError(true);
		response.setMessage("invalid Pnumber");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@PostMapping("/getRequest")
	public ResponseEntity<PinfoListResponse> getRequest(@RequestParam String patientNumber) {
		PinfoListResponse response = new PinfoListResponse();
		System.out.println("Hitting here");
		ViewRecordRequest results = rService.findPRequest(patientNumber);
		if (results != null) {
			response.setError(false);
			response.setMessage("patient found");
			response.setRequest(results);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		response.setError(true);
		response.setMessage("invalid Pnumber");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@PostMapping("/getHospital")
	public ResponseEntity<PinfoListResponse> getHospitalPerPatient(@RequestParam String patientNumber) {
		PinfoListResponse response = new PinfoListResponse();
		System.out.println("Hitting here");
		List<Admission> results = aService.findHospitalBYpatientNumber(patientNumber);
		if (results.size() != 0) {
			response.setError(false);
			response.setMessage("hospital found");
			List<Admission> hospitals = new ArrayList<Admission>();
			hospitals.addAll(results);
			response.setAdmissionInfos(hospitals);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		response.setError(true);
		response.setAdmissionInfos(null);
		response.setMessage("no hospital found");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}

	@PostMapping("/getAdmission")
	public ResponseEntity<PinfoListResponse> getAdmissionPerHospital(@RequestParam String patientNumber,

			@RequestParam Long hospitalId) {
		PinfoListResponse response = new PinfoListResponse();
		System.out.println("Hitting here");
		List<Admission> results = aService.findPAdmissionBYpatientNumber(patientNumber, hospitalId);
		if (results.size() != 0) {
			response.setError(false);
			response.setMessage("Information found");
			List<Admission> hospitals = new ArrayList<Admission>();
			hospitals.addAll(results);
			response.setAdmissionInfos(results);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		response.setError(true);
		response.setAdmissionInfos(null);
		response.setMessage("no information found");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}

	@PostMapping("/getAll")
	public ResponseEntity<PinfoListResponse> getAll(@RequestParam String patientTrackingNumber) {
		PinfoListResponse response = new PinfoListResponse();
		System.out.println("Hitting here");
		List<ExamRecord> examrecords = eService.findExamRecordsByPatient(patientTrackingNumber);
		Consultation results = cService.findByPatientTruckingNumber(patientTrackingNumber);
		Prescription medecine = pService.findPByPatientTruckingNumber(patientTrackingNumber);
		if (results != null || examrecords.size() != 0 || medecine != null) {
			response.setError(false);
			response.setMessage("Information found");
			response.setConsultation(results);
			response.setPrescription(medecine);
			List<ExamRecord> hospitals = new ArrayList<ExamRecord>();
			hospitals.addAll(examrecords);
			response.setExamRecords(hospitals);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		response.setError(true);
		response.setConsultation(null);
		response.setExamRecords(null);
		response.setPrescription(null);
		response.setMessage("no information found");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}

	@PostMapping("/ApproveStatus")
	public ResponseEntity<PinfoListResponse> approveStatus(@RequestParam String patientNumber) {
		System.out.println("Reaching at this point at least");
		// Getting the student data from the client request andcreate a new student object to be saved
		ViewRecordRequest results = rService.findPRequest(patientNumber);
		results.setRequestStatus(EViewRequestStatus.APPROVED);
		PinfoListResponse response = new PinfoListResponse();
		if (rService.update(results) != null) {
			response.setError(false);
			response.setMessage("request Accepted");
			response.setRequest(results);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		}
		response.setError(true);
		response.setMessage("not updated");
		response.setRequest(null);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping("/DenyStatus")
	public ResponseEntity<PinfoListResponse> denyStatus(@RequestParam String patientNumber) {
		System.out.println("Reaching at this point at least");
		ViewRecordRequest results = rService.findPRequest(patientNumber);
		results.setRequestStatus(EViewRequestStatus.DENIED);
		PinfoListResponse response = new PinfoListResponse();
		if (rService.update(results) != null) {
			response.setError(false);
			response.setMessage("request Denyed");
			response.setRequest(results);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		}
		response.setError(true);
		response.setMessage("not updated");
		response.setRequest(null);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping("/getHistory")
	public ResponseEntity<PatientListResponse> getMedicalHistory(@RequestParam String patientNumber) {
		PatientListResponse response = new PatientListResponse();
		System.out.println("Hitting here");
		List<RecordHistoryLog> results = vService.findMedicalHistory(patientNumber);
		if (results.size() != 0) {
			response.setError(false);
			response.setMessage("Info found");
			List<RecordHistoryLog> hospitals = new ArrayList<RecordHistoryLog>();
			hospitals.addAll(results);
			response.setPatientRecordsViewHistory(hospitals);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		response.setError(true);
		response.setPatientRecordsViewHistory(null);
		response.setMessage("no info found");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}

	@GetMapping("/create/master/data/hospital")
	public ResponseEntity<List<Hospital>> createMasterData() {
		List<Department> allDepartments = departmentService.findAllDepartemts();
		List<MedicalExam> allExams = eexamService.findExams();

		Set<Department> departments = new HashSet<>(allDepartments);
		Set<MedicalExam> exams = new HashSet<>(allExams);
		// Hospitals
		Hospital firstHospital = new Hospital();
		firstHospital.setAddress("Kicukiro");
		firstHospital.setHospitalCode("KFH");
		firstHospital.setHospitalName("King Faical Hospital");
		firstHospital.setType(EHealthFacilityType.REFERRAL_HOSPITAL);

		firstHospital.setDepartments(departments);
		firstHospital.setExams(exams);

		Hospital secondHospital = new Hospital();
		secondHospital.setAddress("Bugesera");
		secondHospital.setHospitalCode("NAH");
		secondHospital.setHospitalName("Nyamata ADEPER Hospital");
		secondHospital.setType(EHealthFacilityType.DISTRICT_HOSPITAL);

		secondHospital.setDepartments(departments);
		secondHospital.setExams(exams);

		List<Hospital> createdHospitals = new ArrayList<>();

		createdHospitals.add(hospitalService.createHospital(firstHospital));
		createdHospitals.add(hospitalService.createHospital(secondHospital));

		return new ResponseEntity<>(createdHospitals, HttpStatus.OK);

	}
}
