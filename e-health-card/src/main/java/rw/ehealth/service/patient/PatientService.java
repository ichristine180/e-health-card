
package rw.ehealth.service.patient;

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
	 * @see
	 * com.us.service.patient.IPatientService#savePatientInfo(com.us.models.Patient)
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
			}else {
				System.out.println("there is no patient with that id number");
				return null;
			}
			
		} catch (Exception e) {
			throw e;
		}
		
	}
}
