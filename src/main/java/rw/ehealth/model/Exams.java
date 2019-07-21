package rw.ehealth.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	 * @param description the description to set
	 */
	

	
	

	/*
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Exams [examId=" + examId + ", name=" + name + "]";
	}

}
