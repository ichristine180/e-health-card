package rw.ehealth.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patients")
public class Patient {
	/**
	 * The constant patientId - Long
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "patientId", nullable = false, updatable = false)
	private Long patientId;
	/**
	 * The constant fname - String
	 */
	private String fname;
	/**
	 * The constant lname - String
	 */
	private String lname;
	/**
	 * The constant address - String
	 */
	private String address;

	@Column(name = "identificationNumber", nullable = false, unique = true)
	private String identificationNumber;

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
				+ ", identificationNumber=" + identificationNumber + "]";
	}

}
