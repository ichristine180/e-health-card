
package rw.ehealth.repo.medical;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.ehealth.model.Doctor;



public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	long count();
	@Query("SELECT t from Doctor t  JOIN t.user  a Join t.hospital h  WHERE a.username=:username")
	Doctor findDoctor(@Param("username") String username);
	@Query("SELECT t from Doctor t JOIN t.hospital h")
	List<Doctor>finDoctors();
	
}
