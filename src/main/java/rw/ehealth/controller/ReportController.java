
package rw.ehealth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.service.admission.IAdmissionService;

@RestController
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private IAdmissionService admissionService;

	@PostMapping("/admission/month")
	public ResponseEntity<List<AdmissionInfo>> getAdmisionsReport(@RequestParam int month) {
		System.out.println(month + " the month we're looking for");
		List<AdmissionInfo> results = admissionService.getAdmissionsPerMonth(month);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}

}
