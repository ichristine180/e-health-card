package rw.ehealth.service.consultation;

import java.util.List;
import rw.ehealth.model.Consultation;

public interface IConsultationService {

	String NAME = "consultationService";

	Consultation createConsultation(Consultation consultation);

	Consultation findByPatientTruckingNumber(String pTrackingNumber);

	List<Consultation> findAllInfoByPatient(String pnumber);

	Long countPatientByGender(Long id, String gender);

	Consultation update(Consultation consult);
	List<Consultation> findConsuledPatient(Long hospitalId,String status,Long departmentId);

}
