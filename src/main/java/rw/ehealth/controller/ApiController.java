package rw.ehealth.controller;

import java.util.ArrayList;


import java.util.List;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.ExamRecords;
import rw.ehealth.model.Patient;
import rw.ehealth.service.admission.AdmissionService;
import rw.ehealth.service.medical.ExamRecordService;
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
		System.out.println(results.size());
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
}
	

