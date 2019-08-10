package rw.ehealth.service.medical;

import rw.ehealth.model.ViewRecordRequest;

public interface IViewRequestService {

	ViewRecordRequest findRequestByPatient(String patientNumber);

	ViewRecordRequest createRequest(ViewRecordRequest request);

	ViewRecordRequest findPRequest(String patientNumber, String requestDate);

	ViewRecordRequest update(ViewRecordRequest request);

}
