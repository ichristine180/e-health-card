package rw.ehealth.service.user;

import java.util.List;

import rw.ehealth.model.Department;
import rw.ehealth.model.Hospital;

public interface IDepartemtService {

	List<Department> findAllDepartemts();

	Department findPerName(String name);

	List<Department> findPerHospital(Hospital hospital);

	Department findPerId(Long id);

	/**
	 * Find lvel one departments per hospital.
	 *
	 * @param hospital the hospital
	 * @return the list
	 */
	List<Department> findLvelOneDepartmentsPerHospital(Hospital hospital);
}
