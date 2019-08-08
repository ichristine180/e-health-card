package rw.ehealth.report;

import rw.ehealth.model.Exams;
import rw.ehealth.model.ExamRecords;

public class ExamReport {
	private ExamRecords ExamRecords;
	private Exams exam;

	

	public ExamReport(rw.ehealth.model.ExamRecords examRecords, Exams exam, long examcount) {
		ExamRecords = examRecords;
		this.exam = exam;
		this.examcount = examcount;
	}

	public ExamRecords getExamRecords() {
		return ExamRecords;
	}

	public void setExamRecords(ExamRecords examRecords) {
		ExamRecords = examRecords;
	}

	public Exams getExam() {
		return exam;
	}

	public void setExam(Exams exam) {
		this.exam = exam;
	}

	public long getExamcount() {
		return examcount;
	}

	public void setExamcount(long examcount) {
		this.examcount = examcount;
	}

	private long examcount;

}
