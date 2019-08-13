
package rw.ehealth.repo.medical;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.ehealth.enums.EHealthFacilityType;
import rw.ehealth.model.Hospital;

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

	/**
	 * Find by hospital code.
	 *
	 * @param hospitalCode the hospital code
	 * @return the hospital
	 */
	Hospital findByHospitalCode(String hospitalCode);

}
