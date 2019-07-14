
package rw.ehealth.repo.medical;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.ehealth.enums.EHealthFacilityType;
import rw.ehealth.model.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

	/**
	 * @param id
	 * @return
	 */
	Hospital findByHospitalId(Long id);

	/**
	 * @param type
	 * @return
	 */
	List<Hospital> findByType(EHealthFacilityType type);

	Hospital findByHospitalName(String hospitalName);
	long count();
}
