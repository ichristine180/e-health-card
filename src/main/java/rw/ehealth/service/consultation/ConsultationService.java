
package rw.ehealth.service.consultation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ehealth.model.Consultation;
import rw.ehealth.model.ViewRecordRequest;
import rw.ehealth.repo.medical.ConsultationRepository;
import rw.ehealth.service.medical.ViewRequestService;

@Service
public class ConsultationService implements IConsultationService {

	@Autowired
	private ConsultationRepository cRepository;

	@Autowired
	private ViewRequestService rService;

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
		ViewRecordRequest requestt = rService.findRequestByPatient(pnumber);
		if (requestt != null) {
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
			return cRepository.findConsuledPatients(hospitalId,departmentId);
		} catch (Exception e) {
			throw e;
		}
	}

}
