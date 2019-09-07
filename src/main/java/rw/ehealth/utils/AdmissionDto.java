
package rw.ehealth.utils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AdmissionDto {

	@NotNull(message = "Heigt is required")
	@Min(value = 30, message = "Too short to be a person")
	@Max(value = 250, message = "To high to be a person")
	private Double height;
	/**
	 * The constant temperature - Long
	 */
	@NotNull(message = "Temperature is required")
	@Min(value = 30, message = "Below the minimum temperature of a living person")
	@Max(value = 45, message = "Above the maximum temperature of a living person")
	private Double temperature;
	/**
	 * The constant bloodPressure - Double
	 */
	@Pattern(regexp = "\\d{2}-\\d{3}", message = "Invalid Blood Pressure Format")
	private String bloodPressure;
	/**
	 * The constant weight - Double
	 */
	@NotNull(message = "heartRate is required")
	@Min(value = 0, message = "May be a dead person! Invalid heart rate")
	@Max(value = 100, message = "Too fast heart rate")
	private Double heartRate;

	@NotNull(message = "Weight is required")
	@Min(value = 0L, message = "Invalid weight")
	private Double weight;

	@NotNull(message = "Please Select Departement")
	private String departemtName;

	private String patientNumber;

	public String getPatientNumber() {
		return patientNumber;
	}

	public void setPatientNumber(String patientNumber) {
		this.patientNumber = patientNumber;
	}

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

	/**
	 * @return the bloodPressure
	 */
	public String getBloodPressure() {
		return bloodPressure;
	}

	/**
	 * @param bloodPressure the bloodPressure to set
	 */
	public void setBloodPressure(String bloodPressure) {
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
