
package rw.ehealth.service.prescription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ehealth.model.Prescription;
import rw.ehealth.repo.medical.PrescriptionRepository;

@Service
public class PrescriptionService implements IPrescriptionService {

	@Autowired
	PrescriptionRepository prepository;

	@Override
	public Prescription createPrescription(Prescription prescription) {
		try {
			return prepository.save(prescription);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public Prescription findPByPatientTruckingNumber(String patientTrackingNumber) {
		try {
			return prepository.findByPatientTruckingNumber(patientTrackingNumber);
		} catch (Exception e) {
			throw e;
		}
	}

}
