
package rw.ehealth.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.Doctor;
import rw.ehealth.model.Hospital;
import rw.ehealth.model.Patient;
import rw.ehealth.model.User;
import rw.ehealth.model.security.Role;
import rw.ehealth.model.security.UserRole;
import rw.ehealth.service.admission.AdmissionService;
import rw.ehealth.service.medical.HospitalService;
import rw.ehealth.service.patient.PatientService;
import rw.ehealth.service.user.UserService;
import rw.ehealth.utils.AdmissionData;
import rw.ehealth.utils.DoctorData;
import rw.ehealth.utils.HopitaData;
import rw.ehealth.utils.PatientData;

@Controller
public class HomeController {
	@Autowired
	private UserService userService;
	@Autowired
	private HospitalService hospitalService;
	@Autowired
	private PatientService patientservice;
	@Autowired
	private AdmissionService admissionService;;

	@RequestMapping("/")
	public String homepage(Model model, Principal principal) {
		long doctors = userService.countDoctor();
		model.addAttribute("doctors", doctors);
		long hospitals = hospitalService.countHospital();
		model.addAttribute("hospitals", hospitals);
		long patients = patientservice.countPatient();
		model.addAttribute("patients", patients);
		String username = principal.getName();
		long admissions = admissionService.countAdmission(username);
		model.addAttribute("admissions", admissions);
		return "homepage";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping(value = "/users")
	public String doctor(Model model) {
		Iterable<Role> role = userService.findAll();
		Iterable<Hospital> hospitals = hospitalService.findAllHospitals();
		DoctorData newdoctor = new DoctorData();
		model.addAttribute("doctor", newdoctor);
		model.addAttribute("hospitals", hospitals);
		model.addAttribute("role", role);
		boolean user = true;
		model.addAttribute("user", user);

		return "admin";
	}

	@GetMapping(value = "/hospital")
	public String viewTheForm(Model model) {
		HopitaData newhospital = new HopitaData();
		model.addAttribute("hospital", newhospital);
		boolean hospitals = true;
		model.addAttribute("hospitals", hospitals);
		return "admin";
	}

	@GetMapping(value = "/patient")
	public String Patient(Model model) {
		PatientData newpatient = new PatientData();
		model.addAttribute("patient", newpatient);
		boolean patients = true;
		model.addAttribute("patients", patients);
		return "reception";
	}

	@GetMapping(value = "/admission")
	public String adimssion(Model model) {
		boolean admissions = true;
		model.addAttribute("admissions", admissions);
		AdmissionData admission = new AdmissionData();
		model.addAttribute("admission", admission);
		return "reception";
	}

	@RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public String adddoctor(@ModelAttribute("user") @Valid DoctorData user, Model model) {

		Doctor doc = new Doctor();
		doc.setEmail(user.getEmail());
		doc.setFname(user.getFname());
		doc.setLname(user.getLname());
		doc.setTimestamp(LocalDateTime.now().toString());
		doc.setDepertment(user.getDepertment());
		doc.setPhone(user.getPhone());
		Hospital hospitals = hospitalService.findByHospitalname(user.getHospitalname());
		doc.setHospital(hospitals);
		if (userService.checkUsernameExists(user.getEmail())) {
			if (userService.checkUsernameExists(user.getEmail())) {
				model.addAttribute("emailExists", true);
				System.out.println("email exists");
			}
			return "admin";
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
		return "admin";
	}

	@RequestMapping(value = "/newhospital", method = RequestMethod.POST)
	public String addHospital(@ModelAttribute("hospitals") @Valid HopitaData hospitals, Model model) {
		Hospital hosp = new Hospital();
		hosp.setHospitalName(hospitals.getHospitalname());
		hosp.setType(hospitals.getType());
		hosp.setAddress(hospitals.getAddress());
		hospitalService.createHospital(hosp);
		return "redirect:/hospital";
	}

	@RequestMapping(value = "/newpatient", method = RequestMethod.POST)
	public String addPatient(@ModelAttribute("patient") @Valid PatientData patientData, Model model) {
		Patient patients = new Patient();
		patients.setFname(patientData.getFname());
		patients.setLname(patientData.getLname());
		patients.setAddress(patientData.getAddress());
		patients.setIdentificationNumber(patientData.getIdentificationNumber());
		patientservice.savePatientInfo(patients);
		return "redirect:/patient";

	}

	@RequestMapping(value = "/newadmission", method = RequestMethod.POST)
	public String searchPatient(@ModelAttribute("admission") @Valid AdmissionData admissionData, Model model,
			Principal principal) {
		String username = principal.getName();
		Doctor user = userService.findUserByUsername(username);
		Patient patients = patientservice.findPatientByIdentificationNumber(admissionData.getIdentificationNumber());
		AdmissionInfo newadmission = new AdmissionInfo();
		newadmission.setBloodPressure(admissionData.getBloodPressure());
		newadmission.setHeartRate(admissionData.getHeartRate());
		newadmission.setHeight(admissionData.getHeight());
		newadmission.setTemperature(admissionData.getTemperature());
		newadmission.setAdmissionDate(LocalDateTime.now());
		newadmission.setAdmittedPatient(patients);

		newadmission.setDoctor(user);

		admissionService.createNewPatientAdmission(newadmission);

		return "redirect:/admission";

	}
}
