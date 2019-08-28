
package rw.ehealth.utils;

import java.util.List;

import rw.ehealth.model.Admission;
import rw.ehealth.model.Consultation;
import rw.ehealth.model.Employee;
import rw.ehealth.model.ExamRecord;
import rw.ehealth.model.Prescription;
import rw.ehealth.model.ViewRecordRequest;

public class PinfoListResponse {
	private String message;

	private boolean error;
	private List<ExamRecord> examRecords;
	private Consultation consultation;
	private ViewRecordRequest request;
	private Employee docemployee;

	public Employee getDocemployee() {
		return docemployee;
	}

	public void setDocemployee(Employee docemployee) {
		this.docemployee = docemployee;
	}

	private List<Admission> admissionInfos;

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

	/**
	 * @return the request
	 */
	public ViewRecordRequest getRequest() {
		return request;
	}

	/**
	 * @return the admissionInfos
	 */
	public List<Admission> getAdmissionInfos() {
		return admissionInfos;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest(ViewRecordRequest request) {
		this.request = request;
	}

	/**
	 * @param admissionInfos the admissionInfos to set
	 */
	public void setAdmissionInfos(List<Admission> admissionInfos) {
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

	public List<ExamRecord> getExamRecords() {
		return examRecords;
	}

	public void setExamRecords(List<ExamRecord> examRecords) {
		this.examRecords = examRecords;
	}

	
}
