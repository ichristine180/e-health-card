
package rw.ehealth.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

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
import rw.ehealth.model.security.Role;
import rw.ehealth.service.admission.IAdmissionService;
import rw.ehealth.service.medical.IHospitalService;
import rw.ehealth.service.patient.IPatientService;
import rw.ehealth.service.user.DepartemtService;
import rw.ehealth.service.user.IEmployeeService;
import rw.ehealth.service.user.UserService;
import rw.ehealth.utils.AdmissionDto;
import rw.ehealth.utils.DoctorData;
import rw.ehealth.utils.IDGenerator;
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
		newadmission.setPatientTrackingNumber(IDGenerator.generateTrackingNumber());
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
				Employee activeUser = userService.findDoctor(principal.getName());
				Hospital hospital = activeUser.getHospital();
				Iterable<Department> departemt = departemtService.findPerHospital(hospital);
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

	@GetMapping("/department/{employeeId}")
	public String addDpt(Model model, @PathVariable Long employeeId, Principal principal) {
		if (employeeId != null) {
			Employee result = userService.findByEmployeeId(employeeId);
			Hospital hospital = result.getHospital();
			// if the Doctor is found, we proceed
			if (result != null) {
				DoctorData doctor = new DoctorData();
				Iterable<Department> departemt = departemtService.findPerHospital(hospital);
				model.addAttribute("departemtperhospital", departemt);
				model.addAttribute("doctor", doctor);
				model.addAttribute("doctors", result);
				return "registration";
			}

		}
		return "redirect:/";

	}

	@PostMapping("/updateEmployee")
	public String addDpt(@ModelAttribute("user") @Valid DoctorData user, Model model) {
		if (user.getDepertmentName().isEmpty() == false && user.getId() != null) {
			Employee result = userService.findByEmployeeId(user.getId());
			Department dpt = departemtService.findPerName(user.getDepertmentName());
			employeeService.addEmployeeDepartment(result, dpt);
			return "redirect:/";
		}
		return "redirect:/";
	}

}
