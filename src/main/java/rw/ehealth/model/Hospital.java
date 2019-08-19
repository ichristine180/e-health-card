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

	private int departmentCount;

	private int serviceCount;

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
	@OneToMany(mappedBy = "hospital")
	private Set<Consultation> Consultations;

	public Set<Consultation> getConsultations() {
		return Consultations;
	}

	public void setConsultations(Set<Consultation> consultations) {
		Consultations = consultations;
	}

	public Set<Prescription> getPrescriptions() {
		return Prescriptions;
	}

	public void setPrescriptions(Set<Prescription> prescriptions) {
		Prescriptions = prescriptions;
	}

	// ExamRecords
	@OneToMany(mappedBy = "hospital")
	private Set<ExamRecord> ExamRecords;

	public Set<ExamRecord> getExamRecords() {
		return ExamRecords;
	}

	public void setExamRecords(Set<ExamRecord> examRecords) {
		ExamRecords = examRecords;
	}

	// Prescriptions
	@OneToMany(mappedBy = "hospital")
	private Set<Prescription> Prescriptions;
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

	/**
	 * @return the departmentCount
	 */
	public int getDepartmentCount() {
		return this.getDepartments().size();
	}

	/**
	 * @return the serviceCount
	 */
	public int getServiceCount() {
		return this.getExams().size();
	}

	/**
	 * @return the admissions
	 */
	public Set<Admission> getAdmissions() {
		return admissions;
	}

	/**
	 * @return the employees
	 */
	public Set<Employee> getEmployees() {
		return employees;
	}

	/**
	 * @param departmentCount the departmentCount to set
	 */
	public void setDepartmentCount(int departmentCount) {
		this.departmentCount = departmentCount;
	}

	/**
	 * @param serviceCount the serviceCount to set
	 */
	public void setServiceCount(int serviceCount) {
		this.serviceCount = serviceCount;
	}

	/**
	 * @param admissions the admissions to set
	 */
	public void setAdmissions(Set<Admission> admissions) {
		this.admissions = admissions;
	}

	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	/*
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Hospital [hospitalId=" + hospitalId + ", hospitalName=" + hospitalName + ", hospitalCode="
				+ hospitalCode + ", type=" + type + ", address=" + address + ", departments=" + departments
				+ ", departmentCount=" + departmentCount + ", serviceCount=" + serviceCount + ", exams=" + exams
				+ ", admissions=" + admissions + ", Consultations=" + Consultations + ", ExamRecords=" + ExamRecords
				+ ", Prescriptions=" + Prescriptions + ", employees=" + employees + "]";
	}
}
