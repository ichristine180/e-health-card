
package rw.ehealth.utils;


import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class AdmissionDto {

	@NotNull(message = "Heigt is required")
	@DecimalMax("1.9") @DecimalMin("1.45") 
	private Double height;
	/**
	 * The constant temperature - Long
	 */
	@NotNull(message = "temperature is required")
	@DecimalMax("45") @DecimalMin("30") 
	private Double temperature;
	/**
	 * The constant bloodPressure - Double
	 */
	@NotNull(message = "bloodPressure is required")
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
	@NotNull(message = "heartRate is required")
	private Double heartRate;
	@NotNull(message = "weight is required")
	@DecimalMax("160.0") @DecimalMin("35.0") 
	private Double weight;
	@NotNull(message = "Please Select Departement")
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
