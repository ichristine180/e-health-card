package rw.ehealth;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import rw.ehealth.model.Admission;
import rw.ehealth.model.Department;
import rw.ehealth.model.Employee;
import rw.ehealth.model.Hospital;
import rw.ehealth.model.Patient;
import rw.ehealth.model.User;
import rw.ehealth.model.security.Role;
import rw.ehealth.model.security.UserRole;
import rw.ehealth.service.admission.IAdmissionService;
import rw.ehealth.service.hospital.IHospitalService;
import rw.ehealth.service.patient.IPatientService;
import rw.ehealth.service.user.DepartemtService;
import rw.ehealth.service.user.IUserService;
import rw.ehealth.utils.IDGenerator;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class EHealthCardApplicationTests {

	@Autowired
	private IHospitalService hospitalService;

	@Autowired
	private IUserService userService;
	@Autowired
	private IPatientService patientService;
	@Autowired
	private DepartemtService departemtService;
	Hospital createdHospital;
	@Test
	public void testCreateHospital() {

		Hospital hospital = new Hospital();
		hospital.setAddress("Nyamata");
		hospital.setHospitalCode("Hn");
		hospital.setHospitalName("Hopital_Nyamata");

	createdHospital = hospitalService.createHospital(hospital);

		Assert.assertEquals(hospital.getHospitalName(), createdHospital.getHospitalName());

	}

	@Test
	public void testCreatePatient() {

		Patient patient = new Patient();
		patient.setAddress("KK Ndera");
		patient.setAdmissionStatus(false);
		patient.setDateOfBirth("1967-10-02");
		patient.setFname("Christie");
		patient.setLname("Mukamana");
		patient.setGender("F");
		patient.setHospital("KFH");
		patient.setIdentificationNumber("1234567891234567");

		patient.setPatientNumber(IDGenerator.generatePatientNumber(patient));

		Patient createdPatient = patientService.savePatientInfo(patient);
		Assert.assertEquals(patient.getAddress(), createdPatient.getAddress());

	}
	@Test
	public void testCreateDoctor() {
		Employee doc = new Employee();
		doc.setEmail("ichristine180@gmail.com");
		doc.setFname("chris");
		doc.setLname("mwiza");
		doc.setTimestamp(LocalDate.now().toString());
		doc.setPhone("0788028913");
		Hospital hospitals = hospitalService.findByHospitalname("Hopital_Nyamata");
		doc.setHospital(hospitals);
		Department departemt = departemtService.findPerName("Laboratory");
		doc.setDepertment(departemt);
		User myuser = new User();
		myuser.setUsername("ichristine180@gmail.com");
		myuser.setPassword("pass");
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(myuser, userService.findByName("Role_GENERAL_DOCTOR")));
		myuser.setDoctor(doc);
		doc.setUser(myuser);
		userService.createUser(myuser);
		User empoUser = userService.createUser(myuser, userRoles);
		Assert.assertEquals(myuser.getUsername(), empoUser.getUsername());

	}

}
