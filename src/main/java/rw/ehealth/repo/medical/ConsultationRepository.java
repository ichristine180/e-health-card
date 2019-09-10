
package rw.ehealth.repo.medical;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.ehealth.model.Admission;
import rw.ehealth.model.Consultation;
import rw.ehealth.model.Employee;
import rw.ehealth.model.Hospital;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

	/**
	 * Find by admission.
	 *
	 * @param info the info
	 * @return the consultation
	 */
	Consultation findByAdmission(Admission info);

	/**
	 * Find by doctor.
	 *
	 * @param doctor the doctor
	 * @return the list
	 */
	List<Consultation> findByDoctor(Employee doctor);

	/**
	 * Find all info by patient.
	 *
	 * @param patientNumber the patient number
	 * @return the list
	 */
	@Query("SELECT c from Consultation c JOIN c.admission a JOIN a.admittedPatient p WHERE p.patientNumber= :patientNumber")
	List<Consultation> findAllInfoByPatient(@Param("patientNumber") String patientNumber);

	@Query("SELECT count(h.hospitalId) from Consultation c  JOIN c.hospital h  JOIN c.admission a JOIN a.departement d "
			+ "WHERE h.hospitalId=:hospitalId "
			+ "AND a.status is not 'COMPLETE'"
			+ "AND a.departement.departmentId=:departmentId")
	long countConsultation(@Param("hospitalId") Long hospitalId,
			@Param("departmentId") Long departmentId);

	/**
	 * Find by consultation id.
	 *
	 * @param id the id
	 * @return the consultation
	 */
	Consultation findByConsultationId(Long id);

	/**
	 * Find by patient trucking number.
	 *
	 * @param patientTrackingNumber the patient tracking number
	 * @return the consultation
	 */
	@Query("SELECT c from Consultation c JOIN c.admission a WHERE a.patientTrackingNumber=:patientTrackingNumber")
	Consultation findByPatientTruckingNumber(@Param("patientTrackingNumber") String patientTrackingNumber);

	@Query("SELECT c from Consultation c  JOIN c.hospital  JOIN c.admission a JOIN a.departement d "
			+ "WHERE c.hospital.hospitalId=:hospitalId "
			+ "AND a.status is not 'COMPLETE'"
			+ "AND a.departement.departmentId=:departmentId")
	List<Consultation> findConsuledPatients(@Param("hospitalId") Long hospitalId,
			@Param("departmentId") Long departmentId);

	/**
	 * Count by gender.
	 *
	 * @param hospitalId the hospital id
	 * @param gender     the gender
	 * @return the long
	 */
	@Query("SELECT count(p.gender) from Consultation c JOIN c.admission a JOIN a.admittedPatient p JOIN c.hospital h WHERE p.gender=:gender and h.hospitalId=:hospitalId ")
	Long CountByGender(@Param("hospitalId") Long hospitalId, @Param("gender") String gender);

	long countByHospital(Hospital hospital);

}
