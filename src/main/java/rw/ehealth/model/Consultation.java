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
@Table(name = "consultation")
public class Consultation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id", nullable = false, updatable = false)
	private Long consultationId;
	/**
	 * The constant description - String
	 */
	@Column(name = "DESCRIPTON", length = 1000)
	private String description;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admissionId")
	private AdmissionInfo admissionInfo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "docId")
	private Doctor doctor;

	/**
	 * @return the consultationId
	 */
	public Long getConsultationId() {
		return consultationId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
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
	 * @param consultationId the consultationId to set
	 */
	public void setConsultationId(Long consultationId) {
		this.consultationId = consultationId;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
		return "Consultation [consultationId=" + consultationId + ", description=" + description + ", admissionInfo="
				+ admissionInfo + ", doctor=" + doctor + "]";
	}

}
