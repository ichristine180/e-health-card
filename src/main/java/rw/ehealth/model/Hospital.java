package rw.ehealth.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import rw.ehealth.enums.EHealthFacilityType;

@Entity
@Table(name = "hospital")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Hospital {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hospitalId", nullable = false, updatable = false)
	private Long hospitalId;
	/**
	 * The constant hospitalName - String
	 */
	@Column(name = "hospitalname")
	private String hospitalName;

	/** The hospital code. */
	@NotBlank
	@NotNull
	@Column(name = "hospitalCode", unique = true, nullable = false)
	private String hospitalCode;
	/**
	 * The constant type - EHealthFacilityType
	 */
	private EHealthFacilityType type;
	/**
	 * The constant address - String
	 */
	private String address;

	// Available departments
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
			name = "hospitalDepartments",
				joinColumns = { @JoinColumn(name = "departmentId") },
				inverseJoinColumns = { @JoinColumn(name = "hospitalId") })
	private Set<Department> departments = new HashSet<>();

	// Available exams
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
			name = "hospitalExams",
				joinColumns = { @JoinColumn(name = "hospitalId") },
				inverseJoinColumns = { @JoinColumn(name = "examId") })
	private Set<MedicalExam> exams = new HashSet<>();

	// Admissions
	@OneToMany(mappedBy = "hospital")
	private Set<Admission> admissions;

	// Consultations

	// ExamRecords

	// Prescriptions

	// Employees
	@OneToMany(mappedBy = "hospital")
	private Set<Employee> employees;

	/**
	 * @return the hospitalId
	 */
	public Long getHospitalId() {
		return hospitalId;
	}

	/**
	 * @return the hospitalName
	 */
	public String getHospitalName() {
		return hospitalName;
	}

	/**
	 * @return the type
	 */
	public EHealthFacilityType getType() {
		return type;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param hospitalId the hospitalId to set
	 */
	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	/**
	 * @param hospitalName the hospitalName to set
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(EHealthFacilityType type) {
		this.type = type;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the hospitalCode
	 */
	public String getHospitalCode() {
		return hospitalCode;
	}

	/**
	 * @param hospitalCode the hospitalCode to set
	 */
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	/**
	 * @return the departments
	 */
	public Set<Department> getDepartments() {
		return departments;
	}

	/**
	 * @param departments the departments to set
	 */
	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

	/**
	 * @return the exams
	 */
	public Set<MedicalExam> getExams() {
		return exams;
	}

	/**
	 * @param exams the exams to set
	 */
	public void setExams(Set<MedicalExam> exams) {
		this.exams = exams;
	}

	/*
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Hospital [hospitalId=" + hospitalId + ", hospitalName=" + hospitalName + ", hospitalCode="
				+ hospitalCode + ", type=" + type + ", address=" + address + "]";
	}

}
