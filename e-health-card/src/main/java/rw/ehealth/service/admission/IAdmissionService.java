
package rw.ehealth.service.admission;

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
	long countAdmission( String username);

}
