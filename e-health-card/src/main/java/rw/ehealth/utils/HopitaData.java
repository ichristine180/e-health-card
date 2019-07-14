package rw.ehealth.utils;

import rw.ehealth.enums.EHealthFacilityType;

public class HopitaData {
private String hospitalname;
private EHealthFacilityType type;
private String address;


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
