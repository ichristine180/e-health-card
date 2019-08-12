
package rw.ehealth.service.medical;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ehealth.model.ExamRecord;
import rw.ehealth.repo.medical.ExamRecordRepository;
import rw.ehealth.report.ExamReport;

@Service(IExamRecordService.NAME)
public class ExamRecordService implements IExamRecordService {

	@Autowired
	private ExamRecordRepository eRepository;

	@Override
	public ExamRecord creaExamRecords(ExamRecord examRecords) {
		try {
			return eRepository.save(examRecords);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<ExamRecord> findAllPExam(Long id) {
		try {
			return eRepository.findPatiExamRecord(id);

		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<ExamRecord> findExamRecordsByPatient(String patientTrackingNumber) {
		try {
			return eRepository.findExamRecordByPatient(patientTrackingNumber);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<ExamRecord> findErecords(String patientTrackingNumber) {
		try {
			return eRepository.findErecords(patientTrackingNumber);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public ExamRecord findOneExam(String pnumber, int id) {
		try {
			return eRepository.findExam(id, pnumber);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public ExamRecord update(ExamRecord examRecords) {
		try {
			return eRepository.save(examRecords);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public ExamRecord findExamRecordByExamId(int i) {
		try {
			return eRepository.findExamRecordById(Long.valueOf(i));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<ExamRecord> findInfoByPatient(String patientNumber) {
		try {
			return eRepository.findInfo(patientNumber);
		} catch (Exception e) {
			throw e;
		}

	}

	public List<ExamRecord> findAll() {
		try {
			return eRepository.findAll();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<ExamRecord> findExamrecords(String patientTrackingNumber) {
		try {
			return eRepository.findErecords(patientTrackingNumber);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<ExamReport> countByExamName(Long hospitalId) {
		try {
			return eRepository.countByExamName(hospitalId);
		} catch (Exception e) {
			throw e;

		}
	}

	@Override
	public Long countPatient(Long hospitalId) {
		try {
			return eRepository.countPatient(hospitalId);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<ExamRecord> findResults(Long hospitalId, String status) {
		try {
			return eRepository.findResults(hospitalId,status);
		} catch (Exception e) {
			throw e;
		}
	}
}
