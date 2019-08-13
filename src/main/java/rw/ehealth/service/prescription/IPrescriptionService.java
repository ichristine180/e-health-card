package rw.ehealth.service.prescription;

import rw.ehealth.model.Prescription;

public interface IPrescriptionService {

	Prescription createPrescription(Prescription prescription);

	Prescription findPByPatientTruckingNumber(String patientTrackingNumber);
}
