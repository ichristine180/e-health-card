
package rw.ehealth.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.Doctor;
import rw.ehealth.service.admission.IAdmissionService;
import rw.ehealth.service.medical.IHospitalService;
import rw.ehealth.service.user.IUserService;

@Controller
public class HospitalController {

	@Autowired
	private IHospitalService hospitalService;

	@Autowired
	private IAdmissionService admissionService;

	@Autowired
	private IUserService userService;

	@GetMapping("/admissions")
	public String getActiveAdmissions(Model model, Principal principal) {

		Doctor activeUser = userService.findDoctor(principal.getName());
		List<AdmissionInfo> admissionList = admissionService
				.allAdmissionsPerHospital(activeUser.getHospital().getHospitalName());

		model.addAttribute("admissionList", admissionList);

		return "admissions";
	}

}
