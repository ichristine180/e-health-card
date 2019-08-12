
package rw.ehealth.controller.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import rw.ehealth.service.medical.IHospitalService;

@Controller
public class AdminReportController {

	@Autowired
	IHospitalService hospitalService;

	@GetMapping("/admin/report")
	public String adminReportHomepage(Model model) {
		model.addAttribute("hospitals", hospitalService.findAllHospitals());
		return "admin";
	}

}
