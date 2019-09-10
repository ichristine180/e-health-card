package rw.ehealth.service.consultation;

import java.util.List;

import rw.ehealth.model.Consultation;
import rw.ehealth.model.Employee;
import rw.ehealth.model.Hospital;

public interface IConsultationService {

	String NAME = "consultationService";

	Consultation createConsultation(Consultation consultation);

	Consultation findByPatientTruckingNumber(String pTrackingNumber);

	List<Consultation> findAllInfoByPatient(String pnumber);

	Long countPatientByGender(Long id, String gender);
	long countByHospital(Hospital hospital);

	Consultation update(Consultation consult);
	List<Consultation> findConsuledPatient(Long hospitalId,Long departmentId);
	long countConsultation(Long hospitalId,
			Long departmentId);
	List<Consultation> findByDoctor(Employee doctor);

}
