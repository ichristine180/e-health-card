package rw.ehealth.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import rw.ehealth.enums.EHealthFacilityType;


@Entity
@Table(name = "hospital")
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
	/**
	 * The constant type - EHealthFacilityType
	 */
	private EHealthFacilityType type;
	/**
	 * The constant address - String
	 */
	private String address;

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

	/*
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Hospital [hospitalId=" + hospitalId + ", hospitalName=" + hospitalName + ", type=" + type + ", address="
				+ address + "]";
	}

}
