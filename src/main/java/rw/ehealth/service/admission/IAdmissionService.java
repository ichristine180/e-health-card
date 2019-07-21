
package rw.ehealth.service.admission;

import java.util.List;

import rw.ehealth.model.AdmissionInfo;

public interface IAdmissionService {

	/**
	 * The constant NAME - String
	 */
	String NAME = "admissionService";

	/**
	 * @param info
	 * @return
	 */
	AdmissionInfo createNewPatientAdmission(AdmissionInfo info);

	long countAdmission(Long hospitalId,boolean admissionStatus);

	/**
	 * All admission infos.
	 *
	 * @param hospitalname the hospitalname
	 * @return the list
	 */
	List<AdmissionInfo> allAdmissionsPerHospital(Long hospitalId,boolean admissionStatus);

	long countAdmissionBypatient(String pnumber, String hospitalname);
List<AdmissionInfo>listAdmissionInfosByPatients(String pnumber, String hospitalname);
AdmissionInfo findByPatientTruckingNumber(String patientTruckingNumber);
List<AdmissionInfo>listInfosByPatients(String pnumber);









}
