package rw.ehealth;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import rw.ehealth.model.Hospital;
import rw.ehealth.model.Patient;
import rw.ehealth.service.hospital.IHospitalService;
import rw.ehealth.service.patient.IPatientService;
import rw.ehealth.utils.IDGenerator;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class EHealthCardApplicationTests {

	@Autowired
	private IHospitalService hospitalService;

	@Autowired
	private IPatientService patientService;

	@Test
	public void testCreateHospital() {

		Hospital hospital = new Hospital();
		hospital.setAddress("Ndera");
		hospital.setHospitalCode("HVP");
		hospital.setHospitalName("Ndera-Mu Mutwe");

		Hospital createdHospital = hospitalService.createHospital(hospital);

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
		patient.setIdentificationNumber("1199970000852013");

		patient.setPatientNumber(IDGenerator.generatePatientNumber(patient));

		Patient createdPatient = patientService.savePatientInfo(patient);
		Assert.assertEquals(patient.getAddress(), createdPatient.getAddress());

	}

}
