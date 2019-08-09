package rw.ehealth.service.medical;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ehealth.model.PatientRecordsViewHistory;
import rw.ehealth.repo.medical.PatientRecordsViewHistoryRepo;

@Service
public class PatientRecordsViewHistoryService implements IPatientRecordsViewHistoryService {
	@Autowired
	private PatientRecordsViewHistoryRepo vRepo;

	@Override
	public PatientRecordsViewHistory create(PatientRecordsViewHistory patientRecordsViewHistory) {
		try {
			return vRepo.save(patientRecordsViewHistory);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<PatientRecordsViewHistory> findMedicalHistory(String pnumber) {
		try {
		return vRepo.viewHistory(pnumber);
		}catch(Exception e ) {
			throw e;
		}
	}

}
