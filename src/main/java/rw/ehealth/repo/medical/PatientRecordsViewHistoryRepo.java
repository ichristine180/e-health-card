package rw.ehealth.repo.medical;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.ehealth.model.PatientRecordsViewHistory;

public interface PatientRecordsViewHistoryRepo extends JpaRepository<PatientRecordsViewHistory, Long>  {
	@Query("SELECT p,d FROM PatientRecordsViewHistory p  JOIN p.doctor d JOIN p.hospital h JOIN p.patient pa WHERE pa.patientNumber=:patientNumber")
	List<PatientRecordsViewHistory> viewHistory(@Param("patientNumber") String patientNumber);
}
