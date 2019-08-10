
package rw.ehealth.report;

import rw.ehealth.model.Admission;
import rw.ehealth.model.Patient;

public class AdmissionReport {

	private Admission admission;

	private Patient patient;

	private long admissionCount;

	/**
	 * @param admission
	 * @param patient
	 * @param admissionCount
	 */

	public AdmissionReport(Admission admission, Patient patient, long admissionCount) {
		this.admission = admission;
		this.patient = patient;
		this.admissionCount = admissionCount;
	}

	/**
	 * @return the admission
	 */

	public Admission getAdmission() {
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

	public void setAdmission(Admission admission) {
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
