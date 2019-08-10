
package rw.ehealth.service.medical;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ehealth.model.RecordHistoryLog;
import rw.ehealth.repo.medical.RecordHistoryLogRepository;

@Service
public class ViewRecordHistoryService implements IViewRecordHistoryService {

	@Autowired
	private RecordHistoryLogRepository vRepo;

	@Override
	public RecordHistoryLog create(RecordHistoryLog patientRecordsViewHistory) {
		try {
			return vRepo.save(patientRecordsViewHistory);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<RecordHistoryLog> findMedicalHistory(String pnumber) {
		try {
			return vRepo.viewHistory(pnumber);
		} catch (Exception e) {
			throw e;
		}
	}

}
