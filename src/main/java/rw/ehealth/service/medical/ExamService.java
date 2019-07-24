package rw.ehealth.service.medical;

import java.util.List;

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

	@Override
	public List<Exams> findExams() {
		try {
			return examsRepo.findAll();
		} catch (Exception e) {
			throw e;
		}
		
	}

	public Exams findHospitalById(long id) {
		try {
			return examsRepo.findByExamId(id);
		} catch (Exception e) {
			throw e;
		}
		
	}
	

}