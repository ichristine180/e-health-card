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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "employee")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Employee {
	/**
	 * The constant docId - Long
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "docId", nullable = false, updatable = false)
	private Long employeeId;
	/**
	 * The constant fname - String
	 */
	@NotNull
	@NotBlank(message = "The first name is required")
	@Pattern(regexp = "[a-z-A-Z]*", message = "First name has invalid characters")
	@Column(name = "fname", nullable = false)
	private String fname;
	/**
	 * The constant lname - String
	 */
	@NotNull
	@NotBlank(message = "The Last name is required")
	@Pattern(regexp = "[a-z-A-Z]*", message = "Last name has invalid characters")
	@Column(name = "lname", nullable = false)
	private String lname;
	/**
	 * The constant email - String
	 */

	@NotBlank(message = "The Email is required")
	@Email(message = "Valid email is Requird")
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	/**
	 * The constant phone - String
	 */
	@Pattern(regexp = "[0-9]+", message = "Invalid characters. Use digits only")
	@Size(min = 10, max = 10, message = "Valid phone number is 10 Digits")
	@NotNull(message = " This field cant be null")
	@Column(name = "phone", nullable = false)
	private String phone;

	@NotNull
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hospitalId", nullable = false)
	private Hospital hospital;

	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "accountid", nullable = false)
	private User user;

	/**
	 * The constant depertment
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "departmentId")
	private Department depertment;
	/**
	 * The constant timestamp - String
	 */
	@Column(name = "timestamp", nullable = false)
	private String timestamp;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Department getDepertment() {
		return depertment;
	}

	public void setDepertment(Department depertment) {
		this.depertment = depertment;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
}
