
package rw.ehealth.utils.report;

import rw.ehealth.model.Department;

public class DepartmentReport {

	private Department department;

	private int patientCount;

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @return the patientCount
	 */
	public int getPatientCount() {
		return patientCount;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * @param patientCount the patientCount to set
	 */
	public void setPatientCount(int patientCount) {
		this.patientCount = patientCount;
	}

}
