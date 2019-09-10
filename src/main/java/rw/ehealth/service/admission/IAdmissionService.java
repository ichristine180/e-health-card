package rw.ehealth.service.admission;

import java.util.List;

import rw.ehealth.model.Admission;
import rw.ehealth.model.Hospital;
import rw.ehealth.model.Patient;
import rw.ehealth.report.AdmissionReport;

public interface IAdmissionService {

	/**
	 * The constant NAME - String
	 */

	String NAME = "admissionService";

	/**
	 * @param info
	 * @return
	 */

	Admission createNewPatientAdmission(Admission info);

	long countAdmission(Long hospitalId);
	List<Admission> countAllAdmission(Long hospitalId);

	long countAdmissionfodoctor(Long hospitalId);

	Admission findBYpatientNumber(String patientNumber);

	List<AdmissionReport> findBydoctor(String email);

	Admission listAdmissions(String patientNumber);

	/**
	 * @param gender
	 * @return
	 */

	List<AdmissionReport> findByGender(String gender, Long hospitalId);

	/**
	 * All admission infos.
	 *
	 * @param hospitalname the hospitalname
	 * @return the list
	 */

	List<Admission> allAdmissionsPerHospital(Long hospitalId);

	long countAdmissionBypatient(String pnumber, Long id);

	List<Admission> listAdmissionsByPatients(String pnumber, Long id);

	Admission findByPatientTruckingNumber(String patientTruckingNumber);

	List<Admission> listInfosByPatients(String pnumber);

	List<Admission> findByAdmittedPatient(Patient patient);

	Admission update(Admission admissionInfo);

	List<Admission> findHospitalBYpatientNumber(String patientNumber);

	/**
	 * Find P admission B ypatient number.
	 *
	 * @param patientNumber the patient number
	 * @param hospitalId    the hospital id
	 * @return the list
	 */
	List<Admission> findPAdmissionBYpatientNumber(String patientNumber, Long hospitalId);

	/**
	 * Gets the admissions per month.
	 *
	 * @param month the month
	 * @return the admissions per month
	 */
	List<Admission> getAdmissionsPerMonth(int month);

	/**
	 * Admission infos.
	 *
	 * @param hospitalId   the hospital id
	 * @param departmentId the department id
	 * @return the admission
	 */
	Admission AdmissionInfos(Long hospitalId, Long departmentId);

	/**
	 * Find all admission.
	 *
	 * @return the list
	 */
	List<Admission> findAllAdmission();

	/**
	 * Admissions.
	 *
	 * @param hospitalId   the hospital id
	 * @param departmentId the department id
	 * @return the list
	 */
	List<Admission> Admissions(Long hospitalId, Long departmentId);

	/**
	 * Admissions.
	 *
	 * @param hospitalId   the hospital id
	 * @param departmentId the department id
	 * @param status       the status
	 * @return the list
	 */
	List<Admission> midleAdmissions(Long hospitalId, Long department);

	/**
	 * @param patient
	 * @return
	 */
	List<Hospital> findUniqueHospitalByPatient(Patient patient);

}
