package rw.ehealth.repo.medical;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rw.ehealth.model.ExamRecords;

@Repository
public interface ExamRecordsRepository extends JpaRepository<ExamRecords, Long> {
	@Query("SELECT e FROM ExamRecords e JOIN e.admissionInfo a WHERE e.results = null GROUP BY a.admissionId")
	List<ExamRecords> findPatiExamRecords();
	@Query("SELECT e FROM ExamRecords e JOIN e.admissionInfo a WHERE a.patientTrackingNumber=:patientTrackingNumber")
	List<ExamRecords>findExamRecordsByPatient(@Param("patientTrackingNumber") String patientTrackingNumber);
	@Query("SELECT e from ExamRecords e JOIN e.admissionInfo a  JOIN e.exams  ex WHERE a.patientTrackingNumber=:patientTrackingNumber and ex.examId=:examId")
	ExamRecords findExam(@Param("examId") Long examId,@Param("patientTrackingNumber") String patientTrackingNumber);
}
