
package rw.ehealth.utils;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AdmissionDto {

	@NotNull(message = "Heigt is required")
	@Min(value = 30, message = "Too short to be a person")
	@Max(value = 250, message = "To tall to be a person")
	private String height;
	/**
	 * The constant temperature - Long
	 */
	@NotNull(message = "Temperature is required")
	@DecimalMin(value = "30.0", message = "Below the minimum temperature of a living person")
	@DecimalMax(value = "45.0", message = "Above the maximum temperature of a living person")
	@Pattern(regexp = "\\d{2}", message = "Invalid temperature")
	private String temperature;
	/**
	 * The constant bloodPressure - Double
	 */
	@Pattern(regexp = "\\d{3}/\\d{2}", message = "Invalid Blood Pressure Format")
	private String bloodPressure;
	/**
	 * The constant weight - Double
	 */
	@NotNull(message = "heartRate is required")
	@DecimalMin(value = "30.0", message = "May be a dead person! Invalid heart rate")
	@Pattern(regexp = "\\d{2}", message = "Invalid heartRate format")
	@DecimalMax(value = "100.0", message = "Too fast heart rate")
	private String heartRate;

	@NotNull(message = "Weight is required")
	@Pattern(regexp = "\\d{2}", message = "Invalid weight format")
	@Min(value = 0, message = "Invalid Weight")
	private String weight;

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

	
	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
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

	/**
	 * @return the temperature
	 */
	public String getTemperature() {
		return temperature;
	}

	/**
	 * @return the heartRate
	 */
	public String getHeartRate() {
		return heartRate;
	}

	/**
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	/**
	 * @param heartRate the heartRate to set
	 */
	public void setHeartRate(String heartRate) {
		this.heartRate = heartRate;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

}
