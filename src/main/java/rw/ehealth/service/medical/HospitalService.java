
package rw.ehealth.service.medical;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import rw.ehealth.model.Hospital;
import rw.ehealth.repo.medical.HospitalRepository;

@Service(IHospitalService.name)
public class HospitalService implements IHospitalService {

	@Autowired
	private HospitalRepository hRepository;

	/*
	 *
	 * @see com.us.service.medical.IHospitalService#createHospital(com.us.models.Hospital)
	 */
	@Override
	public Hospital createHospital(Hospital hospital) {
		Hospital savedHospital = hRepository.findByHospitalName(hospital.getHospitalName());
		 if (savedHospital != null) {
			System.out.println("hospital already saved");
			return null;
		 }else {
		try {
			return hRepository.save(hospital);
		} catch (Exception ex) {
			throw ex;
		}
		 }
	}

	/*
	 *
	 * @see com.us.service.medical.IHospitalService#updateHospital(com.us.models.Hospital)
	 */
	@Override
	public Hospital updateHospital(Hospital hospital) {
		try {
			return hRepository.save(hospital);
		} catch (Exception ex) {
			throw ex;
		}
	}

	/*
	 *
	 * @see com.us.service.medical.IHospitalService#deleteHospital(com.us.models.Hospital)
	 */
	@Override
	public void deleteHospital(Hospital hospital) {
		try {
			hRepository.delete(hospital);
		} catch (Exception ex) {
			throw ex;
		}
	}


	/*
	 *
	 * @see com.us.service.medical.IHospitalQueryService#findAllHospitals()
	 */
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
	 * @see com.us.service.medical.IHospitalQueryService#findHospitalById(java.lang.Long)
	 */
	@Override
	public Hospital findHospitalById(Long id) {
		try {
			return hRepository.findByHospitalId(id);
		} catch (Exception ex) {
			throw ex;
		}
	}
	@Override
	public Hospital findByHospitalname(String hospitalname) {
		try {
			return hRepository.findByHospitalName(hospitalname);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public long countHospital() {
		try {
			return hRepository.count();
		} catch (Exception ex) {
			throw ex;
		}
	}
}
