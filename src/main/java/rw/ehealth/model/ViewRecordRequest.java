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

import rw.ehealth.enums.EViewRequestStatus;

@Entity
@Table(name = "viewRequest")
public class ViewRecordRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "requestId", nullable = false, updatable = false)
	private Long requestId;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "admissionId")
	private Admission admission;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "requestedBy")
	private Employee requestedBy;

	private String requestDate;
	private String accessCode;

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "requestStatus", nullable = false)
	private EViewRequestStatus requestStatus;

	private boolean isActive;

	/**
	 * @return the requestedBy
	 */
	public Employee getRequestedBy() {
		return requestedBy;
	}

	/**
	 * @return the requestStatus
	 */
	public EViewRequestStatus getRequestStatus() {
		return requestStatus;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param requestedBy the requestedBy to set
	 */
	public void setRequestedBy(Employee requestedBy) {
		this.requestedBy = requestedBy;
	}

	/**
	 * @param requestStatus the requestStatus to set
	 */
	public void setRequestStatus(EViewRequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}



	public Admission getAdmission() {
		return admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	/*
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ViewRecordRequest [requestId=" + requestId + ", admission=" + admission + ", requestedBy=" + requestedBy
				+ ", requestDate=" + requestDate + ", requestStatus=" + requestStatus + ", isActive=" + isActive + "]";
	}

}
