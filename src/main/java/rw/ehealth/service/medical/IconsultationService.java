package rw.ehealth.service.medical;

import rw.ehealth.model.Consultation;

public interface IconsultationService {
	String name = "consultationService";
Consultation createConsultation(Consultation consultation);
Consultation findByPatientTruckingNumber(String pTrackingNumber);
}
