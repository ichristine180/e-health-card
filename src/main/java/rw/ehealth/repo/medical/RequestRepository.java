package rw.ehealth.repo.medical;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.ehealth.model.Request;



public interface RequestRepository extends JpaRepository<Request, Long>{
	@Query("SELECT r FROM Request r JOIN r.patient p WHERE p.patientNumber=:patientNumber and r.status is 'APPROVED' and r.requestDate=:requestDate")
	Request findRequestBypatient(@Param("patientNumber") String patientNumber,@Param("requestDate") String requestDate);
	@Query("SELECT r FROM Request r JOIN r.patient p WHERE p.patientNumber=:patientNumber and r.requestDate=:requestDate")
	Request findRequest(@Param("patientNumber") String patientNumber,@Param("requestDate") String requestDate);

}
