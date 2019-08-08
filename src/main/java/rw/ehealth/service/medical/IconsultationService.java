package rw.ehealth.service.medical;

import java.util.List;

import rw.ehealth.model.Consultation;
import rw.ehealth.model.Request;

public interface IconsultationService {
	String name = "consultationService";
Consultation createConsultation(Consultation consultation);
Consultation findByPatientTruckingNumber(String pTrackingNumber);
List<Consultation>findAllInfoByPatient(String pnumber);
Long countPatientByGender(Long id,String gender);

}
