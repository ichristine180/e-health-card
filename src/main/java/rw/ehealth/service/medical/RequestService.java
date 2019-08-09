package rw.ehealth.service.medical;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ehealth.model.Request;
import rw.ehealth.repo.medical.RequestRepository;

@Service
public class RequestService  implements IrequestService{
	@Autowired
	private RequestRepository rRepository;

	@Override
	public Request findRequestByPatient(String patientNumber) {
		
		return rRepository.findRequestBypatient(patientNumber,LocalDate.now().toString());
	}

	@Override
	public Request createRequest(Request request) {
		try {
		return rRepository.save(request);
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	public Request findPRequest(String patientNumber,String requestDate) {
		try {
		return rRepository.findRequest(patientNumber,requestDate);
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	public Request update(Request request) {
		try {
			return rRepository.save(request);
			}catch(Exception e) {
				throw e;
			}
		}
}
