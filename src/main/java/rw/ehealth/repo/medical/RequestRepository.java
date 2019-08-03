package rw.ehealth.repo.medical;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.ehealth.model.Request;



public interface RequestRepository extends JpaRepository<Request, Long>{
	@Query("SELECT r FROM Request r JOIN r.patient p WHERE p.patientNumber=:patientNumber and r.status='APPROVED'")
	Request findRequestBypatient(@Param("patientNumber") String patientNumber);
	@Query("SELECT r FROM Request r JOIN r.patient p WHERE p.patientNumber=:patientNumber and r.status!='APPROVED' or r.status!='DENYED' ")
	Request findRequest(@Param("patientNumber") String patientNumber);

}
