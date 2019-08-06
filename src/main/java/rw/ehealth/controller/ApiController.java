package rw.ehealth.controller;

import java.util.ArrayList;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.Consultation;
import rw.ehealth.model.ExamRecords;
import rw.ehealth.model.Patient;
import rw.ehealth.model.Prescription;
import rw.ehealth.model.Request;
import rw.ehealth.service.admission.AdmissionService;
import rw.ehealth.service.medical.ConsultationService;
import rw.ehealth.service.medical.ExamRecordService;
import rw.ehealth.service.medical.PrescriptionService;
import rw.ehealth.service.medical.RequestService;
import rw.ehealth.service.patient.PatientService;
import rw.ehealth.utils.PatientListResponse;
import rw.ehealth.utils.PinfoListResponse;

@RestController
public class ApiController {
	@Autowired
	private PatientService pservice;
	@Autowired
	private ExamRecordService eService;
	@Autowired
	private AdmissionService aService;
	@Autowired
	private ConsultationService cService;
	@Autowired
	private PrescriptionService pService;
	@Autowired
	private RequestService rService;


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
		Request results = rService.findPRequest(patientNumber,"PENDING");
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
	public ResponseEntity<PinfoListResponse>getHospitalPerPatient(@RequestParam String patientNumber) {
		PinfoListResponse response = new PinfoListResponse();
		System.out.println("Hitting here");
		List<AdmissionInfo> results = aService.findHospitalBYpatientNumber(patientNumber);
		if (results.size()!=0) {
			response.setError(false);
			response.setMessage("hospital found");
			List<AdmissionInfo> hospitals = new ArrayList<AdmissionInfo>();
			hospitals.addAll(results);
			response.setAdmissionInfos(hospitals);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		
		response.setError(true);
		response.setAdmissionInfos(null);
		response.setMessage("no hospital found");
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		
	}
	@PostMapping("/getAdmission")
	public ResponseEntity<PinfoListResponse>getAdmissionPerHospital(@RequestParam String patientNumber,@RequestParam Long hospitalId) {
		PinfoListResponse response = new PinfoListResponse();
		System.out.println("Hitting here");
		List<AdmissionInfo> results = aService.findPAdmissionInfoBYpatientNumber(patientNumber,hospitalId);
		if (results.size()!=0) {
			response.setError(false);
			response.setMessage("Information found");
			List<AdmissionInfo> hospitals = new ArrayList<AdmissionInfo>();
			hospitals.addAll(results);
			response.setAdmissionInfos(hospitals);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		
		response.setError(true);
		response.setAdmissionInfos(null);
		response.setMessage("no information found");
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		
	}
	@PostMapping("/getby")
	public ResponseEntity<PinfoListResponse>getall(@RequestParam String email) {
		PinfoListResponse response = new PinfoListResponse();
		System.out.println("Hitting here");
		List<AdmissionInfo> results = aService.findBydoctor(email);
		if (results.size()!=0) {
			response.setError(false);
			response.setMessage("Information found");
			List<AdmissionInfo> hospitals = new ArrayList<AdmissionInfo>();
			hospitals.addAll(results);
			response.setAdmissionInfos(hospitals);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		
		response.setError(true);
		response.setAdmissionInfos(null);
		response.setMessage("no information found");
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		
	}
	@PostMapping("/getAll")
	public ResponseEntity<PinfoListResponse>getAll(@RequestParam String patientTrackingNumber) {
		PinfoListResponse response = new PinfoListResponse();
		System.out.println("Hitting here");
		List<ExamRecords> examrecords = eService.findExamRecordsByPatient(patientTrackingNumber);
		Consultation results = cService.findByPatientTruckingNumber(patientTrackingNumber);
		Prescription medecine = pService.findPByPatientTruckingNumber(patientTrackingNumber);
		if (results != null && examrecords.size()!=0 && medecine != null) {
			response.setError(false);
			response.setMessage("Information found");
			response.setConsultation(results);
			response.setPrescription(medecine);
			List<ExamRecords> hospitals = new ArrayList<ExamRecords>();
			hospitals.addAll(examrecords);
			response.setExamRecords(hospitals);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		
		response.setError(true);
		response.setConsultation(null);
		response.setExamRecords(null);
		response.setPrescription(null);
		response.setMessage("no information found");
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		
	}
	@PostMapping("/ApproveStatus")
	public ResponseEntity<PinfoListResponse>approveStatus(@RequestParam String patientNumber) {

		System.out.println("Reaching at this point at least");
		// Getting the student data from the client request and create a new student object to be saved
		Request results = rService.findPRequest(patientNumber,"PENDING");
		results.setStatus("APPROVED");
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
	public ResponseEntity<PinfoListResponse>denyStatus(@RequestParam String patientNumber) {

		System.out.println("Reaching at this point at least");
		// Getting the student data from the client request and create a new student object to be saved
		Request results = rService.findPRequest(patientNumber,"PENDING");
		results.setStatus("DENYED");
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

}
	

