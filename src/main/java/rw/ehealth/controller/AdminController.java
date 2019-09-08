
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
import rw.ehealth.model.User;
import rw.ehealth.model.security.Role;
import rw.ehealth.model.security.UserRole;
import rw.ehealth.service.exams.ExamService;
import rw.ehealth.service.hospital.IHospitalService;
import rw.ehealth.service.user.DepartemtService;
import rw.ehealth.service.user.UserService;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private IHospitalService hospitalService;

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

	@PostMapping("/hospregistration")
	public String registerhospital(@RequestParam(value = "examId", required = false) int[] examId,
			@RequestParam(value = "departmentId", required = false) Long[] departmentId, @ModelAttribute("hospital") @Valid Hospital hdata, BindingResult results ,
			Model model, Principal principal) {
		if (results.hasErrors()) {
			model.addAttribute("examss", examService.findExams());
			model.addAttribute("departments", departemtService.findAllDepartemts());
			return "registration";
		}
		if (examId == null){
			model.addAttribute("message","Please select exams");
			model.addAttribute("examss", examService.findExams());
			model.addAttribute("departments", departemtService.findAllDepartemts());
			return "registration";
		}
		if (departmentId == null) {
			model.addAttribute("Dmessage","Please select Departement");
			model.addAttribute("examss", examService.findExams());
			model.addAttribute("departments", departemtService.findAllDepartemts());
			return "registration";
		}
		else {
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
		model.addAttribute("Hmessage","This hospital is already registered");
		model.addAttribute("examss", examService.findExams());
		model.addAttribute("departments", departemtService.findAllDepartemts());
		return "registration";
	}

	@GetMapping("/docregistration")
	public String registerDoctor(Model model) {
		Employee user = new Employee();
		Iterable<Role> roles = userService.findAll();
		Iterable<Hospital> hospitals = hospitalService.findAllHospitals();
		model.addAttribute("hospitals", hospitals);
		model.addAttribute("roles", roles);
		model.addAttribute("user", user);

		boolean doctors = true;
		model.addAttribute("doctors", doctors);
		return "registration";
	}

	@PostMapping("/docregistration")
	public String adddoctor(@ModelAttribute("user") @Valid Employee user, BindingResult results, Model model) {
		if (results.hasErrors()) {
			Iterable<Role> roles = userService.findAll();
			Iterable<Hospital> hospitals = hospitalService.findAllHospitals();
			model.addAttribute("hospitals", hospitals);
			model.addAttribute("roles", roles);
			model.addAttribute("doctors", true);
			System.out.println("Errors Found" + results.getAllErrors().toString());
			return "registration";
		}
		System.out.println(user.toString() + " User to be saved");
		Department departemt;
		if (user.getHospital() != null && user.getEmail() != null) {
			Employee doc = new Employee();
			doc.setEmail(user.getEmail());
			doc.setFname(user.getFname());
			doc.setLname(user.getLname());
			doc.setTimestamp(LocalDate.now().toString());
			doc.setPhone(user.getPhone());
			Hospital hospitals = hospitalService.findByHospitalname(user.getHospital().getHospitalName());
			doc.setHospital(hospitals);
			if (user.getUser().getUsername().equals("ROLE_RECEPTIONIST")) {
				departemt = departemtService.findPerName("Reception");
				doc.setDepertment(departemt);
			} else if (user.getUser().getUsername().equals("ROLE_GENERAL_DOCTOR")) {
				departemt = departemtService.findPerName("General Practitioner");
				doc.setDepertment(departemt);
			} else if (user.getUser().getUsername().equals("ROLE_LABORATORY_DOCTOR")) {
				departemt = departemtService.findPerName("Laboratory");
				doc.setDepertment(departemt);
			}
			if (userService.checkUsernameExists(user.getEmail())) {
				if (userService.checkUsernameExists(user.getEmail())) {
					Iterable<Role> roles = userService.findAll();
					Iterable<Hospital> hospitalss = hospitalService.findAllHospitals();
					model.addAttribute("hospitals", hospitalss);
					model.addAttribute("roles", roles);
					model.addAttribute("doctors", true);
					System.out.println("Errors Found" + results.getAllErrors().toString());
					model.addAttribute("message", "There is user with this Email ");
					System.out.println("email exists");
					return "registration";
				}
			}
			User myuser = new User();
			myuser.setUsername(user.getEmail());
			myuser.setPassword("pass");
			Set<UserRole> userRoles = new HashSet<>();
			userRoles.add(new UserRole(myuser, userService.findByName(user.getUser().getUsername())));
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
