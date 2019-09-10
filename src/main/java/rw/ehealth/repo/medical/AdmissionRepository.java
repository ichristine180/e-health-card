
package rw.ehealth.repo.medical;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.ehealth.model.Admission;
import rw.ehealth.model.Patient;
import rw.ehealth.report.AdmissionReport;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {

	/**
	 * @param id
	 * @return
	 */

	Admission findByAdmissionId(Long id);
	@Query("SELECT a from Admission a Join a.hospital h WHERE h.hospitalId=:hospitalId")
	List<Admission> countAllAdmission(@Param("hospitalId") Long hospitalId);
	/**
	 * @param patient
	 * @return
	 */
	List<Admission> findByAdmittedPatient(Patient patient);

	/*
	 *
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
	 */
	@Override
	List<Admission> findAll();

	/**
	 * @param patientTrackingNumber
	 * @return
	 */
	@Query("SELECT a from Admission a Join a.admittedPatient p WHERE p.patientNumber=:patientNumber and  a.status is 'COMPLETE'")
	List<Admission> listcompleteAdmissions(@Param("patientNumber") String patientNumber);
	Admission findByPatientTrackingNumber(String patientTrackingNumber);

	/**
	 * Count admission info.
	 *
	 * @param hospitalId the hospital id
	 * @return the long
	 */
	@Query("SELECT count(h.hospitalId) from Admission a Join a.hospital h WHERE h.hospitalId=:hospitalId and a.releasedDate is null")
	long countAdmission(@Param("hospitalId") Long hospitalId);

	@Query("SELECT count(h.hospitalId) from Admission a Join a.hospital h WHERE h.hospitalId=:hospitalId and a.status is 'PENDING'")
	long countAdmissionFordoctor(@Param("hospitalId") Long hospitalId);

	@Query("SELECT a from Admission a JOIN a.hospital h WHERE h.hospitalId=:hospitalId and a.releasedDate is null")
	List<Admission> allAdmissions(@Param("hospitalId") Long hospitalId);

	@Query("SELECT count(p.patientNumber) from Admission t JOIN t.admittedPatient p  JOIN t.hospital h  WHERE p.patientNumber=:patientNumber and h.hospitalId = :hospitalId ")
	long countAdmissionBypatient(@Param("patientNumber") String patientNumber,

			@Param("hospitalId") Long hospitalId);

	@Query("SELECT a from Admission a Join a.admittedPatient p Join a.hospital h WHERE p.patientNumber=:patientNumber and  h.hospitalId=:hospitalId")
	List<Admission> listAdmissionsByPatients(@Param("patientNumber") String patientNumber,

			@Param("hospitalId") Long hospitalId);

	@Query("SELECT a from Admission a Join a.hospital h JOIN a.departement d WHERE h.hospitalId=:hospitalId and a.releasedDate is null and a.status is 'PENDING' and d.id=:id")
	List<Admission> Admissions(@Param("hospitalId") Long hospitalId,

			@Param("id") Long id);
	@Query("SELECT a from Admission a Join a.hospital h JOIN a.departement d WHERE h.hospitalId=:hospitalId and a.releasedDate is null and a.status is 'MIDLE' and d.id=:id")
	List<Admission> midleAdmissions(@Param("hospitalId") Long hospitalId,

			@Param("id") Long id);

	// boolean checkToUpdade(@Param("hospitalId") Long hospitalId,@Param("pa"))

	@Query("SELECT a from Admission a Join a.admittedPatient p WHERE p.patientNumber=:patientNumber order by a.admissionDate")
	List<Admission> listInfosByPatients(@Param("patientNumber") String patientNumber);

	@Query("SELECT a from Admission a JOIN a.admittedPatient p WHERE p.patientNumber=:patientNumber and a.releasedDate=null")
	Admission findBYpatientNumber(@Param("patientNumber") String patientNumber);

	@Query("SELECT a from Admission a JOIN a.admittedPatient p WHERE p.patientNumber=:patientNumber")
	Admission findInfoBYpatientNumber(@Param("patientNumber") String patientNumber);

	@Query("SELECT DISTINCT(h) from Admission a JOIN a.admittedPatient p JOIN a.hospital h WHERE p.patientNumber=:patientNumber")
	List<Admission> findHospitalBYpatientNumber(@Param("patientNumber") String patientNumber);

	@Query("SELECT a from Admission a JOIN a.admittedPatient p JOIN a.hospital h WHERE p.patientNumber=:patientNumber and h.hospitalId=:hospitalId")
	List<Admission> findAdmissionBYpatientNumber(@Param("patientNumber") String patientNumber,

			@Param("hospitalId") Long hospitalId);

	@Query("SELECT new rw.ehealth.report.AdmissionReport(a,p,count(p.patientNumber)) from Admission a JOIN a.admittedPatient p Join a.admittedBy d WHERE d.email=:email group BY p.patientNumber")
	List<AdmissionReport> findAdmissionByDoctor(@Param("email") String email);

	@Query("SELECT new rw.ehealth.report.AdmissionReport(a,p,count(p.patientNumber)) from Admission a JOIN a.admittedPatient p   join a.hospital h WHERE p.gender=:gender and h.hospitalId=:hospitalId group BY p.patientNumber")
	List<AdmissionReport> findByGender(@Param("gender") String gender, @Param("hospitalId") Long hospitalId);

	/**
	 * @param patientNumber
	 * @return
	 */
	@Query("SELECT a from Admission a where a.admittedPatient.patientNumber =:patientNumber and releasedDate is null")
	Admission findActiveAdmission(@Param("patientNumber") String patientNumber);

	@Query("SELECT a from Admission a Join a.admittedPatient p Join a.hospital h WHERE p.patientNumber=:patientNumber and a.releasedDate is null")
	Admission listAdmissions(@Param("patientNumber") String patientNumber);

}
