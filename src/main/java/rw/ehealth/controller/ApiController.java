package rw.ehealth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rw.ehealth.model.Patient;
import rw.ehealth.service.patient.PatientService;
import rw.ehealth.utils.PatientListResponse;

@RestController
public class ApiController {
	@Autowired
	PatientService pservice;

	@PostMapping("/api")
	public ResponseEntity<PatientListResponse> getStudentMarksByRegnbr(@RequestParam String patientNumber) {
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

}
