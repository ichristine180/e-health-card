
package rw.ehealth.repo.medical;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.ehealth.model.Patient;



public interface PatientRepository extends JpaRepository<Patient, Long> {

	Patient findByPatientId(Long id);

	Patient findByIdentificationNumber(String identificationNumber);
	long count();

}
