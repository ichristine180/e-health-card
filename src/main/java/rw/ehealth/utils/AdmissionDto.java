
package rw.ehealth.utils;

import java.time.LocalDateTime;

public class AdmissionDto {

	private LocalDateTime admissionDate;

	private String releasedDate;

	private String patientNumber;

	private String departementName;

	public String getDepartementName() {
		return departementName;
	}

	public void setDepartementName(String departementName) {
		this.departementName = departementName;
	}

	private String patientTrackingNumber;

	/**
	 * The constant height - Double
	 */
	private String height;
	/**
	 * The constant temperature - Long
	 */
	private String temperature;
	/**
	 * The constant bloodPressure - Double
	 */
	private String bloodPressure;
	/**
	 * The constant weight - Double
	 */
	private String weight;
	/**
	 * The constant heartRate - String
	 */
	private String heartRate;

	/**
	 * @return the admissionDate
	 */
	public LocalDateTime getAdmissionDate() {
		return admissionDate;
	}

	/**
	 * @return the releasedDate
	 */
	public String getReleasedDate() {
		return releasedDate;
	}

	/**
	 * @return the patientTrackingNumber
	 */
	public String getPatientTrackingNumber() {
		return patientTrackingNumber;
	}

	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @return the temperature
	 */
	public String getTemperature() {
		return temperature;
	}

	/**
	 * @return the bloodPressure
	 */
	public String getBloodPressure() {
		return bloodPressure;
	}

	/**
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * @return the heartRate
	 */
	public String getHeartRate() {
		return heartRate;
	}

	/**
	 * @param admissionDate the admissionDate to set
	 */
	public void setAdmissionDate(LocalDateTime admissionDate) {
		this.admissionDate = admissionDate;
	}

	/**
	 * @param releasedDate the releasedDate to set
	 */
	public void setReleasedDate(String releasedDate) {
		this.releasedDate = releasedDate;
	}

	/**
	 * @param patientTrackingNumber the patientTrackingNumber to set
	 */
	public void setPatientTrackingNumber(String patientTrackingNumber) {
		this.patientTrackingNumber = patientTrackingNumber;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	/**
	 * @param bloodPressure the bloodPressure to set
	 */
	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * @param heartRate the heartRate to set
	 */
	public void setHeartRate(String heartRate) {
		this.heartRate = heartRate;
	}

	/**
	 * @return the patientNumber
	 */
	public String getPatientNumber() {
		return patientNumber;
	}

	/**
	 * @param patientNumber the patientNumber to set
	 */
	public void setPatientNumber(String patientNumber) {
		this.patientNumber = patientNumber;
	}

}
