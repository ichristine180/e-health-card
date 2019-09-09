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
@Table(name = "prescriptions")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Prescription {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id", nullable = false, updatable = false)
	private Long id;
	/**
	 * The constant name - String
	 */
	@Column(name = "name", length = 200)
	@NotNull(message = "Invalid Prescription data")
	private String name;
	/**
	 * The constant description - String
	 */
	@Column(name = "description", length = 200)
	@NotNull(message = "Invalid description data")
	private String description;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admissionId")
	private Admission admission;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prescribedBy")
	private Employee prescribedBy;
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
	public Admission getAdmission() {
		return admission;
	}

	/**
	 * @param admissionInfo the admissionInfo to set
	 */
	public void setAdmission(Admission admissionInfo) {
		this.admission = admissionInfo;
	}

	/**
	 * @return the prescribedBy
	 */
	public Employee getPrescribedBy() {
		return prescribedBy;
	}

	/**
	 * @param prescribedBy the prescribedBy to set
	 */
	public void setPrescribedBy(Employee prescribedBy) {
		this.prescribedBy = prescribedBy;
	}

	/*
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Prescription [id=" + id + ", name=" + name + ", description=" + description + ", admission=" + admission
				+ ", prescribedBy=" + prescribedBy + ", hospital=" + hospital + "]";
	}

}
