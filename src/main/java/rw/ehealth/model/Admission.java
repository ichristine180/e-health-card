
package rw.ehealth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ADMISSIONS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Admission {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ADMISSION_ID", nullable = false, updatable = false)
	private Long admissionId;

	/**
	 * The constant admissionDate - LocalDateTime
	 */
	@Column(name = "ADMINSSION_DATE", nullable = false)
	private String admissionDate;

	/**
	 * The constant releasedDate - LocalDateTime
	 */
	@Column(name = "RELEASED_DATE")
	private String releasedDate;

	/**
	 * The constant admittedPatient - Patient
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "patientId")
	private Patient admittedPatient;

	/** The admitted by. */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "admittedBy")
	private Employee admittedBy;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hospitalId", nullable = false)
	private Hospital hospital;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "departmentId")
	private Department departement;
	/**
	 * The constant patientTrackingNumber - String
	 */
	@Column(name = "PATIENT_TRACKING_NUMBER", unique = true, nullable = false)
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
	 * The constant heartRate - String
	 */
	private String heartRate;
	/**
	 * The constant bloodPressure - Double
	 */
	@Pattern(regexp = "\\d{3}/\\d{2}", message = "Invalid Blood Pressure Format")
	private String bloodPressure;
	/**
	 * The constant weight - Double
	 */
	private String weight;

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Department getDepartement() {
		return departement;
	}

	public void setDepartement(Department departement) {
		this.departement = departement;
	}

	/**
	 * @return the admissionId
	 */
	public Long getAdmissionId() {
		return admissionId;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	/**
	 * @return the releasedDate
	 */
	public String getReleasedDate() {
		return releasedDate;
	}

	/**
	 * @return the admittedPatient
	 */
	public Patient getAdmittedPatient() {
		return admittedPatient;
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

	/**
	 * @param admissionId the admissionId to set
	 */
	public void setAdmissionId(Long admissionId) {
		this.admissionId = admissionId;
	}

	/**
	 * @param releasedDate the releasedDate to set
	 */
	public void setReleasedDate(String releasedDate) {
		this.releasedDate = releasedDate;
	}

	/**
	 * @param admittedPatient the admittedPatient to set
	 */
	public void setAdmittedPatient(Patient admittedPatient) {
		this.admittedPatient = admittedPatient;
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

	/**
	 * @return the admissionDate
	 */
	public String getAdmissionDate() {
		return admissionDate;
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
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(String weight) {
		this.weight = weight;
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
	 * @return the heartRate
	 */
	public String getHeartRate() {
		return heartRate;
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
	 * @param heartRate the heartRate to set
	 */
	public void setHeartRate(String heartRate) {
		this.heartRate = heartRate;
	}

	/**
	 * @param admissionDate the admissionDate to set
	 */
	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	/**
	 * @return the admittedBy
	 */
	public Employee getAdmittedBy() {
		return admittedBy;
	}

	/**
	 * @param admittedBy the admittedBy to set
	 */
	public void setAdmittedBy(Employee admittedBy) {
		this.admittedBy = admittedBy;
	}

	/*
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Admission [admissionId=" + admissionId + ", admissionDate=" + admissionDate + ", releasedDate="
				+ releasedDate + ", admittedPatient=" + admittedPatient + ", admittedBy=" + admittedBy + ", hospital="
				+ ", temperature=" + temperature + ", bloodPressure=" + bloodPressure + ", weight=" + weight
				+ ", heartRate=" + heartRate + ", departement=" + departement + "]";
	}

}
