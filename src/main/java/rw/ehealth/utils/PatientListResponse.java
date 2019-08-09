
package rw.ehealth.utils;

import java.util.List;

import rw.ehealth.model.Patient;
import rw.ehealth.model.PatientRecordsViewHistory;


public class PatientListResponse {

	private boolean error;

	private String message;

	private Patient patients;
private List<PatientRecordsViewHistory> patientRecordsViewHistory;
	
	public List<PatientRecordsViewHistory> getPatientRecordsViewHistory() {
	return patientRecordsViewHistory;
}

public void setPatientRecordsViewHistory(List<PatientRecordsViewHistory> patientRecordsViewHistory) {
	this.patientRecordsViewHistory = patientRecordsViewHistory;
}

	public boolean isError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(boolean error) {
		this.error = error;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the students
	 */
	
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public Patient getPatients() {
		return patients;
	}

	public void setPatients(Patient patients) {
		this.patients = patients;
	}

	

}
