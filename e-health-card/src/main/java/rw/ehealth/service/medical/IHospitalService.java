
package rw.ehealth.service.medical;

import java.util.List;

import rw.ehealth.model.Hospital;


public interface IHospitalService {

	String name = "hospitalService";

	/**
	 * @param hospital
	 * @return
	 */
	Hospital createHospital(Hospital hospital);

	/**
	 * @param hospital
	 * @return
	 */
	Hospital updateHospital(Hospital hospital);

	/**
	 * @param hospital
	 */
	void deleteHospital(Hospital hospital);
	
	List<Hospital> findAllHospitals();

	/**
	 * @param id
	 * @return
	 */
	Hospital findHospitalById(Long id);

	Hospital findByHospitalname(String hospitalname);
	long countHospital();
}
