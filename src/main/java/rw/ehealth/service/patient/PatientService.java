
package rw.ehealth.service.patient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ehealth.model.Patient;
import rw.ehealth.repo.medical.PatientRepository;

@Service(IPatientService.NAME)
public class PatientService implements IPatientService {

	@Autowired
	private PatientRepository pRepository;

	/*
	 *
	 * @see rw.ehealth.service.patient.IPatientService#savePatientInfo(rw.ehealth.model.Patient)
	 */
	@Override
	public Patient savePatientInfo(Patient patient) {
		Patient savedpatient = findPatientById(patient.getIdentificationNumber());
		String idnbr = patient.getIdentificationNumber();
		if (idnbr.isEmpty()) {
			System.out.println("id number can not be empty");
			return null;
		} else if (savedpatient != null) {
			System.out.println(" patient already registered");
			return null;

		} else
			try {

				return pRepository.save(patient);
			} catch (Exception ex) {
				throw ex;
			}
	}

	public Patient findPatientById(String identificationNumber) {

		try {
			return pRepository.findByIdentificationNumber(identificationNumber);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public long countPatient() {
		try {
			return pRepository.count();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public Patient findPatientByIdentificationNumber(String IdNumber) {
		try {
			Patient pateintPatient = pRepository.findByIdentificationNumber(IdNumber);
			if (pateintPatient != null) {
				return pateintPatient;
			} else {
				System.out.println("there is no patient with that id number");
				return null;
			}

		} catch (Exception e) {
			throw e;
		}

	}

	/*
	 *
	 * @see rw.ehealth.service.patient.IPatientService#findPatientByPatientNumber(java.lang.String)
	 */
	@Override
	public Patient findPatientByPatientNumber(String patientNumber) {
		try {
			return pRepository.findByPatientNumber(patientNumber);
		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * @return
	 */
	@Override
	public List<Patient> findpAll() {
		try {
			return pRepository.findAll();
		} catch (Exception ex) {
			throw ex;
		}
	}
	@Override
	public List<Patient> findAll() {
		try {
			return pRepository.findpAll();
		} catch (Exception ex) {
			throw ex;
		}
	}
	/*
	 *
	 * @see rw.ehealth.service.patient.IPatientService#updatePatient(rw.ehealth.model.Patient)
	 */
	@Override
	public Patient updatePatient(Patient patient) {
		try {
			return pRepository.save(patient);
		} catch (Exception ex) {
			throw ex;
		}
	}

	public Patient findByPatientId(Long patientId) {
		try {
			return pRepository.findByPatientId(patientId);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public Patient findByAdmissionStatus(String patientNumber) {
		try {
			return pRepository.findByAdmissionStatus(patientNumber);
		} catch (Exception e) {
			throw e;
		}

	}
}
