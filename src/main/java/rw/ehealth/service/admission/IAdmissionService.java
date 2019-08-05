
package rw.ehealth.service.admission;

import java.util.List;


import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.Patient;

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

	long countAdmission(Long hospitalId);
	AdmissionInfo findBYpatientNumber(String patientNumber);
	List<AdmissionInfo>findBydoctor(String email);
	List<AdmissionInfo> findByGender(String gender);

	/**
	 * All admission infos.
	 *
	 * @param hospitalname the hospitalname
	 * @return the list
	 */
	List<AdmissionInfo> allAdmissionsPerHospital(Long hospitalId);

	long countAdmissionBypatient(String pnumber, String hospitalname);

	List<AdmissionInfo> listAdmissionInfosByPatients(String pnumber, String hospitalname);

	AdmissionInfo findByPatientTruckingNumber(String patientTruckingNumber);

	List<AdmissionInfo> listInfosByPatients(String pnumber);

	List<AdmissionInfo> findByAdmittedPatient(Patient patient);
AdmissionInfo update(AdmissionInfo admissionInfo);
List<AdmissionInfo> findHospitalBYpatientNumber(String patientNumber);
List<AdmissionInfo> findPAdmissionInfoBYpatientNumber( String patientNumber,Long hospitalId);
}
