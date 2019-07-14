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
@Table(name = "doctor")
public class Doctor {
	/**
	 * The constant docId - Long
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "docId", nullable = false, updatable = false)
	private Long docId;
	/**
	 * The constant fname - String
	 */
	@Column(name = "fname", nullable = false)
	private String fname;
	/**
	 * The constant lname - String
	 */
	@Column(name = "lname", nullable = false)
	private String lname;
	/**
	 * The constant email - String
	 */
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	/**
	 * The constant phone - String
	 */
	@Column(name = "phone", nullable = false)
	private String phone;
	/**
	 * The constant depertment - String
	 */
	@Column(name = "depertment", nullable = false)
	private String depertment;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hospitalId", nullable = false)
	private Hospital hospital;
	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="accountid",nullable=false)
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
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

	public String getDepertment() {
		return depertment;
	}

	public void setDepertment(String depertment) {
		this.depertment = depertment;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
}
