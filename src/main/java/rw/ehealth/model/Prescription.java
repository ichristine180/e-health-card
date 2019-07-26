package rw.ehealth.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prescriptions")
public class Prescription {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id", nullable = false, updatable = false)
	private Long id;
	/**
	 * The constant name - String
	 */
	private String name;
	/**
	 * The constant description - String
	 */
	private String description;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admissionId")
	private AdmissionInfo admissionInfo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patientId")
	private Doctor doctor;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the admissionInfo
	 */
	public AdmissionInfo getAdmissionInfo() {
		return admissionInfo;
	}

	/**
	 * @return the doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * @param admissionInfo the admissionInfo to set
	 */
	public void setAdmissionInfo(AdmissionInfo admissionInfo) {
		this.admissionInfo = admissionInfo;
	}

	/**
	 * @param doctor the doctor to set
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/*
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Prescription [id=" + id + ", name=" + name + ", description=" + description
				+ ", admissionInfo=" + admissionInfo + ", doctor=" + doctor + "]";
	}

}
