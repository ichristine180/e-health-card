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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ExamRecords {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "examrecordid", nullable = false, updatable = false)
	private Long id;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "examid")
	private Exams exams;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hospitalId", nullable = false)
	private Hospital hospital;
	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Exams getExams() {
		return exams;
	}

	public void setExams(Exams exams) {
		this.exams = exams;
	}
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "admissionid")
	private AdmissionInfo admissionInfo;
	private String datetaken;
	private String results;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AdmissionInfo getAdmissionInfo() {
		return admissionInfo;
	}

	public void setAdmissionInfo(AdmissionInfo admissionInfo) {
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

	/*
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExamRecords [id=" + id + ", exams=" + exams + ", admissionInfo=" + admissionInfo + ", datetaken="
				+ datetaken + ", results=" + results + "]";
	}

}
