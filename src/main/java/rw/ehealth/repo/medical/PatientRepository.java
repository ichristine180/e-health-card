
package rw.ehealth.repo.medical;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.ehealth.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	/**
	 * Find by patient id.
	 *
	 * @param id the id
	 * @return the patient
	 */
	@Query("SELECT p from Patient p WHERE p.admissionStatus=false")
	List<Patient>findpAll();
	List<Patient>findAll();

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

	/**
	 * Find by admission status.
	 *
	 * @param patientNumber the patient number
	 * @return the patient
	 */
	@Query("SELECT p from Patient p WHERE p.admissionStatus=true and p.patientNumber=:patientNumber")
	Patient findByAdmissionStatus(@Param("patientNumber") String patientNumber);
}
