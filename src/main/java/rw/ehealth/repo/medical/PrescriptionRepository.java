
package rw.ehealth.repo.medical;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.ehealth.model.Admission;
import rw.ehealth.model.Employee;
import rw.ehealth.model.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

	/**
	 * @param info
	 * @return
	 */

	Prescription findByAdmission(Admission info);

	/**
	 * @param doctor
	 * @return
	 */

	List<Prescription> findByPrescribedBy(Employee doctor);

	/**
	 * @param id
	 * @return
	 */
	Prescription findById(Long id);

	/**
	 * Find by patient trucking number.
	 *
	 * @param patientTrackingNumber the patient tracking number
	 * @return the prescription
	 */
	@Query("SELECT p FROM Prescription p JOIN p.admission a WHERE a.patientTrackingNumber=:patientTrackingNumber")
	Prescription findByPatientTruckingNumber(@Param("patientTrackingNumber") String patientTrackingNumber);

	/**
	 * @param admission
	 * @return
	 */
	@Query("SELECT p from Prescription p where p.admission=:admission and p.name is not null")
	Prescription findPrescribedByAdmission(@Param("admission") Admission admission);

}
