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
@Table(name = "exams")
public class Exams {
	/**
	 * The constant examId - Long
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id", nullable = false, updatable = false)
	private Long examId;
	/**
	 * The constant name - String
	 */
	private String name;
	/**
	 * The constant description - String
	 */
	private String description;
	/**
	 * The constant results - String
	 */
	@Column(name = "RESULTS")
	private String results;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "admissionId")
	private AdmissionInfo admissionInfo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "patientId")
	private Doctor doctor;

	/**
	 * @return the examId
	 */
	public Long getExamId() {
		return examId;
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
	 * @return the results
	 */
	public String getResults() {
		return results;
	}

	/**
	 * @param examId the examId to set
	 */
	public void setExamId(Long examId) {
		this.examId = examId;
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
	 * @param results the results to set
	 */
	public void setResults(String results) {
		this.results = results;
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
		return "Exams [examId=" + examId + ", name=" + name + ", description=" + description + ", results=" + results
				+ ", admissionInfo=" + admissionInfo + ", doctor=" + doctor + "]";
	}

}
