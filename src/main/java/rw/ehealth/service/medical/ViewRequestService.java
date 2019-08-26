
package rw.ehealth.service.medical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ehealth.model.ViewRecordRequest;
import rw.ehealth.repo.medical.ViewRecordRequestRepository;

@Service
public class ViewRequestService implements IViewRequestService {

	@Autowired
	private ViewRecordRequestRepository rRepository;

	@Override
	public ViewRecordRequest update(ViewRecordRequest request) {
		try {
			return rRepository.save(request);
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 *
	 * @see rw.ehealth.service.medical.IViewRequestService#findRequestByPatient(java.lang.String)
	 */
	@Override
	public ViewRecordRequest findRequestByPatient(String patientNumber) {
		return rRepository.findViewRecordRequest(patientNumber);

	}

	/*
	 *
	 * @see rw.ehealth.service.medical.IViewRequestService#createRequest(rw.ehealth.model.ViewRecordRequest)
	 */
	@Override
	public ViewRecordRequest createRequest(ViewRecordRequest request) {
		try {
			return rRepository.save(request);
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 *
	 * @see rw.ehealth.service.medical.IViewRequestService#findPRequest(java.lang.String, java.lang.String)
	 */
	@Override
	public ViewRecordRequest findPRequest(String patientNumber) {
		try {
			return rRepository.findViewRecordRequest(patientNumber);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public ViewRecordRequest findRequestByStatus(String patientNumber) {
		try {
			return rRepository.findViewRecordBystatus(patientNumber);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public ViewRecordRequest findPRequestByAccessCode(String accessCode) {
		try {
			return rRepository.findViewRecordRequestByAccessCode(accessCode);
		} catch (Exception e) {
			throw e;
		}
	}
}
