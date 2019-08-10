package rw.ehealth.service.medical;

import java.util.List;

import rw.ehealth.model.ExamRecord;
import rw.ehealth.report.ExamReport;

public interface IExamRecordService {

	String NAME = "examRecordService";

	ExamRecord creaExamRecords(ExamRecord examRecords);

	List<ExamRecord> findAllPExam(Long id);

	List<ExamRecord> findExamRecordsByPatient(String patientTrackingNumber);

	ExamRecord findOneExam(String pnumber, int examId);

	ExamRecord update(ExamRecord examRecords);

	Long countPatient(Long hospitalId);

	ExamRecord findExamRecordByExamId(int i);

	List<ExamRecord> findInfoByPatient(String patientNumber);

	List<ExamRecord> findExamrecords(String patientTrackingNumber);

	List<ExamReport> countByExamName(Long hospitalId);

	List<ExamRecord> findErecords(String patientTrackingNumber);

}
