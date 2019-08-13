
package rw.ehealth.service.exams;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ehealth.model.MedicalExam;
import rw.ehealth.repo.medical.MedicalExamRepository;

@Service(IExamService.name)
public class ExamService implements IExamService {

	@Autowired
	private MedicalExamRepository examsRepo;

	public MedicalExam findHospitalById(long id) {
		try {
			return examsRepo.findByExamId(id);
		} catch (Exception e) {
			throw e;
		}

	}

	/*
	 *
	 * @see rw.ehealth.service.medical.IExamService#createExams(rw.ehealth.model.MedicalExam)
	 */
	@Override
	public MedicalExam createExams(MedicalExam exams) {
		try {
			return examsRepo.save(exams);
		} catch (Exception ex) {
			throw ex;
		}
	}

	/*
	 *
	 * @see rw.ehealth.service.medical.IExamService#findExams()
	 */
	@Override
	public List<MedicalExam> findExams() {
		try {
			return examsRepo.findAll();
		} catch (Exception e) {
			throw e;
		}
	}

}
