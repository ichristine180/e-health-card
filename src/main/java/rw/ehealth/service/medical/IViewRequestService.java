package rw.ehealth.service.medical;

import rw.ehealth.model.ViewRecordRequest;

public interface IViewRequestService {

	ViewRecordRequest findRequestByPatient(String patientNumber);
	ViewRecordRequest findRequestByStatus(String patientNumber);

	ViewRecordRequest createRequest(ViewRecordRequest request);

	ViewRecordRequest findPRequest(String patientNumber);

	ViewRecordRequest update(ViewRecordRequest request);
	ViewRecordRequest findPRequestByAccessCode(String accessCode);

}
