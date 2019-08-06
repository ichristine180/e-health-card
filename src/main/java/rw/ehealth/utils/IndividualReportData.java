package rw.ehealth.utils;

import java.util.List;

import rw.ehealth.model.AdmissionInfo;

public class IndividualReportData {
	
List<AdmissionInfo> admissionInfos;
private int count;

public List<AdmissionInfo> getAdmissionInfos() {
	return admissionInfos;
}
public void setAdmissionInfos(List<AdmissionInfo> admissionInfos) {
	this.admissionInfos = admissionInfos;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
}
