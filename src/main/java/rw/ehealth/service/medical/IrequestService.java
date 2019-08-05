package rw.ehealth.service.medical;


import rw.ehealth.model.Request;

public interface IrequestService {
	Request findRequestByPatient(String patientNumber);
	Request createRequest(Request request);
	Request findPRequest(String patientNumber,String statusS);
	Request update(Request request);

}
