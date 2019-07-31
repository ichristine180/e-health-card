
package rw.ehealth.repo.medical;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.Consultation;
import rw.ehealth.model.Doctor;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

	/**
	 * @param info
	 * @return
	 */
	Consultation findByAdmissionInfo(AdmissionInfo info);

	/**
	 * @param doctor
	 * @return
	 */
	List<Consultation> findByDoctor(Doctor doctor);
	@Query("SELECT c from Consultation c JOIN c.admissionInfo a JOIN a.admittedPatient p WHERE p.patientNumber= :patientNumber")
	List<Consultation> findAllInfoByPatient(@Param("patientNumber") String patientNumber);

	/**
	 * @param id
	 * @return
	 */
	Consultation findByConsultationId(Long id);
	@Query("SELECT c from Consultation c JOIN c.admissionInfo a WHERE a.patientTrackingNumber=:patientTrackingNumber")
	Consultation findByPatientTruckingNumber(@Param("patientTrackingNumber") String patientTrackingNumber);
	

}
