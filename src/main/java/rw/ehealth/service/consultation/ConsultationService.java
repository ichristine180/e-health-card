
package rw.ehealth.service.consultation;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ehealth.model.Consultation;
import rw.ehealth.model.Employee;
import rw.ehealth.model.Hospital;
import rw.ehealth.repo.medical.ConsultationRepository;

@Service
public class ConsultationService implements IConsultationService {

	@Autowired
	private ConsultationRepository cRepository;
	@Override
	public Consultation createConsultation(Consultation consultation) {
		Consultation admitConsultation = findByPatientTruckingNumber(
				consultation.getAdmission().getPatientTrackingNumber());
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
		try {
			return cRepository.findAllInfoByPatient(pnumber);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public Long countPatientByGender(Long id, String gender) {
		try {
			return cRepository.CountByGender(id, gender);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Consultation update(Consultation consult) {
		try {
			return cRepository.save(consult);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Consultation> findConsuledPatient(Long hospitalId, Long departmentId) {
		try {
			System.out.println(cRepository.findConsuledPatients(hospitalId, departmentId).size() + "SIZE");
			return cRepository.findConsuledPatients(hospitalId, departmentId);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public long countConsultation(Long hospitalId, Long departmentId) {
		try {
			return cRepository.countConsultation(hospitalId, departmentId);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public long countByHospital(Hospital hospital) {
		try {
			return cRepository.countByHospital(hospital);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Consultation> findByDoctor(Employee doctor) {
		try {
			return cRepository.findByDoctor(doctor);
		} catch (Exception e) {
			throw e;
		}
	}
}
