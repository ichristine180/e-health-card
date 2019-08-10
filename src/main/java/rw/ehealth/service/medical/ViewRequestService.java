
package rw.ehealth.service.medical;

import java.time.LocalDate;

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
		return rRepository.findViewRecordRequestBypatient(patientNumber, LocalDate.now().toString());

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
	public ViewRecordRequest findPRequest(String patientNumber, String requestDate) {
		try {
			return rRepository.findViewRecordRequest(patientNumber, requestDate);
		} catch (Exception e) {
			throw e;
		}
	}
}
