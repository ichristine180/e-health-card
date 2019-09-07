package rw.ehealth.utils;

import javax.validation.constraints.NotNull;

import rw.ehealth.enums.EHealthFacilityType;

public class HopitaData {
@NotNull(message = "hospitalname is required")
private String hospitalname;
private EHealthFacilityType type;
@NotNull(message = "address is required")
private String address;
@NotNull(message = "hospitalCode is required")
private String hospitalCode;


public String getHospitalCode() {
	return hospitalCode;
}

public void setHospitalCode(String hospitalCode) {
	this.hospitalCode = hospitalCode;
}

public EHealthFacilityType getType() {
	return type;
}

public void setType(EHealthFacilityType type) {
	this.type = type;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getHospitalname() {
	return hospitalname;
}

public void setHospitalname(String hospitalname) {
	this.hospitalname = hospitalname;
}




}
