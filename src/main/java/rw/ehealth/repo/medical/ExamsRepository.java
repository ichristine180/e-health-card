package rw.ehealth.repo.medical;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.ehealth.model.Exams;


@Repository
public interface ExamsRepository extends JpaRepository<Exams, Long> {
	List<Exams>findAll();
}
