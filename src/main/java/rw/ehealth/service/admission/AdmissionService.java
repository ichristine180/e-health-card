
package rw.ehealth.service.admission;

import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.repo.medical.AdmissionInfoRepository;

@Service(IAdmissionService.NAME)
public class AdmissionService implements IAdmissionService {

	@Autowired
	private AdmissionInfoRepository aRepository;

	/*
	 *
	 * @see com.us.service.admission.IAdmissionService#createNewPatientAdmission(com.us.models.AdmissionInfo)
	 */
	@Override
	public AdmissionInfo createNewPatientAdmission(AdmissionInfo info) {
		try {
			if (info.getAdmittedPatient() == null) {
				System.out.println("Cant admit an invalid patient");
				return null;
			} else if (info.getDoctor() == null) {
				System.out.println("A doctor is required to admit a patient");
				return null;
			} else {
				// Generate a patient tracking number randomly and save the data
				info.setPatientTrackingNumber(RandomStringUtils.random(10, true, true));
				return aRepository.save(info);
			}
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public long countAdmission(String username) {
		try {
			return aRepository.countAdmissionInfo(username);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<AdmissionInfo> allAdmissionsPerHospital(String hospitalname) {
		try {
			return aRepository.allAdmissionInfos(hospitalname);
		} catch (Exception e) {
			throw e;
		}

	}

}
