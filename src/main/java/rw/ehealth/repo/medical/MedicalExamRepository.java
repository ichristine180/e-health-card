
package rw.ehealth.repo.medical;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.ehealth.model.MedicalExam;

@Repository
public interface MedicalExamRepository extends JpaRepository<MedicalExam, Long> {
	@Override
	List<MedicalExam> findAll();

	MedicalExam findByExamId(long id);
}
