
package rw.ehealth.service.hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ehealth.model.Department;
import rw.ehealth.model.Hospital;
import rw.ehealth.repo.medical.HospitalRepository;
import rw.ehealth.utils.report.DepartmentReport;

@Service(IHospitalService.name)
public class HospitalService implements IHospitalService {

	@Autowired
	private HospitalRepository hRepository;

	/*
	 *
	 * @see rw.ehealth.service.medical.IHospitalService#createHospital(rw.ehealth.model.Hospital)
	 */
	@Override
	public Hospital createHospital(Hospital hospital) {
		Hospital savedHospital = hRepository.findByHospitalName(hospital.getHospitalName());
		try {
			if (savedHospital != null) {
				System.out.println("hospital already saved");
				return null;
			}
			return hRepository.save(hospital);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public Hospital updateHospital(Hospital hospital) {
		try {
			return hRepository.save(hospital);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public void deleteHospital(Hospital hospital) {
		try {
			hRepository.delete(hospital);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public List<Hospital> findAllHospitals() {
		try {
			return hRepository.findAll();
		} catch (Exception ex) {
			throw ex;
		}

	}

	/*
	 *
	 * @see rw.ehealth.service.medical.IHospitalService#findHospitalById(java.lang.Long)
	 */
	@Override
	public Hospital findHospitalById(Long id) {
		try {
			return hRepository.findByHospitalId(id);
		} catch (Exception ex) {
			throw ex;
		}
	}

	/*
	 *
	 * @see rw.ehealth.service.medical.IHospitalService#findByHospitalname(java.lang.String)
	 */
	@Override
	public Hospital findByHospitalname(String hospitalname) {
		try {
			return hRepository.findByHospitalName(hospitalname);
		} catch (Exception ex) {
			throw ex;
		}
	}

	/*
	 *
	 * @see rw.ehealth.service.medical.IHospitalService#countHospital()
	 */
	@Override
	public long countHospital() {
		try {
			return hRepository.count();
		} catch (Exception ex) {
			throw ex;
		}
	}

	/*
	 *
	 * @see rw.ehealth.service.medical.IHospitalService#findHospitalByCode(java.lang.String)
	 */
	@Override
	public Hospital findHospitalByCode(String hospitalCode) {
		return hRepository.findByHospitalCode(hospitalCode);
	}

	/*
	 *
	 * @see rw.ehealth.service.medical.IHospitalService#getDepartmentStatistics(java.lang.String)
	 */
	@Override
	public List<DepartmentReport> getDepartmentStatistics(String hospitalCode) {
		List<DepartmentReport> departmentReports = new ArrayList<>();

		Set<Department> departments = hRepository.findByHospitalCode(hospitalCode).getDepartments();
		System.out.println(departments.size() + " dep size");
		for (Department department : departments) {
			DepartmentReport dReport = new DepartmentReport();
			dReport.setDepartment(department);
			departmentReports.add(dReport);

		}

		return departmentReports;
	}
}
