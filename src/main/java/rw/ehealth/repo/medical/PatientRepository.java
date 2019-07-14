
package rw.ehealth.repo.medical;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.ehealth.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	/**
	 * Find by patient id.
	 *
	 * @param id the id
	 * @return the patient
	 */
	Patient findByPatientId(Long id);

	/**
	 * Find by identification number.
	 *
	 * @param identificationNumber the identification number
	 * @return the patient
	 */
	Patient findByIdentificationNumber(String identificationNumber);

	@Override
	long count();

	/**
	 * Find by patient number.
	 *
	 * @param patientNumber the patient number
	 * @return the patient
	 */
	Patient findByPatientNumber(String patientNumber);

}
