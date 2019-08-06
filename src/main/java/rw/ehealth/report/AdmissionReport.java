
package rw.ehealth.report;

import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.Patient;

public class AdmissionReport {

	private AdmissionInfo admission;

	private Patient patient;

	private long admissionCount;

	/**
	 * @param admission
	 * @param patient
	 * @param admissionCount
	 */
	public AdmissionReport(AdmissionInfo admission, Patient patient, long admissionCount) {
		this.admission = admission;
		this.patient = patient;
		this.admissionCount = admissionCount;
	}

	/**
	 * @return the admission
	 */
	public AdmissionInfo getAdmission() {
		return admission;
	}

	/**
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * @return the admissionCount
	 */
	public long getAdmissionCount() {
		return admissionCount;
	}

	/**
	 * @param admission the admission to set
	 */
	public void setAdmission(AdmissionInfo admission) {
		this.admission = admission;
	}

	/**
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * @param admissionCount the admissionCount to set
	 */
	public void setAdmissionCount(long admissionCount) {
		this.admissionCount = admissionCount;
	}

}
