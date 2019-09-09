
package rw.ehealth.utils.report;

import java.util.List;

import rw.ehealth.model.Admission;
import rw.ehealth.model.Consultation;
import rw.ehealth.model.ExamRecord;
import rw.ehealth.model.Prescription;

public class MedicalRecordUtil {

	private Admission admission;

	private Consultation consultation;

	private List<ExamRecord> examRecords;

	private Prescription prescription;

	/**
	 * @return the admission
	 */
	public Admission getAdmission() {
		return admission;
	}

	/**
	 * @return the consultation
	 */
	public Consultation getConsultation() {
		return consultation;
	}

	/**
	 * @return the examRecords
	 */
	public List<ExamRecord> getExamRecords() {
		return examRecords;
	}

	/**
	 * @return the prescription
	 */
	public Prescription getPrescription() {
		return prescription;
	}

	/**
	 * @param admission the admission to set
	 */
	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	/**
	 * @param consultation the consultation to set
	 */
	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}

	/**
	 * @param examRecords the examRecords to set
	 */
	public void setExamRecords(List<ExamRecord> examRecords) {
		this.examRecords = examRecords;
	}

	/**
	 * @param prescription the prescription to set
	 */
	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	/*
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MedicalRecordUtil [admission=" + admission.toString() + ", consultation=" + consultation.toString()
				+ ", examRecordsSize=" + examRecords.size() + ", prescription=" + prescription.toString() + "]";
	}

}
