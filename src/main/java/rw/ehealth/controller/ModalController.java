
package rw.ehealth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import rw.ehealth.model.Admission;
import rw.ehealth.service.admission.IAdmissionService;
import rw.ehealth.service.exams.IExamRecordService;

@Controller
public class ModalController {
	@Autowired
	private IExamRecordService examRecordService;
	@Autowired
	private IAdmissionService admissionService;

	@GetMapping(value = "/exams/details/{patientTrackingNumber}")
	public String displayPatientExamRecord(@PathVariable String patientTrackingNumber, Model model) {

		Admission admission = admissionService.findByPatientTruckingNumber(patientTrackingNumber);
		System.out.println(examRecordService.findExamRecordByAddmission(admission).size());
		model.addAttribute("examRecords", examRecordService.findExamRecordByAddmission(admission));

		return "information :: modalContents";
	}

}
