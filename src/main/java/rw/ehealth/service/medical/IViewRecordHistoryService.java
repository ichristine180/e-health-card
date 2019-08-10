package rw.ehealth.service.medical;

import java.util.List;

import rw.ehealth.model.RecordHistoryLog;

public interface IViewRecordHistoryService {

	RecordHistoryLog create(RecordHistoryLog patientRecordsViewHistory);

	List<RecordHistoryLog> findMedicalHistory(String pnumber);

}
