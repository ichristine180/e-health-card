
package rw.ehealth.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import rw.ehealth.model.Admission;
import rw.ehealth.model.Department;
import rw.ehealth.model.Employee;
import rw.ehealth.model.Hospital;
import rw.ehealth.model.Patient;
import rw.ehealth.model.User;
import rw.ehealth.model.security.Role;
import rw.ehealth.model.security.UserRole;
import rw.ehealth.service.admission.IAdmissionService;
import rw.ehealth.service.medical.IHospitalService;
import rw.ehealth.service.patient.IPatientService;
import rw.ehealth.service.user.DepartemtService;
import rw.ehealth.service.user.IEmployeeService;
import rw.ehealth.service.user.UserService;
import rw.ehealth.utils.AdmissionDto;
import rw.ehealth.utils.DoctorData;
import rw.ehealth.utils.PatientData;

@Controller
public class AdmissionController {

	@Autowired
	private UserService userService;

	@Autowired
	private IHospitalService hospitalService;

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private IAdmissionService admissionService;

	@Autowired
	private IPatientService patientService;

	@Autowired
	private DepartemtService departemtService;

	@GetMapping("/registration")
	public String registerPatient(Model model) {
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		return "registration";
	}

	@GetMapping("/docregistration")
	public String registerDoctor(Model model) {
		DoctorData doctor = new DoctorData();
		Iterable<Role> role = userService.findAll();
		Iterable<Hospital> hospitals = hospitalService.findAllHospitals();
		Iterable<Department> departemt = departemtService.findAllDepartemts();
		model.addAttribute("departemt", departemt);
		model.addAttribute("hospitals", hospitals);
		model.addAttribute("role", role);
		model.addAttribute("doctor", doctor);
		boolean doctors = true;
		model.addAttribute("doctors", doctors);
		return "registration";
	}

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
				newPatient.setPatientNumber(this.generatePatientNumber(patient));

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
			// userService.createUser(myuser);
			User createdUser = userService.createUser(myuser, userRoles);
			doc.setUser(createdUser);

