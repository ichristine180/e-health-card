package rw.ehealth.service.medical;

import rw.ehealth.model.Prescription;

public interface IPrescriptionService {

	Prescription createPrescription(Prescription prescription);

	Prescription findPByPatientTruckingNumber(String patientTrackingNumber);
}
