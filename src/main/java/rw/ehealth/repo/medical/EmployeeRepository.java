
package rw.ehealth.repo.medical;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.ehealth.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	@Override
	long count();

	@Query("SELECT t from Employee t  JOIN t.user  a Join t.hospital h  WHERE a.username=:username")
	Employee findDoctor(@Param("username") String username);

	@Query("SELECT t from Employee t JOIN t.hospital h")
	List<Employee> finDoctors();

	Employee findByEmployeeId(Long id);

}
