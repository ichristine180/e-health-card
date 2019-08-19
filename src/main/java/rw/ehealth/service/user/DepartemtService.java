package rw.ehealth.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
