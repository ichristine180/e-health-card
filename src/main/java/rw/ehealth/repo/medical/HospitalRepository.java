
package rw.ehealth.repo.medical;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rw.ehealth.enums.EHealthFacilityType;
import rw.ehealth.model.Hospital;
import rw.ehealth.report.AdmissionReport;
import rw.ehealth.report.HospitalReport;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

	@Override
	List<Hospital> findAll();

	/**
	 * Find by hospital id.
	 *
	 * @param id the id
	 * @return the hospital
	 */
	Hospital findByHospitalId(Long id);

	/**
	 * Find by type.
	 *
	 * @param type the type
	 * @return the list
	 */
	List<Hospital> findByType(EHealthFacilityType type);

	/**
	 * Find by hospital name.
	 *
	 * @param hospitalName the hospital name
	 * @return the hospital
	 */
	Hospital findByHospitalName(String hospitalName);

	@Override
	long count();

	@Query("SELECT new rw.ehealth.report.HospitalReport(a,d,c,p,e) from Hospital h JOIN h.admissions a join a.admittedPatient pa join h.Consultations c join h.Prescriptions p join h.ExamRecords e join h.departments WHERE h.hospitalId=:hospitalId and a.releasedDate is null and a.status=:status and d.departmentId=:id")
	List<HospitalReport> consultadedpatients(@Param("hospitalId") Long hospitalId, @Param("status") String status,

			@Param("id") Long id);

}
