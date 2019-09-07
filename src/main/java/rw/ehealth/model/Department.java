
package rw.ehealth.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import rw.ehealth.enums.ELevelType;

@Entity
@Table(name = "departemt")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id", nullable = false, updatable = false)
	private Long departmentId;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;
	@JsonIgnore
	@ManyToMany(mappedBy = "departments")
	private Set<Hospital> hospitals = new HashSet<>();

	@Column(name = "LEVEL")
	@Enumerated(EnumType.STRING)
	private ELevelType level;

	/**
	 * @return the level
	 */
	public ELevelType getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(ELevelType level) {
		this.level = level;
	}

	/**
	 * @return the departmentId
	 */
	public Long getDepartmentId() {
		return departmentId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the abbreviation
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param abbreviation the abbreviation to set
	 */
	public void setCode(String abbreviation) {
		this.code = abbreviation;
	}

	/**
	 * @return the hospitals
	 */
	public Set<Hospital> getHospitals() {
		return hospitals;
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
		return "Department [departmentId=" + departmentId + ", name=" + name + ", code=" + code + "]";
	}

}
