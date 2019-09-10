
package rw.ehealth.service.record;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ehealth.model.Admission;
import rw.ehealth.model.Consultation;
import rw.ehealth.model.ExamRecord;
import rw.ehealth.model.Patient;
import rw.ehealth.model.Prescription;
import rw.ehealth.repo.medical.AdmissionRepository;
import rw.ehealth.repo.medical.ConsultationRepository;
import rw.ehealth.repo.medical.ExamRecordRepository;
import rw.ehealth.repo.medical.PrescriptionRepository;
import rw.ehealth.utils.report.MedicalRecordUtil;

@Service
public class MedicalRecordService implements IMedicalRecordService {

	@Autowired
	private AdmissionRepository aRepository;

	@Autowired
	private ConsultationRepository cRepository;

	@Autowired
	private ExamRecordRepository examRecordRepository;

	@Autowired
	private PrescriptionRepository prescriptionRepository;

	/*
	 *
	 * @see rw.ehealth.service.record.IMedicalRecordService#findPersonalMedicalRecord(rw.ehealth.model.Patient)
	 */
	@Override
	public List<MedicalRecordUtil> findPersonalMedicalRecord(Patient patient) {
		List<MedicalRecordUtil> personalMedicalRecordList = new ArrayList<>();
		try {

			List<Admission> admissionList = aRepository.listcompleteAdmissions(patient.getPatientNumber());
			for (Admission admission : admissionList) {
				MedicalRecordUtil medicalRecordUtil = new MedicalRecordUtil();

				Consultation consultation = cRepository.findByAdmission(admission);

				List<ExamRecord> examRecords = examRecordRepository.findByAdmissionInfo(admission);

				Prescription prescription = prescriptionRepository.findByAdmission(admission);

				medicalRecordUtil.setAdmission(admission);
				medicalRecordUtil.setConsultation(consultation);
				medicalRecordUtil.setExamRecords(examRecords);
				medicalRecordUtil.setPrescription(prescription);

				personalMedicalRecordList.add(medicalRecordUtil);

			}
		} catch (Exception ex) {
			throw ex;
		}
		return personalMedicalRecordList;
	}

}
