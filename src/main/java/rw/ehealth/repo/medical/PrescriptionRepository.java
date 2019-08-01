
package rw.ehealth.repo.medical;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.Doctor;
import rw.ehealth.model.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

	/**
	 * @param info
	 * @return
	 */
	Prescription findByAdmissionInfo(AdmissionInfo info);

	/**
	 * @param doctor
	 * @return
	 */
	List<Prescription> findByDoctor(Doctor doctor);

	/**
	 * @param id
	 * @return
	 */
	Prescription findById(Long id);
	@Query("SELECT p FROM Prescription p JOIN p.admissionInfo a WHERE a.patientTrackingNumber=:patientTrackingNumber")
	Prescription findByPatientTruckingNumber(@Param("patientTrackingNumber") String patientTrackingNumber);

}
