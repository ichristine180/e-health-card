
package rw.ehealth.controller.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import rw.ehealth.service.admission.IAdmissionService;
import rw.ehealth.service.medical.IHospitalService;
import rw.ehealth.service.patient.IPatientService;
import rw.ehealth.service.user.IUserService;

@Controller
public class AdminReportController {

	@Autowired
	private IHospitalService hospitalService;

	@Autowired
	private IPatientService patientService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IAdmissionService admissionService;

	@GetMapping("/admin/report")
	public String adminReportHomepage(Model model) {
		model.addAttribute("hospitals", hospitalService.findAllHospitals());
		model.addAttribute("hospitalCount", hospitalService.findAllHospitals().size());
		model.addAttribute("admissionCount", admissionService.findAllAdmission().size());
		model.addAttribute("patientCount", patientService.findAll().size());
		model.addAttribute("userCount", userService.findUserList().size());
		return "report/admin";
	}

	@GetMapping
	public String hospitalReportPag(Model model, @PathVariable String hospitalCode) {

		return "report/h_report_page";
	}

}
