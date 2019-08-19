package rw.ehealth.repo.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.ehealth.model.Department;
import rw.ehealth.model.Hospital;

@Repository
public interface DepartemtRepository extends JpaRepository<Department, Long> {
	List<Department> findAll();
	Department findByName( String name);
	List<Department> findByHospitals(Hospital hospital);
	Department findByDepartmentId(Long id);

}
