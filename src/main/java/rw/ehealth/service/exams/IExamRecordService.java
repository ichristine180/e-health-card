package rw.ehealth.service.exams;

import java.util.List;

import rw.ehealth.model.Admission;
import rw.ehealth.model.ExamRecord;
import rw.ehealth.model.Hospital;
import rw.ehealth.model.Patient;
import rw.ehealth.report.ExamReport;

public interface IExamRecordService {

	String NAME = "examRecordService";

	ExamRecord creaExamRecords(ExamRecord examRecords);

	List<ExamRecord> findAllPExam(Long id);

	List<ExamRecord> findExamRecordsByPatient(String patientTrackingNumber);

	ExamRecord findOneExam(String pnumber, int examId);

	ExamRecord update(ExamRecord examRecords);

	Long countPatient(Long hospitalId);

	Long countresults(Long hospitalId);

	ExamRecord findExamRecordByExamId(int i);

	List<ExamRecord> findInfoByPatient(String patientNumber);

	List<ExamRecord> findExamrecords(String patientTrackingNumber);

	List<ExamReport> countByExamName(Long hospitalId);

	List<ExamRecord> findErecords(String patientTrackingNumber);

	List<ExamRecord> findResults(Long hospitalId);

	boolean isNotCreated(ExamRecord examRecords);

	List<ExamRecord> findAll();

	/**
	 * @param admission
	 * @return
	 */
	List<ExamRecord> findExamRecordByAddmission(Admission admission);

	/**
	 * @param hospital
	 * @return
	 */
	List<ExamRecord> findActiveExamRecords(Hospital hospital);

	/**
	 * Find exam records by patient.
	 *
	 * @param patient the patient
	 * @return the list
	 */
	List<ExamRecord> findExamRecordsByPatient(Patient patient);
	Long countRecievedPatient(Long hospitalId);
	Long countRecievedPatientByDoctor(Long hospitalId,Long employeeId);
}
