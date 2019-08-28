package rw.ehealth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import rw.ehealth.enums.ERecordType;

@Entity
@Table(name = "recordHistoryLog")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class RecordHistoryLog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "recordHistoryLogId", nullable = false, updatable = false)
	private Long recordHistoryLogId;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hospitalId", nullable = false)
	private Hospital hospital;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "patientId")
	private Patient patient;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "viewedBy")
	private Employee viewer;

	@Column(name = "VIEWD_ON")
	private String viewOn;

	@Column(name = "viewedRecordId", nullable = false)
	private long viewedRecordId;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	/**
	 * @return the recordHistoryLogId
	 */
	public Long getRecordHistoryLogId() {
		return recordHistoryLogId;
	}

	/**
	 * @return the viewer
	 */
	public Employee getViewer() {
		return viewer;
	}
	/**
	 * @return the viewedRecordId
	 */
	public long getViewedRecordId() {
		return viewedRecordId;
	}

	/**
	 * @param recordHistoryLogId the recordHistoryLogId to set
	 */
	public void setRecordHistoryLogId(Long recordHistoryLogId) {
		this.recordHistoryLogId = recordHistoryLogId;
	}

	/**
	 * @param viewer the viewer to set
	 */
	public void setViewer(Employee viewer) {
		this.viewer = viewer;
	}


	public String getViewOn() {
		return viewOn;
	}

	public void setViewOn(String viewOn) {
		this.viewOn = viewOn;
	}

	

	/**
	 * @param viewedRecordId the viewedRecordId to set
	 */
	public void setViewedRecordId(long viewedRecordId) {
		this.viewedRecordId = viewedRecordId;
	}

	/*
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RecordHistoryLog [recordHistoryLogId=" + recordHistoryLogId + ", hospital=" + hospital + ", patient="
				+ patient + ", viewer=" + viewer + ", viewOn=" + viewOn + ",viewedRecordId=" + viewedRecordId + "]";
	}

}
