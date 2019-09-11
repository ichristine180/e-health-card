package rw.ehealth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PATIENTS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Patient {
	/**
	 * The constant patientId - Long
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PATIENT_ID", nullable = false, updatable = false)
	private Long patientId;
	/**
	 * The constant fname - String
	 */
	@NotNull
	@NotBlank(message = "The first name is required")
	@Pattern(regexp = "[a-z-A-Z\\s]*", message = "First name has invalid characters! use letter only!")
	@Column(name = "FIRST_NAME")
	private String fname;

	@NotBlank(message = "Birth Day  is required")
	@Column(name = "DATE_OF_BIRTH")
	private String dateOfBirth;
	/**
	 * The constant lname - String
	 */
	@NotNull
	@NotBlank(message = "The last name is required")
	@Pattern(regexp = "[a-z-A-Z\\s]*", message = "Last name has invalid characters! use letter only!")
	@Column(name = "LAST_NAME")
	private String lname;

	@NotBlank(message = "Please select gender")
	@Column(name = "GENDER")
	private String gender;
	/**
	 * The constant address - String
	 */
	@NotBlank(message = "Address  is required")
	@Pattern(regexp = "[a-z-A-Z]*", message = "address has invalid characters! use letter only!")
	@Column(name = "ADDRESS")
	private String address;

	
	
	@NotNull(message = " This field cant be null")
	@Pattern(regexp = "[0-9]+", message = "Invalid characters. Use digits only")
	@Size(min = 16, max = 16, message = "Valid National Identification is 16 Digits")
	@Column(name = "IDENTIFICATION_NUMBER", nullable = false, unique = true)
	private String identificationNumber;

	@Column(name = "PATIENT_NUMBER", nullable = false, unique = true)
	private String patientNumber;

	@Column(name = "registeredDateString")
	private String registeredDate;

	@Column(name = "hospital")
	private String hospital;

	public String getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(String registeredDate) {
		this.registeredDate = registeredDate;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	private boolean admissionStatus;

	/**
	 * @return the admissionStatus
	 */
	public boolean isAdmissionStatus() {
		return admissionStatus;
	}

	/**
	 * @param admissionStatus the admissionStatus to set
	 */
	public void setAdmissionStatus(boolean admissionStatus) {
		this.admissionStatus = admissionStatus;
	}

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
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

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String iDnumber) {
		this.identificationNumber = iDnumber;
	}

	/*
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", fname=" + fname + ", lname=" + lname + ", address=" + address
				+ ", identificationNumber=" + identificationNumber + ", patientNumber=" + patientNumber + "]";
	}

}
