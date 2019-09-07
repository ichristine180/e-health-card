package rw.ehealth.service.user;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ehealth.enums.ELevelType;
import rw.ehealth.model.Department;
import rw.ehealth.model.Hospital;
import rw.ehealth.repo.user.DepartemtRepository;

@Service
public class DepartemtService implements IDepartemtService {
	@Autowired
	private DepartemtRepository dRepository;

	@Override
	public List<Department> findAllDepartemts() {
		try {
			return dRepository.findAll();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public Department findPerName(String name) {
		try {
			return dRepository.findByName(name);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public Department findPerId(Long id) {
		try {
			return dRepository.findByDepartmentId(id);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<Department> findPerHospital(Hospital hospital) {
		try {
			return dRepository.findByHospitals(hospital);
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * @param hospital
	 * @return
	 */
	@Override
	public List<Department> findLvelOneDepartmentsPerHospital(Hospital hospital) {
		List<Department> levelOneDepartments = new ArrayList<>();
		try {
			List<Department> departments = dRepository.findByHospitals(hospital);
			for (Department department : departments) {
				if (department.getLevel().equals(ELevelType.LEVEL_ONE)) {
					levelOneDepartments.add(department);
				}
			}
			System.out.println(levelOneDepartments.size() + " jsss");
			return levelOneDepartments;
		} catch (Exception e) {
			throw e;
		}
	}

}
