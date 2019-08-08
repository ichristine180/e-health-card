package rw.ehealth.repo.medical;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rw.ehealth.model.ExamRecords;
import rw.ehealth.report.ExamReport;
 
@Repository
public interface ExamRecordsRepository extends JpaRepository<ExamRecords, Long> {

	/**
	 * Find pati exam records.
	 *
	 * @return the list
	 */ 
	@Query("SELECT count(a.patientTrackingNumber) FROM ExamRecords e JOIN e.admissionInfo a  JOIN e.hospital h WHERE h.hospitalId=:hospitalId")
	Long countPatient(@Param("hospitalId") Long hospitalId);
	@Query("SELECT e FROM ExamRecords e JOIN e.admissionInfo a  JOIN e.hospital h WHERE e.results = null  and h.hospitalId = :hospitalId GROUP BY a.admissionId")
	List<ExamRecords> findPatiExamRecords(@Param("hospitalId") Long id);

	/**
	 * Find exam records by patient.
	 *
	 * @param patientTrackingNumber the patient tracking number
	 * @return the list
	 */
	@Query("SELECT e,ex FROM ExamRecords e  JOIN e.exams ex JOIN e.admissionInfo a WHERE a.patientTrackingNumber=:patientTrackingNumber")
	List<ExamRecords> findExamRecordsByPatient(@Param("patientTrackingNumber") String patientTrackingNumber);
	@Query("SELECT e FROM ExamRecords e  JOIN e.exams ex JOIN e.admissionInfo a WHERE a.patientTrackingNumber=:patientTrackingNumber")
	List<ExamRecords> findErecords(@Param("patientTrackingNumber") String patientTrackingNumber);

	/**
	 * Find exam.
	 *
	 * @param id                the exam id
	 * @param patientTrackingNumber the patient tracking number
	 * @return the exam records
	 */
	@Query("SELECT e from ExamRecords e JOIN e.admissionInfo a  JOIN e.exams  ex WHERE a.patientTrackingNumber=:patientTrackingNumber and ex.examId=:examId")
	ExamRecords findExam(@Param("examId") int id, @Param("patientTrackingNumber") String patientTrackingNumber);

	/**
	 * Find by exam record id.
	 *
	 * @param id the id
	 * @return the exam records
	 */
	//@Query("SELECT a from ExamRecords a where a.exams.examId = :id")
	ExamRecords findExamRecordById(Long id);
	@Query("SELECT e FROM ExamRecords e JOIN e.admissionInfo a JOIN a.admittedPatient p WHERE p.patientNumber=:patientNumber")
	List<ExamRecords> findInfo(@Param("patientNumber") String patientNumber);
	@Query("SELECT new rw.ehealth.report.ExamReport(ex,e,count(e.examId)) from ExamRecords ex JOIN ex.exams e JOIN ex.admissionInfo a  join ex.hospital h WHERE h.hospitalId=:hospitalId group BY e.examId")
	List<ExamReport> countByExamName(@Param("hospitalId") Long hospitalId);
	
	
	
	
	
	
	
	
	
	
	
}
