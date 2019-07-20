
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

	@Query("SELECT count(h.hospitalId) from AdmissionInfo a Join a.admittedPatient p  Join a.doctor d Join d.hospital h WHERE h.hospitalId=:hospitalId and p.admissionStatus = :admissionStatus")
	long countAdmissionInfo(@Param("hospitalId") Long hospitalId,@Param("admissionStatus") boolean admissionStatus);

	@Query("SELECT a from AdmissionInfo a Join a.admittedPatient p Join a.doctor d Join d.hospital h WHERE h.hospitalId=:hospitalId and p.admissionStatus=:admissionStatus")
	List<AdmissionInfo> allAdmissionInfos(@Param("hospitalId") Long hospitalId,@Param("admissionStatus") boolean admissionStatus);

	@Query("SELECT count(p.patientNumber) from AdmissionInfo t JOIN t.admittedPatient p JOIN t.doctor d JOIN d.hospital h  WHERE p.patientNumber=:patientNumber and h.hospitalName = :hospitalName ")
	long countAdmissionBypatient(@Param("patientNumber") String patientNumber,
			@Param("hospitalName") String hospitalName);
	@Query("SELECT a from AdmissionInfo a Join a.admittedPatient p Join a.doctor d Join d.hospital h WHERE p.patientNumber=:patientNumber and  h.hospitalName=:hospitalName")
	List<AdmissionInfo>listAdmissionInfosByPatients(@Param("patientNumber") String patientNumber,
			@Param("hospitalName") String hospitalName);
	
	
	
	@Query("SELECT a from AdmissionInfo a Join a.admittedPatient p Join a.doctor d Join d.hospital h WHERE h.hospitalId=:hospitalId and p.admissionStatus=:admissionStatus and a.departement=:departement")
	List<AdmissionInfo> AdmissionInfos(@Param("hospitalId") Long hospitalId,@Param("admissionStatus") boolean admissionStatus,@Param("departement") String departement);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
