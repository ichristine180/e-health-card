
package rw.ehealth.controller.reports;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import rw.ehealth.model.Hospital;
import rw.ehealth.service.admission.IAdmissionService;
import rw.ehealth.service.hospital.IHospitalService;
import rw.ehealth.service.patient.IPatientService;
import rw.ehealth.service.user.IUserService;
import rw.ehealth.utils.report.DepartmentReport;

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
		model.addAttribute("patientCount", patientService.findpAll().size());
		model.addAttribute("userCount", userService.findUserList().size());
		return "report/admin";
	}

	@GetMapping("admin/report/{hospitalCode}")
	public String hospitalReportPag(Model model, @PathVariable String hospitalCode) {

		Hospital hospital = hospitalService.findHospitalByCode(hospitalCode);

		model.addAttribute("hospitalName", hospital.getHospitalName());
		model.addAttribute("hospital", hospital);
		model.addAttribute("admissions", hospital.getAdmissions());
		model.addAttribute("departments", hospital.getDepartments());
		model.addAttribute("admissionCount", hospital.getAdmissions().size());
		model.addAttribute("departmentCount", hospital.getDepartments().size());

		return "report/h_report_page";
	}

	@GetMapping("/admin/report/departments/{hospitalCode}")
	public String hospitalDepartmentsReportPage(Model model, @PathVariable String hospitalCode) {

		Hospital hospital = hospitalService.findHospitalByCode(hospitalCode);

		List<DepartmentReport> dReports = hospitalService.getDepartmentStatistics(hospitalCode);

		model.addAttribute("dReports", dReports);
		model.addAttribute("hospital", hospital);

		return "report/h_report_department_page";
	}

}
