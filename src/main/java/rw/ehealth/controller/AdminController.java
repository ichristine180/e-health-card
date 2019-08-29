
package rw.ehealth.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import rw.ehealth.model.Department;
import rw.ehealth.model.Employee;
import rw.ehealth.model.Hospital;
import rw.ehealth.model.MedicalExam;
import rw.ehealth.model.Patient;
import rw.ehealth.model.User;
import rw.ehealth.model.security.UserRole;
import rw.ehealth.service.exams.ExamService;
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
	@Autowired
	private ExamService examService;

	@GetMapping(value = "/hospregistration")
	public String registerHospital(Model model) {
		Hospital newhospital = new Hospital();
		model.addAttribute("hospital", newhospital);
		model.addAttribute("examss", examService.findExams());
		model.addAttribute("departments", departemtService.findAllDepartemts());
		return "registration";
	}

	@PostMapping("/registration")
	public String registerPatient(@ModelAttribute @Valid Patient patient, BindingResult results, Principal principal,
			Model model) {
		if (results.hasErrors()) {
			System.out.println("Validation Errors occured");
			return "registration";
		}
		System.out.println("No Errors-then proceed");
		String username = principal.getName();
		Employee doctor = userService.findDoctor(username);
		Hospital hospital = doctor.getHospital();
		String hospitalName = hospital.getHospitalName();
		if (patient.getIdentificationNumber().isEmpty() == false) {
			if (patient.getIdentificationNumber() != null) {
				Patient existingPatient = patientService
						.findPatientByIdentificationNumber(patient.getIdentificationNumber());

				if (existingPatient != null) {
					model.addAttribute("message", "There is Patient with this IdentificationNumber");
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
	public String registerhospital(@RequestParam(value = "examId", required = false) int[] examId,
			@RequestParam(value = "departmentId", required = false) Long[] departmentId, @ModelAttribute Hospital hdata,
			Model model, Principal principal) {
		if (examId != null && departmentId != null) {

			Hospital hospital = new Hospital();
			hospital.setAddress(hdata.getAddress());
			hospital.setHospitalCode(hdata.getHospitalCode());
			hospital.setHospitalName(hdata.getHospitalName());
			hospital.setType(hdata.getType());

			Set<MedicalExam> selectedMedicalExam = new HashSet<>();
			for (int i = 0; i < examId.length; i++) {
				MedicalExam exam = examService.findHospitalById(examId[i]);
				selectedMedicalExam.add(exam);
			}
			Set<Department> selectedMedicalDepartment = new HashSet<>();
			for (int i = 0; i < departmentId.length; i++) {
				Department dpt = departemtService.findPerId(departmentId[i]);
				selectedMedicalDepartment.add(dpt);
			}

			hospital.setDepartments(selectedMedicalDepartment);
			hospital.setExams(selectedMedicalExam);
			Hospital savedHospital = hospitalService.createHospital(hospital);
			if (savedHospital != null) {
				model.addAttribute("hospital", hospitalService.findHospitalById(savedHospital.getHospitalId()));
				return "registrationSuccess";
			}

		}
		return "registration";
	}

	@PostMapping("/docregistration")
	public String adddoctor(@ModelAttribute("user") @Valid DoctorData user, Model model) {
		Department departemt;
		if (user.getHospitalname().isEmpty() == false && user.getEmail().isEmpty() == false) {
			Employee doc = new Employee();
			doc.setEmail(user.getEmail());
			doc.setFname(user.getFname());
			doc.setLname(user.getLname());
			doc.setTimestamp(LocalDate.now().toString());
			doc.setPhone(user.getPhone());
			Hospital hospitals = hospitalService.findByHospitalname(user.getHospitalname());
			doc.setHospital(hospitals);
			if (user.getRoleName().equals("ROLE_RECEPTIONIST")) {
				departemt = departemtService.findPerName("Reception");
				doc.setDepertment(departemt);
			} else if (user.getRoleName().equals("ROLE_GENERAL_DOCTOR")) {
				departemt = departemtService.findPerName("General Practitioner");
				doc.setDepertment(departemt);
			} else if (user.getRoleName().equals("ROLE_LABORATORY_DOCTOR")) {
				departemt = departemtService.findPerName("Laboratory");
				doc.setDepertment(departemt);
			}
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
			User empoUser = userService.createUser(myuser, userRoles);
			if (empoUser != null) {
				model.addAttribute("user", userService.findByUsername(empoUser.getUsername()));
				return "registrationSuccess";
			}
		}
		return "redirect:/docregistration";
	}

	@GetMapping("/listHospital")
	public String adminReportHomepage(Model model) {
		model.addAttribute("hospitals", hospitalService.findAllHospitals());
		model.addAttribute("hospitalCount", hospitalService.findAllHospitals().size());
		model.addAttribute("userCount", userService.findUserList().size());
		return "report";
	}

}
