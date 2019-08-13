package rw.ehealth.service.exams;

import java.util.List;

import rw.ehealth.model.MedicalExam;

public interface IExamService {

	String name = "examService";

	MedicalExam createExams(MedicalExam exams);

	List<MedicalExam> findExams();
}