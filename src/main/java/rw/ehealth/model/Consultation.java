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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "consultation")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Consultation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id", nullable = false, updatable = false)
	private Long consultationId;
	/**
	 * The constant description - String
	 */
	@Column(name = "DESCRIPTON", length = 1000)
	@NotNull(message = "Invalid consultation data")
	private String description;

	private String dateTaken;

	private String status;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admissionId")
	private Admission admission;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "consultedBy")
	private Employee doctor;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hospitalId", nullable = false)
	private Hospital hospital;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateTaken() {
		return dateTaken;
	}

	public void setDateTaken(String dateTaken) {
		this.dateTaken = dateTaken;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

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
	public Admission getAdmission() {
		return admission;
	}

	/**
	 * @return the doctor
	 */
	public Employee getDoctor() {
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
	public void setAdmission(Admission admissionInfo) {
		this.admission = admissionInfo;
	}

	/**
	 * @param doctor the doctor to set
	 */
	public void setDoctor(Employee doctor) {
		this.doctor = doctor;
	}

	/*
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Consultation [consultationId=" + consultationId + ", description=" + description + ", dateTaken="
				+ dateTaken + ", admission=" + admission + ", doctor=" + doctor + ", hospital=" + hospital + "]";
	}

}
