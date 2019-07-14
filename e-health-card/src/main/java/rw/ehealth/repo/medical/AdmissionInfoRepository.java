
package rw.ehealth.repo.medical;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.Patient;

public interface AdmissionInfoRepository extends JpaRepository<AdmissionInfo, Long> {

	/**
	 * @param id
	 * @return
	 */
	AdmissionInfo findByAdmissionId(Long id);

	/**
	 * @param patient
	 * @return
	 */
	List<AdmissionInfo> findByAdmittedPatient(Patient patient);

	/*
	 *
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
	 */
	@Override
	List<AdmissionInfo> findAll();

	/**
	 * @param patientTrackingNumber
	 * @return
	 */
	AdmissionInfo findByPatientTrackingNumber(String patientTrackingNumber);
	@Query("SELECT count(p.username) from AdmissionInfo t  JOIN t.doctor  a JOIN a.user p  WHERE p.username=:username")
	long countAdmissionInfo(@Param("username") String username);

}
