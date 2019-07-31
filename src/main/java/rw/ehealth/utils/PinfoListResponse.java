package rw.ehealth.utils;

import java.util.List;

import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.Consultation;
import rw.ehealth.model.ExamRecords;

public class PinfoListResponse {
	private String message;

	private boolean error;
	private List<ExamRecords> examRecords;
	private Consultation consultation;
	public Consultation getConsultation() {
		return consultation;
	}
	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}
	private List<AdmissionInfo> admissionInfos;
	public List<AdmissionInfo> getAdmissionInfos() {
		return admissionInfos;
	}
	public void setAdmissionInfos(List<AdmissionInfo> admissionInfos) {
		this.admissionInfos = admissionInfos;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public List<ExamRecords> getExamRecords() {
		return examRecords;
	}
	public void setExamRecords(List<ExamRecords> examRecords) {
		this.examRecords = examRecords;
	}

}
