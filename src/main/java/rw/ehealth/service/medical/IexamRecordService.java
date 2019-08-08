package rw.ehealth.service.medical;

import java.util.List;


import rw.ehealth.model.ExamRecords;
import rw.ehealth.report.ExamReport;

public interface IexamRecordService {
	String nameString = "examRecordService";

	ExamRecords creaExamRecords(ExamRecords examRecords);

	List<ExamRecords> findAllPExam(Long id);

	List<ExamRecords> findExamRecordsByPatient(String patientTrackingNumber);

	ExamRecords findOneExam(String pnumber, int examId);

	ExamRecords update(ExamRecords examRecords);
	Long countPatient(Long hospitalId);

	/**
	 * @param i
	 * @return
	 */
	ExamRecords findExamRecordByExamId(int i); 
	
	List<ExamRecords> findInfoByPatient(String patientNumber);
	List<ExamRecords> findExamrecords(String patientTrackingNumber);
	List<ExamReport> countByExamName(Long hospitalId);

}