			// Create the employee
			employeeService.createEmployee(doc);
			return "redirect:/";
		}
		return "redirect:/docregistration";
	}

	@SuppressWarnings("unused")
	@PostMapping("/patient/admission")
	public String admitPatient(Model model, @ModelAttribute AdmissionDto admission, Principal principal) {
		String username = principal.getName();
		Employee user = userService.findUserByUsername(username);
		Admission newadmission = new Admission();
		newadmission.setBloodPressure(admission.getBloodPressure());
		newadmission.setHeartRate(admission.getHeartRate());
		newadmission.setHeight(admission.getHeight());
		newadmission.setWeight(admission.getWeight());
		newadmission.setTemperature(admission.getTemperature());

		Department depertment = departemtService.findPerName(admission.getDepartementName());
		newadmission.setDepartement(depertment);
		newadmission.setAdmissionDate(LocalDate.now().toString());
		newadmission.setAdmittedPatient(patientService.findPatientByPatientNumber(admission.getPatientNumber()));
		newadmission.setPatientTrackingNumber(this.generateTrackingNumber());
		newadmission.setAdmittedBy(user);
		newadmission.setHospital(user.getHospital());
		System.out.println(newadmission.toString() + " THis is the admisssion to be saved");

		Admission savedadmission = admissionService.createNewPatientAdmission(newadmission);
		// Update the patient admission status
		Patient patient = savedadmission.getAdmittedPatient();
		patient.setAdmissionStatus(true);
		patientService.updatePatient(patient);
		if (savedadmission != null) {
			model.addAttribute("admission", savedadmission);
			boolean showAdmission = true;
			model.addAttribute("admissions", showAdmission);
			model.addAttribute("patients", savedadmission.getAdmittedPatient());
			model.addAttribute("message", "Admission is successful for " + savedadmission.getPatientTrackingNumber());
			return "redirect:/recptionist";

		}
		model.addAttribute("error", true);
		model.addAttribute("message", "The Admission Failed! Try Again");
		return "redirect:/recptionist";
	}

	@GetMapping("/admit/patient/{patientNumber}")
	public String admitPatient(Model model, @PathVariable String patientNumber, Principal principal) {
		if (patientNumber != null) {
			Patient result = patientService.findPatientByPatientNumber(patientNumber);
			// if the patient is found, we proceed with admission
			if (result != null) {
				model.addAttribute("patient", result);
				boolean patientresult = true;
				model.addAttribute("found", patientresult);
				AdmissionDto admission = new AdmissionDto();
				Iterable<Department> departemt = departemtService.findAllDepartemts();
				model.addAttribute("departemt", departemt);
				model.addAttribute("admission", admission);
				System.out.println("We reach this page");
				return "admit";
			}
			// If not found, go to registration page toregister a patient first
			Patient patient = new Patient();
			model.addAttribute("patient", patient);
			return "registration";
		}
		return "reception";

	}

	@GetMapping("/patient/update/{patientNumber}")
	public String closePatientAdmission(Model model, @PathVariable String patientNumber, Principal principal) {
		if (patientNumber != null) {
			boolean update = true;
			model.addAttribute("update", update);
			Patient patient = new Patient();
			model.addAttribute("patient", patient);
			model.addAttribute("patientNumber", patientNumber);
		}

		return "reception";

	}

	@PostMapping("/patient/update")
	public String closePatientAdmissions(Model model, PatientData pData, Principal principal) {
		if (pData.getPatientNumber() != null) {
			Patient result = patientService.findPatientByPatientNumber(pData.getPatientNumber());
			// if the patient is found, we proceed with closing admission
			if (result != null) {
				Admission admitepToday = admissionService.findBYpatientNumber(pData.getPatientNumber());
				admitepToday.setReleasedDate(LocalDateTime.now().toString());
				admissionService.update(admitepToday);
				result.setAdmissionStatus(false);
				patientService.updatePatient(result);

			}
		}
		return "redirect:/recptionist";

	}

	@GetMapping("/details/patient/{patientNumber}")
	public String showInformation(Model model, @PathVariable String patientNumber, Principal principal) {
		if (patientNumber != null) {
			Patient result = patientService.findPatientByPatientNumber(patientNumber);
			// if the patient is found, we proceed with admission
			if (result != null) {
				boolean patientInfo = true;
				model.addAttribute("patientInfo", patientInfo);
				model.addAttribute("patient", result);
				return "reception";
			}
		}

		return "redirect:/recptionist";

	}

	@GetMapping("/details/admission/{patientNumber}")
	public String showAdnissionInformation(Model model, @PathVariable String patientNumber, Principal principal) {
		String username = principal.getName();
		Employee doctor = userService.findDoctor(username);
		Hospital hospital = doctor.getHospital();
		Long id = hospital.getHospitalId();
		if (patientNumber != null) {
			long admissionNumber = admissionService.countAdmissionBypatient(patientNumber, id);
			boolean admissionInfo = true;
			List<Admission> results = admissionService.listAdmissionsByPatients(patientNumber, id);
			model.addAttribute("admissionList", results);
			model.addAttribute("admissionInfo", admissionInfo);
			model.addAttribute("admissionNumber", admissionNumber);
			return "reception";
		}

		return "redirect:/recptionist";

	}

	/**
	 * Generate tracking number.
	 *
	 * @return the string
	 */
	private String generateTrackingNumber() {
		return "TR-" + RandomStringUtils.randomNumeric(6).toUpperCase();
	}

	/**
	 * Generate patient number.
	 *
	 * @param patient the patient
	 * @return the string
	 */
	private String generatePatientNumber(Patient patient) {
		return "PN-" + RandomStringUtils.randomAlphanumeric(8).toUpperCase();
	}
}
