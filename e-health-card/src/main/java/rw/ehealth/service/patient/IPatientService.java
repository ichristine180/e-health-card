
package rw.ehealth.service.patient;

import rw.ehealth.model.Patient;

public interface IPatientService {

	String NAME = "patientService";

	/**
	 * @param patient
	 * @return
	 */
	Patient savePatientInfo(Patient patient);
	long countPatient();
	
	Patient findPatientByIdentificationNumber(String IdNumber);

}
