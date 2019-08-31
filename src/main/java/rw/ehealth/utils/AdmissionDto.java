
package rw.ehealth.utils;


import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class AdmissionDto {

	@NotNull(message = "This Field is required")
	private Double height;
	/**
	 * The constant temperature - Long
	 */
	private Double temperature;
	/**
	 * The constant bloodPressure - Double
	 */
	private Double bloodPressure;
	private String patientNumber;
	public String getPatientNumber() {
		return patientNumber;
	}
	public void setPatientNumber(String patientNumber) {
		this.patientNumber = patientNumber;
	}
	/**
	 * The constant weight - Double
	 */
	private Double heartRate;
	private Double weight;
	private String departemtName;
	public String getDepartemtName() {
		return departemtName;
	}
	public void setDepartemtName(String departemtName) {
		this.departemtName = departemtName;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Double getBloodPressure() {
		return bloodPressure;
	}
	public void setBloodPressure(Double bloodPressure) {
		this.bloodPressure = bloodPressure;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getHeartRate() {
		return heartRate;
	}
	public void setHeartRate(Double heartRate) {
		this.heartRate = heartRate;
	}
	

}
