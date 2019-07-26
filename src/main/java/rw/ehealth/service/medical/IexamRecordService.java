package rw.ehealth.service.medical;

import java.util.List;

import rw.ehealth.model.ExamRecords;

public interface IexamRecordService {
	String nameString = "examRecordService";

	ExamRecords creaExamRecords(ExamRecords examRecords);

	List<ExamRecords> findAllPExam();

	List<ExamRecords> findExamRecordsByPatient(String patientTrackingNumber);

	ExamRecords findOneExam(String pnumber, int examId);

	ExamRecords update(ExamRecords examRecords);

	/**
	 * @param i
	 * @return
	 */
	ExamRecords findExamRecordByExamId(int i);
}
