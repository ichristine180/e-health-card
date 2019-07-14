
package rw.ehealth.model;

import java.time.LocalDateTime;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ADMISSION_INFORMATION")
public class AdmissionInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ADMISSION_ID", nullable = false, updatable = false)
	private Long admissionId;

	/**
	 * The constant admissionDate - LocalDateTime
	 */
	@Column(name = "ADMINSSION_DATE", nullable = false)
	private LocalDateTime admissionDate;

	/**
	 * The constant releasedDate - LocalDateTime
	 */
	@Column(name = "RELEASED_DATE")
	private LocalDateTime releasedDate;

	/**
	 * The constant admittedPatient - Patient
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "patientId")
	private Patient admittedPatient;

	/**
	 * The constant doctor - Doctor
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctorId")
	private Doctor doctor;

	/**
	 * The constant patientTrackingNumber - String
	 */
	@Column(name = "PATIENT_TRACKING_NUMBER", unique = true, nullable = false)
	private String patientTrackingNumber;

	/**
	 * The constant height - Double
	 */
	private Double height;
	/**
	 * The constant temperature - Long
	 */
	private double temperature;
	/**
	 * The constant bloodPressure - Double
	 */
	private Double bloodPressure;
	/**
	 * The constant weight - Double
	 */
	private Double weight;
	/**
	 * The constant heartRate - String
	 */
	private String heartRate;

	/**
	 * @return the admissionId
	 */
	public Long getAdmissionId() {
		return admissionId;
	}

	/**
	 * @return the admissionDate
	 */
	public LocalDateTime getAdmissionDate() {
		return admissionDate;
	}

	/**
	 * @return the admittedPatient
	 */
	public Patient getAdmittedPatient() {
		return admittedPatient;
	}

	/**
	 * @return the doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * @return the patientTrackingNumber
	 */
	public String getPatientTrackingNumber() {
		return patientTrackingNumber;
	}

	/**
	 * @param admissionId the admissionId to set
	 */
	public void setAdmissionId(Long admissionId) {
		this.admissionId = admissionId;
	}

	/**
	 * @param admissionDate the admissionDate to set
	 */
	public void setAdmissionDate(LocalDateTime admissionDate) {
		this.admissionDate = admissionDate;
	}

	/**
	 * @param admittedPatient the admittedPatient to set
	 */
	public void setAdmittedPatient(Patient admittedPatient) {
		this.admittedPatient = admittedPatient;
	}

	/**
	 * @param doctor the doctor to set
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * @param patientTrackingNumber the patientTrackingNumber to set
	 */
	public void setPatientTrackingNumber(String patientTrackingNumber) {
		this.patientTrackingNumber = patientTrackingNumber;
	}

	/**
	 * @return the releasedDate
	 */
	public LocalDateTime getReleasedDate() {
		return releasedDate;
	}

	/**
	 * @param releasedDate the releasedDate to set
	 */
	public void setReleasedDate(LocalDateTime releasedDate) {
		this.releasedDate = releasedDate;
	}

	/**
	 * @return the height
	 */
	public Double getHeight() {
		return height;
	}

	/**
	 * @return the temperature
	 */
	public double getTemperature() {
		return temperature;
	}

	/**
	 * @return the bloodPressure
	 */
	public Double getBloodPressure() {
		return bloodPressure;
	}

	/**
	 * @return the weight
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * @return the heartRate
	 */
	public String getHeartRate() {
		return heartRate;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(Double height) {
		this.height = height;
	}

	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	/**
	 * @param bloodPressure the bloodPressure to set
	 */
	public void setBloodPressure(Double bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	/**
	 * @param heartRate the heartRate to set
	 */
	public void setHeartRate(String heartRate) {
		this.heartRate = heartRate;
	}

	/*
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AdminssionInfo [admissionId=" + admissionId + ", admissionDate=" + admissionDate + ", releasedDate="
				+ releasedDate + ", admittedPatient=" + admittedPatient + ", doctor=" + doctor
				+ ", patientTrackingNumber=" + patientTrackingNumber + ", height=" + height + ", temperature="
				+ temperature + ", bloodPressure=" + bloodPressure + ", weight=" + weight + ", heartRate=" + heartRate
				+ "]";
	}

}
