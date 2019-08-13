
package rw.ehealth.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import rw.ehealth.model.Department;
import rw.ehealth.model.Employee;
import rw.ehealth.model.Hospital;
import rw.ehealth.model.Patient;
import rw.ehealth.model.User;
import rw.ehealth.model.security.UserRole;
import rw.ehealth.service.hospital.IHospitalService;
import rw.ehealth.service.patient.IPatientService;
import rw.ehealth.service.user.DepartemtService;
import rw.ehealth.service.user.UserService;
import rw.ehealth.utils.DoctorData;
import rw.ehealth.utils.IDGenerator;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private IHospitalService hospitalService;

	@Autowired
	private IPatientService patientService;

	@Autowired
	private DepartemtService departemtService;

	@GetMapping(value = "/hospregistration")
	public String registerHospital(Model model) {
		Hospital newhospital = new Hospital();
		model.addAttribute("hospital", newhospital);
		boolean hospitals = true;
		model.addAttribute("hospitals", hospitals);
		return "registration";
	}

	@PostMapping("/registration")
	public String registerPatient(Model model, @ModelAttribute @Valid Patient patient, Principal principal) {
		String username = principal.getName();
		Employee doctor = userService.findDoctor(username);
		Hospital hospital = doctor.getHospital();
		String hospitalName = hospital.getHospitalName();
		if (patient.getIdentificationNumber().isEmpty() == false) {
			if (patient.getIdentificationNumber() != null) {
				Patient existingPatient = patientService
						.findPatientByIdentificationNumber(patient.getIdentificationNumber());

				if (existingPatient != null) {
					model.addAttribute("message", "Patient already registered");
					model.addAttribute("patient", patient);

					return "registration";
				}

				Patient newPatient = new Patient();

				newPatient.setAddress(patient.getAddress());
				newPatient.setDateOfBirth(patient.getDateOfBirth());
				newPatient.setFname(patient.getFname());
				newPatient.setLname(patient.getLname());
				newPatient.setGender(patient.getGender());
				newPatient.setIdentificationNumber(patient.getIdentificationNumber());
				newPatient.setRegisteredDate(LocalDate.now().toString());
				newPatient.setHospital(hospitalName);
				newPatient.setPatientNumber(IDGenerator.generatePatientNumber(newPatient));

				Patient registeredPatient = patientService.savePatientInfo(newPatient);

				model.addAttribute("patientId", registeredPatient.getPatientNumber());
				model.addAttribute("patient", registeredPatient);
				boolean registration = true;
				model.addAttribute("registration", registration);

				return "registrationSuccess";
			}
		}
		model.addAttribute("error", "Invalid Patient Data");
		model.addAttribute("patient", patient);
		return "registration";

	}

	@PostMapping("/hospregistration")
	public String registerhospital(Model model, @ModelAttribute @Valid Hospital hospital) {
		if (hospital.getHospitalName().isEmpty() == false) {
			Hospital existingHospital = hospitalService.findByHospitalname(hospital.getHospitalName());

			if (existingHospital != null) {
				model.addAttribute("message", "Hospital already registered");
				model.addAttribute("hospital", hospital);

				return "redirect:/hospregistration";
			}
			Hospital newhospital = new Hospital();
			newhospital.setHospitalName(hospital.getHospitalName());
			newhospital.setType(hospital.getType());
			newhospital.setAddress(hospital.getAddress());
			hospitalService.createHospital(newhospital);
			return "redirect:/";

		}
		model.addAttribute("error", "invalide hospital data");
		model.addAttribute("hospital", hospital);
		boolean hospitals = true;
		model.addAttribute("hospitals", hospitals);
		return "registration";

	}

	@PostMapping("/docregistration")
	public String adddoctor(@ModelAttribute("user") @Valid DoctorData user, Model model) {
		if (user.getHospitalname().isEmpty() == false && user.getEmail().isEmpty() == false) {
			Employee doc = new Employee();
			doc.setEmail(user.getEmail());
			doc.setFname(user.getFname());
			doc.setLname(user.getLname());
			doc.setTimestamp(LocalDate.now().toString());
			doc.setPhone(user.getPhone());
			Hospital hospitals = hospitalService.findByHospitalname(user.getHospitalname());
			doc.setHospital(hospitals);
			Department departemt = departemtService.findPerName(user.getDepertmentName());
			doc.setDepertment(departemt);

			if (userService.checkUsernameExists(user.getEmail())) {
				if (userService.checkUsernameExists(user.getEmail())) {
					model.addAttribute("emailExists", true);
					System.out.println("email exists");
				}
				return "redirect:/docregistration";
			}
			User myuser = new User();

			myuser.setUsername(user.getEmail());
			myuser.setPassword("pass");
			Set<UserRole> userRoles = new HashSet<>();
			userRoles.add(new UserRole(myuser, userService.findByName(user.getRoleName())));
			myuser.setDoctor(doc);
			doc.setUser(myuser);
			userService.createUser(myuser);
			userService.createUser(myuser, userRoles);
			return "redirect:/";
		}
		return "redirect:/docregistration";
	}
}
