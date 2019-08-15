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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "examRecords")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ExamRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "examrecordid", nullable = false, updatable = false)
	private Long examRecordId;

	/** The medical exam. */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "medicalExamId")
	private MedicalExam medicalExam;

	/** The employee. */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "requestedBy")
	private Employee examRequesterDoctor;

	/** The employee. */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "takenBy")
	private Employee examResponseEmployee;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hospitalId", nullable = false)
	private Hospital hospital;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "admissionid")
	private Admission admissionInfo;

	private String datetaken;
	private String results;

	private boolean closedWithResult;

	/**
	 * @return the closedWithResult
	 */
	public boolean isClosedWithResult() {
		return closedWithResult;
	}

	/**
	 * @param closedWithResult the closedWithResult to set
	 */
	public void setClosedWithResult(boolean closedWithResult) {
		this.closedWithResult = closedWithResult;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public MedicalExam getMedicalExam() {
		return medicalExam;
	}

	public void setMedicalExam(MedicalExam exams) {
		this.medicalExam = exams;
	}

	public Long getExamRecordId() {
		return examRecordId;
	}

	public void setExamRecordId(Long id) {
		this.examRecordId = id;
	}

	public Admission getAdmissionInfo() {
		return admissionInfo;
	}

	public void setAdmissionInfo(Admission admissionInfo) {
		this.admissionInfo = admissionInfo;
	}

	public String getDatetaken() {
		return datetaken;
	}

	public void setDatetaken(String datetaken) {
		this.datetaken = datetaken;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	/**
	 * @return the examRequesterDoctor
	 */
	public Employee getExamRequesterDoctor() {
		return examRequesterDoctor;
	}

	/**
	 * @return the examResponseEmployee
	 */
	public Employee getExamResponseEmployee() {
		return examResponseEmployee;
	}

	/**
	 * @param examRequesterDoctor the examRequesterDoctor to set
	 */
	public void setExamRequesterDoctor(Employee examRequesterDoctor) {
		this.examRequesterDoctor = examRequesterDoctor;
	}

	/**
	 * @param examResponseEmployee the examResponseEmployee to set
	 */
	public void setExamResponseEmployee(Employee examResponseEmployee) {
		this.examResponseEmployee = examResponseEmployee;
	}

	/*
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExamRecords [id=" + examRecordId + ", exams=" + medicalExam + ", admissionInfo=" + admissionInfo
				+ ", datetaken=" + datetaken + ", results=" + results + "]";
	}

}
