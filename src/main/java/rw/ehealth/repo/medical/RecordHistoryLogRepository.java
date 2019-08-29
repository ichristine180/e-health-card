
package rw.ehealth.repo.medical;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.ehealth.model.RecordHistoryLog;

public interface RecordHistoryLogRepository extends JpaRepository<RecordHistoryLog, Long> {

	@Query("SELECT p,d,h FROM RecordHistoryLog p  JOIN p.viewer d JOIN p.hospital h JOIN p.patient pa WHERE pa.patientNumber=:patientNumber")
	List<RecordHistoryLog> viewHistory(@Param("patientNumber") String patientNumber);
}
