package rw.ehealth.service.medical;

import java.util.List;

import rw.ehealth.model.Exams;

public interface IexameService {
	String name = "examservice";

	Exams createExams(Exams exams);
	List<Exams> findExams();
}