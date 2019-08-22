package rw.ehealth.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "exams")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class MedicalExam {
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
	@Column(name = "name")
	private String name;

	@Column(name = "code", unique = true, nullable = false)
	private String code;
	@JsonIgnore
	@ManyToMany(mappedBy = "exams")
	private Set<Hospital> hospitals = new HashSet<>();

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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the hospitals
	 */
	public Set<Hospital> getHospitals() {
		return hospitals;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param hospitals the hospitals to set
	 */
	public void setHospitals(Set<Hospital> hospitals) {
		this.hospitals = hospitals;
	}

	/*
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MedicalExam [examId=" + examId + ", name=" + name + ", code=" + code + "]";
	}

}
