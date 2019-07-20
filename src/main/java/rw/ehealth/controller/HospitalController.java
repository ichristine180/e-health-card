
package rw.ehealth.controller;

import java.security.Principal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.Consultation;
import rw.ehealth.model.Doctor;
import rw.ehealth.model.Patient;
import rw.ehealth.service.admission.IAdmissionService;
import rw.ehealth.service.medical.IconsultationService;
import rw.ehealth.service.patient.PatientService;
import rw.ehealth.service.user.IUserService;
import rw.ehealth.utils.ConsultationDto;

@Controller
public class HospitalController {

	@Autowired
	private IAdmissionService admissionService;

	@Autowired
	private IUserService userService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private IconsultationService consultationService;

	@GetMapping("/admissions")
	public String getActiveAdmissions(Model model, Principal principal) {

		Doctor activeUser = userService.findDoctor(principal.getName());
		List<AdmissionInfo> admissionList = admissionService
				.allAdmissionsPerHospital(activeUser.getHospital().getHospitalId(), true);
		long admissionSize = admissionService.countAdmission(activeUser.getHospital().getHospitalId(), true);
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
			AdmissionInfo results = admissionService.findByPatientTruckingNumber(patientTrackingNumber);
			model.addAttribute("admission", results);
			model.addAttribute("admissionInfo", admissionInfo);

			return "admissions";
		}

		return "redirect:/";

	}

	@GetMapping("/consultation/{patientTrackingNumber}")
	public String consultation(Model model, @PathVariable String patientTrackingNumber, Principal principal) {
		Doctor activeUser = userService.findDoctor(principal.getName());

		if (patientTrackingNumber != null) {
			boolean admissionInfo = true;
			AdmissionInfo results = admissionService.findByPatientTruckingNumber(patientTrackingNumber);
			String department = activeUser.getDepertment();
			Consultation consultation = new Consultation();
			model.addAttribute("department", department);
			model.addAttribute("consultation", consultation);
			model.addAttribute("admission", results);
			model.addAttribute("admissionInfo", admissionInfo);

			return "consult";
		}

		return "redirect:/";

	}
@PostMapping("/patient/consultation")
	public String pConsulatation(Model model, @ModelAttribute ConsultationDto consultationDto, Principal principal) {
		Doctor activeUser = userService.findDoctor(principal.getName());
		AdmissionInfo admit = admissionService.findByPatientTruckingNumber(consultationDto.getPatientTrackingNumber());
		Consultation consultation = new Consultation();

		consultation.setDescription(consultationDto.getDescription());
		consultation.setDoctor(activeUser);
		consultation.setAdmissionInfo(admit);
		Consultation consultated = consultationService.createConsultation(consultation);
		if(consultated != null) {
			boolean admissionInfo = true;
			AdmissionInfo results = admissionService.findByPatientTruckingNumber(consultationDto.getPatientTrackingNumber());
			String department = activeUser.getDepertment();
			model.addAttribute("department", department);
			model.addAttribute("consultation", consultation);
			model.addAttribute("admission", results);
			model.addAttribute("admissionInfo", admissionInfo);
			return "consult";
		}
		

		return "redirect:/";

	}
}
