
package rw.ehealth.repo.medical;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rw.ehealth.model.Admission;
import rw.ehealth.model.ExamRecord;
import rw.ehealth.model.Hospital;
import rw.ehealth.model.MedicalExam;
import rw.ehealth.report.ExamReport;

@Repository
public interface ExamRecordRepository extends JpaRepository<ExamRecord, Long> {

	/**
	 * Find pati exam records.
	 *
	 * @param hospitalId the hospital id
	 * @return the list
	 */
	@Query("SELECT count(distinct a.patientTrackingNumber) FROM ExamRecord e JOIN e.admissionInfo a  JOIN e.hospital h WHERE h.hospitalId=:hospitalId and e.results is null")
	Long countPatient(@Param("hospitalId") Long hospitalId);

	/**
	 * Find pati exam record.
	 *
	 * @param id the id
	 * @return the list
	 */
	@Query("SELECT e FROM ExamRecord e JOIN e.admissionInfo a  JOIN e.hospital h WHERE e.results = null  and h.hospitalId = :hospitalId GROUP BY a.admissionId")
	List<ExamRecord> findPatiExamRecord(@Param("hospitalId") Long id);

	/**
	 * Find by hospital.
	 *
	 * @param hospital the hospital
	 * @return the list
	 */
	List<ExamRecord> findByHospital(Hospital hospital);

	/**
	 * Find active exam record by hospital.
	 *
	 * @param hospital the hospital
	 * @return the list
	 */
	@Query("SELECT e from ExamRecord e where e.hospital=:hospital and e.closedWithResult is false group by e.admissionInfo")
	List<ExamRecord> findActiveExamRecordByHospital(@Param("hospital") Hospital hospital);

	/**
	 * Find exam records by patient.
	 *
	 * @param patientTrackingNumber the patient tracking number
	 * @return the list
	 */
	@Query("SELECT e,ex FROM ExamRecord e  JOIN e.medicalExam ex JOIN e.admissionInfo a WHERE a.patientTrackingNumber=:patientTrackingNumber")
	List<ExamRecord> findExamRecordByPatient(@Param("patientTrackingNumber") String patientTrackingNumber);

	@Query("SELECT e FROM ExamRecord e  JOIN e.medicalExam ex JOIN e.admissionInfo a WHERE a.patientTrackingNumber=:patientTrackingNumber")
	List<ExamRecord> findErecords(@Param("patientTrackingNumber") String patientTrackingNumber);

	/**
	 * Find exam.
	 *
	 * @param id                    the exam id
	 * @param patientTrackingNumber the patient tracking number
	 * @return the exam records
	 */
	@Query("SELECT e from ExamRecord e JOIN e.admissionInfo a  JOIN e.medicalExam  ex WHERE a.patientTrackingNumber=:patientTrackingNumber and ex.examId=:examId")
	ExamRecord findExam(@Param("examId") int id, @Param("patientTrackingNumber") String patientTrackingNumber);

	/**
	 * Find by exam record id.
	 *
	 * @param id the id
	 * @return the exam record
	 */
	ExamRecord findByExamRecordId(Long id);

	/**
	 * Find info.
	 *
	 * @param patientNumber the patient number
	 * @return the list
	 */
	@Query("SELECT e FROM ExamRecord e JOIN e.admissionInfo a JOIN a.admittedPatient p WHERE p.patientNumber=:patientNumber")
	List<ExamRecord> findInfo(@Param("patientNumber") String patientNumber);

	/**
	 * Count by exam name.
	 *
	 * @param hospitalId the hospital id
	 * @return the list
	 */
	@Query("SELECT new rw.ehealth.report.ExamReport(ex,e,count(e.examId)) from ExamRecord ex JOIN ex.medicalExam e JOIN ex.admissionInfo a  join ex.hospital h WHERE h.hospitalId=:hospitalId group BY e.examId")
	List<ExamReport> countByExamName(@Param("hospitalId") Long hospitalId);

	/**
	 * Find results.
	 *
	 * @param hospitalId the hospital id
	 * @param status     the status
	 * @return the list
	 */
	@Query("SELECT  e from ExamRecord e Join e.hospital h join e.admissionInfo a  WHERE h.hospitalId=:hospitalId and e.closedWithResult is true group by a.patientTrackingNumber")
	List<ExamRecord> findResults(@Param("hospitalId") Long hospitalId);

	/**
	 * Find by admission info.
	 *
	 * @param admissionInfo the admission info
	 * @return the exam record
	 */
	List<ExamRecord> findByAdmissionInfo(Admission admissionInfo);

	/**
	 * Find by admission info and exam.
	 *
	 * @param admissionInfo the admission info
	 * @param medicalExam   the medical exam
	 * @return the exam record
	 */
	@Query("SELECT e from ExamRecord e where e.admissionInfo=:admissionInfo and e.medicalExam=:medicalExam")
	ExamRecord findByAdmissionInfoAndExam(@Param("admissionInfo") Admission admissionInfo,
			@Param("medicalExam") MedicalExam medicalExam);

	@Query("SELECT count(distinct a.patientTrackingNumber) FROM ExamRecord e JOIN e.admissionInfo a  JOIN e.hospital h WHERE h.hospitalId=:hospitalId and e.closedWithResult is true")
	Long countresults(@Param("hospitalId") Long hospitalId);

	/**
	 * Find completed exams by admission.
	 *
	 * @param admission the admission
	 * @return the list
	 */
	@Query("SELECT e from ExamRecord e where e.admissionInfo=:admission AND e.results is not null")
	List<ExamRecord> findCompletedExamsByAdmission(@Param("admission") Admission admission);
	@Query("SELECT count(distinct a.patientTrackingNumber) FROM ExamRecord e JOIN e.admissionInfo a  JOIN e.hospital h WHERE h.hospitalId=:hospitalId")
	Long countRecievedPatient(@Param("hospitalId") Long hospitalId);
	@Query("SELECT count(distinct a.patientTrackingNumber) FROM ExamRecord e JOIN e.admissionInfo a  JOIN e.hospital h  JOIN e.examResponseEmployee r WHERE h.hospitalId=:hospitalId and r.employeeId=:employeeId")
	Long countRecievedPatientByDoctor(@Param("hospitalId") Long hospitalId,@Param("employeeId") Long employeeId);

}
