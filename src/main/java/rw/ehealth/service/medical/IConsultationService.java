package rw.ehealth.service.medical;

import java.util.List;

import rw.ehealth.model.Consultation;

public interface IConsultationService {

	String NAME = "consultationService";

	Consultation createConsultation(Consultation consultation);

	Consultation findByPatientTruckingNumber(String pTrackingNumber);

	List<Consultation> findAllInfoByPatient(String pnumber);

	Long countPatientByGender(Long id, String gender);

}
