package rw.ehealth.report;

import rw.ehealth.model.MedicalExam;
import rw.ehealth.model.ExamRecord;

public class ExamReport {
	private ExamRecord ExamRecords;
	private MedicalExam exam;

	

	public ExamReport(rw.ehealth.model.ExamRecord examRecords, MedicalExam exam, long examcount) {
		ExamRecords = examRecords;
		this.exam = exam;
		this.examcount = examcount;
	}

	public ExamRecord getExamRecords() {
		return ExamRecords;
	}

	public void setExamRecords(ExamRecord examRecords) {
		ExamRecords = examRecords;
	}

	public MedicalExam getExam() {
		return exam;
	}

	public void setExam(MedicalExam exam) {
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
