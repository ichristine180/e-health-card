package rw.ehealth.service.medical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ehealth.model.ExamRecords;
import rw.ehealth.repo.medical.ExamRecordsRepository;

@Service(IexamRecordService.nameString)
public class ExamRecordService implements IexamRecordService{
	@Autowired
private ExamRecordsRepository eRepository;
	@Override
	public ExamRecords creaExamRecords(ExamRecords examRecords) {
		try {
			return eRepository.save(examRecords);
		} catch (Exception e) {
			throw e;
		}
		
	}

}
