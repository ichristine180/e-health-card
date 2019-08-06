
package rw.ehealth.service.admission;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ehealth.model.AdmissionInfo;
import rw.ehealth.model.Patient;
import rw.ehealth.repo.medical.AdmissionInfoRepository;

@Service(IAdmissionService.NAME)
public class AdmissionService implements IAdmissionService {

	@Autowired
	private AdmissionInfoRepository aRepository;

	/*
	 *
	 * @see com.us.service.admission.IAdmissionService#createNewPatientAdmission(com.us.models.AdmissionInfo)
	 */
	@Override
	public AdmissionInfo createNewPatientAdmission(AdmissionInfo info) {
		try {
			if (info.getAdmittedPatient() == null) {
				System.out.println("Cant admit an invalid patient");
				return null;
			} else if (info.getDoctor() == null) {
				System.out.println("A doctor is required to admit a patient");
				return null;
			} else {
				// Generate a patient tracking number randomly and save the data
				info.setPatientTrackingNumber(RandomStringUtils.random(10, true, true));
				return aRepository.save(info);
			}
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public long countAdmission(Long hospitalId) {
		try {
			return aRepository.countAdmissionInfo(hospitalId);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<AdmissionInfo> allAdmissionsPerHospital(Long hospitalId) {
		try {
			return aRepository.allAdmissionInfos(hospitalId);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public long countAdmissionBypatient(String pnumber, String hospitalname) {
		try {
			return aRepository.countAdmissionBypatient(pnumber, hospitalname);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<AdmissionInfo> listAdmissionInfosByPatients(String pnumber, String hospitalname) {
		try {
			return aRepository.listAdmissionInfosByPatients(pnumber, hospitalname);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public AdmissionInfo findByPatientTruckingNumber(String patientTruckingNumber) {
		try {
			return aRepository.findByPatientTrackingNumber(patientTruckingNumber);
		} catch (Exception e) {
			throw e;
		}

	}

	public List<AdmissionInfo> AdmissionInfos(Long hospitalId, boolean admissionStatus, String departement) {
		try {
			return aRepository.AdmissionInfos(hospitalId, admissionStatus, departement);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<AdmissionInfo> listInfosByPatients(String pnumber) {
		try {
			return aRepository.listInfosByPatients(pnumber);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<AdmissionInfo> findByAdmittedPatient(Patient patient) {
		try {
			return aRepository.findByAdmittedPatient(patient);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public AdmissionInfo update(AdmissionInfo admissionInfo) {
		try {
			return aRepository.save(admissionInfo);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public AdmissionInfo findBYpatientNumber(String patientNumber) {
		try {
			return aRepository.findBYpatientNumber(patientNumber);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<AdmissionInfo> findHospitalBYpatientNumber(String patientNumber) {
		try {
			return aRepository.findHospitalBYpatientNumber(patientNumber);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<AdmissionInfo> findPAdmissionInfoBYpatientNumber(String patientNumber, Long hospitalId) {
		try {
			return aRepository.findAdmissionInfoBYpatientNumber(patientNumber, hospitalId);
		} catch (Exception e) {
			throw e;
		}

	}

	/*
	 *
	 * @see rw.ehealth.service.admission.IAdmissionService#getAdmissionsPerMonth(int)
	 */
	@Override
	public List<AdmissionInfo> getAdmissionsPerMonth(int month) {
		List<AdmissionInfo> allAdmissions = aRepository.findAll();
		System.out.println(allAdmissions.size() + " all admission");
		List<AdmissionInfo> response = new ArrayList<>();
		for (AdmissionInfo admissionInfo : allAdmissions) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate admissionDate = LocalDate.parse(admissionInfo.getAdmissionDate(),formatter);
			if (admissionDate.getMonthValue() == month) {
				response.add(admissionInfo);
			}
		}
		return response;
	}

	@Override
	public List<AdmissionInfo> findBydoctor(String email) {
		try {
		return aRepository.findAdmissionByDoctor(email);
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	public List<AdmissionInfo> findByGender(String gender) {
		try {
		return aRepository.findByGender(gender);
		}catch(Exception e) {
			throw e;
		}
	}
}
