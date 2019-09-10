
package rw.ehealth.service.exams;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ehealth.model.Admission;
import rw.ehealth.model.ExamRecord;
import rw.ehealth.model.Hospital;
import rw.ehealth.model.Patient;
import rw.ehealth.repo.medical.ExamRecordRepository;
import rw.ehealth.report.ExamReport;

@Service(IExamRecordService.NAME)
public class ExamRecordService implements IExamRecordService {

	@Autowired
	private ExamRecordRepository eRepository;

	@Override
	public ExamRecord creaExamRecords(ExamRecord examRecords) {
		if (this.isNotCreated(examRecords)) {
			try {
				return eRepository.save(examRecords);
			} catch (Exception e) {
				throw e;
			}
		}
		return examRecords;
	}

	@Override
	public boolean isNotCreated(ExamRecord examRecords) {
		boolean isNotCreated = false;
		ExamRecord record = eRepository.findByAdmissionInfoAndExam(examRecords.getAdmissionInfo(),
				examRecords.getMedicalExam());
		if (record == null) {
			isNotCreated = true;
		}
		return isNotCreated;
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
			return eRepository.findByExamRecordId(Long.valueOf(i));
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

	@Override
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
	public List<ExamRecord> findResults(Long hospitalId) {
		try {
			return eRepository.findResults(hospitalId);
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 *
	 * @see rw.ehealth.service.exams.IExamRecordService#findExamRecordByAddmission(rw.ehealth.model.Admission)
	 */
	@Override
	public List<ExamRecord> findExamRecordByAddmission(Admission admission) {
		return eRepository.findByAdmissionInfo(admission);
	}

	/*
	 *
	 * @see rw.ehealth.service.exams.IExamRecordService#findActiveExamRecords(rw.ehealth.model.Hospital)
	 */
	@Override
	public List<ExamRecord> findActiveExamRecords(Hospital hospital) {

		return eRepository.findActiveExamRecordByHospital(hospital);
	}

	@Override
	public Long countresults(Long hospitalId) {
		try {
			return eRepository.countresults(hospitalId);
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 *
	 * @see rw.ehealth.service.exams.IExamRecordService#findExamRecordsByPatient(rw.ehealth.model.Patient)
	 */
	@Override
	public List<ExamRecord> findExamRecordsByPatient(Patient patient) {
		try {
			return eRepository.findInfo(patient.getPatientNumber());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Long countRecievedPatient(Long hospitalId) {
		try {
			return eRepository.countRecievedPatient(hospitalId);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Long countRecievedPatientByDoctor(Long hospitalId, Long employeeId) {
		try {
			return eRepository.countRecievedPatientByDoctor(hospitalId,employeeId);
		} catch (Exception e) {
			throw e;
		}
	}
}
