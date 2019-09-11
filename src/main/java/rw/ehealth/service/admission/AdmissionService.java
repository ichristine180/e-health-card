
package rw.ehealth.service.admission;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ehealth.model.Admission;
import rw.ehealth.model.Hospital;
import rw.ehealth.model.Patient;
import rw.ehealth.repo.medical.AdmissionRepository;
import rw.ehealth.report.AdmissionReport;

@Service(IAdmissionService.NAME)
public class AdmissionService implements IAdmissionService {

	@Autowired
	private AdmissionRepository aRepository;

	@Override
	public Admission createNewPatientAdmission(Admission info) {
		if (stillAdmitted(info)) {
			System.out.println(" This person is admitted somewhere else");
			return aRepository.findActiveAdmission(info.getAdmittedPatient().getPatientNumber());
		}
		try {
			if (info.getAdmittedPatient() == null) {
				System.out.println("Cant admit an invalid patient");
				return null;
			} else if (info.getAdmittedBy() == null) {
				System.out.println("A doctor is required to admit a patient");
				return null;
			} else {
				return aRepository.save(info);
			}
		} catch (

		Exception ex) {
			throw ex;
		}

	}

	@Override
	public long countAdmission(Long hospitalId) {
		try {
			return aRepository.countAdmission(hospitalId);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<Admission> countAllAdmission(Long hospitalId) {
		try {
			return aRepository.countAllAdmission(hospitalId);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<Admission> allAdmissionsPerHospital(Long hospitalId) {
		try {
			return aRepository.allAdmissions(hospitalId);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public long countAdmissionBypatient(String pnumber, Long id) {
		try {
			return aRepository.countAdmissionBypatient(pnumber, id);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<Admission> listAdmissionsByPatients(String pnumber, Long id) {
		try {
			return aRepository.listAdmissionsByPatients(pnumber, id);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public Admission findByPatientTruckingNumber(String patientTruckingNumber) {
		try {
			return aRepository.findByPatientTrackingNumber(patientTruckingNumber);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<Admission> Admissions(Long hospitalId, Long id) {
		try {
			return aRepository.Admissions(hospitalId, id);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<Admission> listInfosByPatients(String pnumber) {
		try {
			return aRepository.listInfosByPatients(pnumber);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<Admission> findByAdmittedPatient(Patient patient) {
		try {
			return aRepository.findByAdmittedPatient(patient);
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 *
	 * @see rw.ehealth.service.admission.IAdmissionService#update(rw.ehealth.model.Admission)
	 */
	@Override
	public Admission update(Admission admissionInfo) {
		try {
			return aRepository.save(admissionInfo);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public Admission findBYpatientNumber(String patientNumber) {
		try {
			return aRepository.findBYpatientNumber(patientNumber);
		} catch (Exception e) {
			throw e;
		}

	}

	/*
	 *
	 * @see rw.ehealth.service.admission.IAdmissionService#findHospitalBYpatientNumber(java.lang.String)
	 */
	@Override
	public List<Admission> findHospitalBYpatientNumber(String patientNumber) {
		try {
			return aRepository.findHospitalBYpatientNumber(patientNumber);
		} catch (Exception e) {
			throw e;
		}

	}

	/*
	 *
	 * @see rw.ehealth.service.admission.IAdmissionService#findPAdmissionBYpatientNumber(java.lang.String,
	 * java.lang.Long)
	 */
	@Override
	public List<Admission> findPAdmissionBYpatientNumber(String patientNumber, Long hospitalId) {
		try {
			return aRepository.findAdmissionBYpatientNumber(patientNumber, hospitalId);
		} catch (Exception e) {
			throw e;
		}

	}

	/*
	 *
	 * @see rw.ehealth.service.admission.IAdmissionService#getAdmissionsPerMonth(int)
	 */
	@Override
	public List<Admission> getAdmissionsPerMonth(int month) {
		List<Admission> allAdmissions = aRepository.findAll();
		System.out.println(allAdmissions.size() + " all admission");
		List<Admission> response = new ArrayList<>();
		for (Admission admissionInfo : allAdmissions) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate admissionDate = LocalDate.parse(admissionInfo.getAdmissionDate(), formatter);
			if (admissionDate.getMonthValue() == month) {
				response.add(admissionInfo);
			}
		}
		return response;
	}

	@Override
	public List<AdmissionReport> findBydoctor(String email) {
		try {
			return aRepository.findAdmissionByDoctor(email);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<AdmissionReport> findByGender(String gender, Long hospitalId) {
		try {
			return aRepository.findByGender(gender, hospitalId);
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean stillAdmitted(Admission admission) {
		Admission activeAdmission = aRepository.findActiveAdmission(admission.getAdmittedPatient().getPatientNumber());
		boolean found = false;
		if (activeAdmission != null) {
			found = true;
		}
		return found;
	}

	// Kora implementation yiyi method
	@Override
	public Admission AdmissionInfos(Long hospitalId, Long departmentId) {
		// TODO Auto-generated method stub
		return null;

	}

	/*
	 *
	 * @see rw.ehealth.service.admission.IAdmissionService#findAllAdmission()
	 */
	@Override
	public List<Admission> findAllAdmission() {
		return aRepository.findAll();
	}

	/*
	 *
	 * @see rw.ehealth.service.admission.IAdmissionService#Admissions(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<Admission> midleAdmissions(Long hospitalId, Long id) {
		try {
			return aRepository.midleAdmissions(hospitalId, id);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public long countAdmissionfodoctor(Long hospitalId) {
		try {
			return aRepository.countAdmissionFordoctor(hospitalId);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public Admission listAdmissions(String patientNumber) {
		try {
			return aRepository.listAdmissions(patientNumber);
		} catch (Exception e) {
			throw e;
		}

	}

	/*
	 *
	 * @see rw.ehealth.service.admission.IAdmissionService#findUniqueHospitalByPatient(rw.ehealth.model.Patient)
	 */
	@Override
	public List<Hospital> findUniqueHospitalByPatient(Patient patient) {
		List<Hospital> uniqueHospital = new ArrayList<>();
		try {
			List<Admission> patientAdmissions = aRepository.findByAdmittedPatient(patient);
			if (!patientAdmissions.isEmpty()) {
				for (Admission admission : patientAdmissions) {
					uniqueHospital.add(admission.getHospital());
				}
			}
			return uniqueHospital.stream().distinct().collect(Collectors.toList());

		} catch (Exception ex) {
			throw ex;
		}
	}
}
