
package rw.ehealth.service.record;

import java.util.List;

import rw.ehealth.model.Patient;
import rw.ehealth.utils.report.MedicalRecordUtil;

public interface IMedicalRecordService {

	/**
	 * Find personal medical record.
	 *
	 * @param patient the patient
	 * @return the list
	 */
	public List<MedicalRecordUtil> findPersonalMedicalRecord(Patient patient);

}
