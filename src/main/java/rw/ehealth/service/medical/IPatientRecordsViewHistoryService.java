package rw.ehealth.service.medical;

import java.util.List;

import rw.ehealth.model.PatientRecordsViewHistory;

public interface IPatientRecordsViewHistoryService {
	PatientRecordsViewHistory create(PatientRecordsViewHistory patientRecordsViewHistory);
	List<PatientRecordsViewHistory> findMedicalHistory(String pnumber);

}
