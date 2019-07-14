package rw.ehealth.repo.medical;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rw.ehealth.model.Exams;


@Repository
public interface ExamsRepository extends JpaRepository<Exams, Long> {
	@Query("SELECT t FROM Exams t where t.name = :name")
	List<Exams> findExamsPername(@Param("name") String name);

	@Query("SELECT t from Exams t  JOIN t.admissionInfo  a JOIN a.admittedPatient p  WHERE p.patientId=:patientId")
	List<Exams> findAllExamsByPatient(@Param("patientId") Long patientId);

}