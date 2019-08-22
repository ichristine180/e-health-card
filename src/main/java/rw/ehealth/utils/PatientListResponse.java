
package rw.ehealth.utils;

import java.util.List;

import rw.ehealth.model.Admission;
import rw.ehealth.model.Patient;
import rw.ehealth.model.RecordHistoryLog;


public class PatientListResponse {

	private boolean error;

	private String message;

	private Patient patients;
	private Admission admission;
public Admission getAdmission() {
		return admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

private List<RecordHistoryLog> patientRecordsViewHistory;
	
	public List<RecordHistoryLog> getPatientRecordsViewHistory() {
	return patientRecordsViewHistory;
}

public void setPatientRecordsViewHistory(List<RecordHistoryLog> patientRecordsViewHistory) {
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
