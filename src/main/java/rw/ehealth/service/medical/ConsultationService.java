package rw.ehealth.service.medical;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ehealth.model.Consultation;
import rw.ehealth.model.Request;
import rw.ehealth.repo.medical.ConsultationRepository;

@Service
public class ConsultationService implements IconsultationService {
	@Autowired
	private ConsultationRepository cRepository;
	@Autowired
	private RequestService rService;

	@Override
	public Consultation createConsultation(Consultation consultation) {
		Consultation admitConsultation = findByPatientTruckingNumber(
				consultation.getAdmissionInfo().getPatientTrackingNumber());
		try {
			if (admitConsultation == null) {
				return cRepository.save(consultation);
			} else
				return null;
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public Consultation findByPatientTruckingNumber(String pTrackingNumber) {
		try {
			return cRepository.findByPatientTruckingNumber(pTrackingNumber);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<Consultation> findAllInfoByPatient(String pnumber) {
		Request requestt = rService.findRequestByPatient(pnumber);
		if(requestt!=null) {
		try {
			return cRepository.findAllInfoByPatient(pnumber);
		} catch (Exception e) {
			throw e;

		}
		}
		return null;
	}

	@Override
	public Long countPatientByGender(Long id, String gender) {
		try {
		return cRepository.CountByGender(id, gender);
		}catch(Exception e) {
			throw e;
		}
	}

}
