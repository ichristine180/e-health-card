package rw.ehealth.utils;

import java.util.List;

import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.Consultation;
import rw.ehealth.model.ExamRecords;
import rw.ehealth.model.Prescription;
import rw.ehealth.model.Request;

public class PinfoListResponse {
	private String message;

	private boolean error;
	private List<ExamRecords> examRecords;
	private Consultation consultation;
	private Request request;
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	private Prescription prescription;
	public Prescription getPrescription() {
		return prescription;
	}
	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}
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
