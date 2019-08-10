
package rw.ehealth.enums;

public enum ERecordType {

	GENERAL_INFO("General Information"), EXAMRECORDS("ExamRecords"), PRESCRIPTIONS("PRESCRIPTIONS");

	private String description;

	/**
	 * Instantiates a new e record type.
	 *
	 * @param descripition the descripition
	 */
	private ERecordType(String descripition) {
		this.description = descripition;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

}
