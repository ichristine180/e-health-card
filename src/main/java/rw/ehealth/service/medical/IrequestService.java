package rw.ehealth.service.medical;


import rw.ehealth.model.Request;

public interface IrequestService {
	Request findRequestByPatient(String patientNumber);
	Request createRequest(Request request);
	Request findRequest(String patientNumber);
	Request update(Request request);

}
