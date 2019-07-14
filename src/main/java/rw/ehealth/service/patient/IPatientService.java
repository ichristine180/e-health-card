
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

	/**
	 * Find patient by identification number.
	 *
	 * @param IdNumber the id number
	 * @return the patient
	 */
	Patient findPatientByIdentificationNumber(String IdNumber);

	/**
	 * Find patient by patient number.
	 *
	 * @param patientNumber the patient number
	 * @return the patient
	 */
	Patient findPatientByPatientNumber(String patientNumber);

}
