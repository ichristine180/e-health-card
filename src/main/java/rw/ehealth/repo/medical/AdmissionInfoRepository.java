
package rw.ehealth.repo.medical;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.Patient;
import rw.ehealth.report.AdmissionReport;

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

	/**
	 * Count admission info.
	 *
	 * @param hospitalId the hospital id
	 * @return the long
	 */
	@Query("SELECT count(h.hospitalId) from AdmissionInfo a Join a.admittedPatient p  Join a.doctor d Join d.hospital h WHERE h.hospitalId=:hospitalId and a.releasedDate is null")
	long countAdmissionInfo(@Param("hospitalId") Long hospitalId);

	@Query("SELECT a from AdmissionInfo a Join a.admittedPatient p Join a.doctor d Join d.hospital h WHERE h.hospitalId=:hospitalId and a.releasedDate is null")
	List<AdmissionInfo> allAdmissionInfos(@Param("hospitalId") Long hospitalId);

	@Query("SELECT count(p.patientNumber) from AdmissionInfo t JOIN t.admittedPatient p JOIN t.doctor d JOIN d.hospital h  WHERE p.patientNumber=:patientNumber and h.hospitalName = :hospitalName ")
	long countAdmissionBypatient(@Param("patientNumber") String patientNumber,
			@Param("hospitalName") String hospitalName);

	@Query("SELECT a from AdmissionInfo a Join a.admittedPatient p Join a.doctor d Join d.hospital h WHERE p.patientNumber=:patientNumber and  h.hospitalName=:hospitalName")
	List<AdmissionInfo> listAdmissionInfosByPatients(@Param("patientNumber") String patientNumber,
			@Param("hospitalName") String hospitalName);

	@Query("SELECT a from AdmissionInfo a Join a.admittedPatient p Join a.doctor d Join d.hospital h JOIN a.departement d WHERE h.hospitalId=:hospitalId and p.admissionStatus=:admissionStatus and d.name=:name")
	List<AdmissionInfo> AdmissionInfos(@Param("hospitalId") Long hospitalId,
			@Param("admissionStatus") boolean admissionStatus, @Param("name") String name);

	// boolean checkToUpdade(@Param("hospitalId") Long hospitalId,@Param("pa"))

	@Query("SELECT a from AdmissionInfo a Join a.admittedPatient p WHERE p.patientNumber=:patientNumber order by a.admissionDate")
	List<AdmissionInfo> listInfosByPatients(@Param("patientNumber") String patientNumber);

	@Query("SELECT a from AdmissionInfo a JOIN a.admittedPatient p WHERE p.patientNumber=:patientNumber and a.releasedDate=null")
	AdmissionInfo findBYpatientNumber(@Param("patientNumber") String patientNumber);

	@Query("SELECT a from AdmissionInfo a JOIN a.admittedPatient p WHERE p.patientNumber=:patientNumber")
	AdmissionInfo findInfoBYpatientNumber(@Param("patientNumber") String patientNumber);

	@Query("SELECT DISTINCT(h) from AdmissionInfo a JOIN a.admittedPatient p JOIN a.doctor d JOIN d.hospital h WHERE p.patientNumber=:patientNumber")
	List<AdmissionInfo> findHospitalBYpatientNumber(@Param("patientNumber") String patientNumber);

	@Query("SELECT a from AdmissionInfo a JOIN a.admittedPatient p JOIN a.doctor d JOIN d.hospital h WHERE p.patientNumber=:patientNumber and h.hospitalId=:hospitalId")
	List<AdmissionInfo> findAdmissionInfoBYpatientNumber(@Param("patientNumber") String patientNumber,
			@Param("hospitalId") Long hospitalId);

	@Query("SELECT new rw.ehealth.report.AdmissionReport(a,p,count(p.patientNumber)) from AdmissionInfo a JOIN a.admittedPatient p Join a.doctor d WHERE d.email=:email group BY p.patientNumber")
	List<AdmissionReport>findAdmissionByDoctor(@Param("email") String email);

	@Query("SELECT new rw.ehealth.report.AdmissionReport(a,p,count(p.patientNumber)) from AdmissionInfo a JOIN a.admittedPatient p  Join a.doctor d join d.hospital h WHERE p.gender=:gender and h.hospitalId=:hospitalId group BY p.patientNumber")
	List<AdmissionReport> findByGender(@Param("gender") String gender,@Param("hospitalId") Long hospitalId);

}
