
package rw.ehealth.repo.medical;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.Consultation;
import rw.ehealth.model.Doctor;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

	/**
	 * @param info
	 * @return
	 */
	Consultation findByAdmissionInfo(AdmissionInfo info);

	/**
	 * @param doctor
	 * @return
	 */
	List<Consultation> findByDoctor(Doctor doctor);

	/**
	 * @param id
	 * @return
	 */
	Consultation findByConsultationId(Long id);

}
