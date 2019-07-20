package rw.ehealth.service.medical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ehealth.model.Consultation;
import rw.ehealth.repo.medical.ConsultationRepository;

@Service
public class ConsultationService implements IconsultationService{
	@Autowired
	private ConsultationRepository cRepository;

	@Override
	public Consultation createConsultation(Consultation consultation) {
		Consultation admitConsultation = findByPatientTruckingNumber(consultation.getAdmissionInfo().getPatientTrackingNumber());
		try {
			if (admitConsultation == null) {
			return cRepository.save(consultation);
			}else
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

}
