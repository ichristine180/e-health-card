package rw.ehealth.service.medical;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import rw.ehealth.model.Exams;
import rw.ehealth.repo.medical.ExamsRepository;


@Service(IexameService.name)
public class ExamService implements IexameService {
	@Autowired
	private ExamsRepository examsRepo;

	@Override
	public Exams createExams(Exams exams) {
		try {
			return examsRepo.save(exams);
		} catch (Exception ex) {
			throw ex;
		}
	}

}