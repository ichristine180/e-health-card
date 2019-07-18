
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

	long countAdmission(String username);

	/**
	 * All admission infos.
	 *
	 * @param hospitalname the hospitalname
	 * @return the list
	 */
	List<AdmissionInfo> allAdmissionsPerHospital(String hospitalname);

}
