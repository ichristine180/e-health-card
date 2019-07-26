package rw.ehealth.service.medical;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ehealth.model.ExamRecords;
import rw.ehealth.repo.medical.ExamRecordsRepository;

@Service(IexamRecordService.nameString)
public class ExamRecordService implements IexamRecordService {
	@Autowired
	private ExamRecordsRepository eRepository;

	@Override
	public ExamRecords creaExamRecords(ExamRecords examRecords) {
		try {
			return eRepository.save(examRecords);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<ExamRecords> findAllPExam() {
		try {
			return eRepository.findPatiExamRecords();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<ExamRecords> findExamRecordsByPatient(String patientTrackingNumber) {
		try {
			return eRepository.findExamRecordsByPatient(patientTrackingNumber);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public ExamRecords findOneExam(String pnumber, int id) {
		try {
			return eRepository.findExam(id, pnumber);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public ExamRecords update(ExamRecords examRecords) {
		try {
			return eRepository.save(examRecords);
		} catch (Exception e) {
			throw e;
		}

	}

	/*
	 *
	 * @see rw.ehealth.service.medical.IexamRecordService#findExamRecordById(int)
	 */
	@Override
	public ExamRecords findExamRecordByExamId(int i) {
		try {
			return eRepository.findExamRecordById(Long.valueOf(i));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
