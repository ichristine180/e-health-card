
package rw.ehealth.service.hospital;

import java.util.List;

import rw.ehealth.model.Hospital;
import rw.ehealth.utils.report.DepartmentReport;

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

	/**
	 * Find hospital by code.
	 *
	 * @param hospitalCode the hospital code
	 * @return the hospital
	 */
	Hospital findHospitalByCode(String hospitalCode);

	/**
	 * Gets the department statistics.
	 *
	 * @param hospitalCode the hospital code
	 * @return the department statistics
	 */
	List<DepartmentReport> getDepartmentStatistics(String hospitalCode);
}
