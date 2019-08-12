package rw.ehealth.report;

import java.util.Set;

import rw.ehealth.model.Admission;
import rw.ehealth.model.Consultation;
import rw.ehealth.model.Department;
import rw.ehealth.model.ExamRecord;
import rw.ehealth.model.Patient;
import rw.ehealth.model.Prescription;

public class HospitalReport {
	private Admission admission;
	private Department departments;

	

	public HospitalReport(Admission admission, Department departments, Consultation consultation,
			Prescription prescriptio, ExamRecord examRecords) {
		this.admission = admission;
		this.departments = departments;
		this.consultation = consultation;
		this.prescriptio = prescriptio;
		this.examRecords = examRecords;
	}

	public Department getDepartments() {
		return departments;
	}

	public void setDepartments(Department departments) {
		this.departments = departments;
	}

	private Consultation consultation;

	private Prescription prescriptio;
	
	private ExamRecord examRecords;

	public Admission getAdmission() {
		return admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	public Consultation getConsultation() {
		return consultation;
	}

	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}

	public Prescription getPrescriptio() {
		return prescriptio;
	}

	public void setPrescriptio(Prescription prescriptio) {
		this.prescriptio = prescriptio;
	}

	public ExamRecord getExamRecords() {
		return examRecords;
	}

	public void setExamRecords(ExamRecord examRecords) {
		this.examRecords = examRecords;
	}


}
