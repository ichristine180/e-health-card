package rw.ehealth.service.medical;

import rw.ehealth.model.Exams;

public interface IexameService {
	String name = "examservice";

	Exams createExams(Exams exams);
}