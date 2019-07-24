package rw.ehealth.repo.medical;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.ehealth.model.ExamRecords;

@Repository
public interface ExamRecordsRepository extends JpaRepository<ExamRecords, Long> {
	

}
