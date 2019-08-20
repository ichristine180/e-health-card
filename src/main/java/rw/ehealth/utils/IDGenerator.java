
package rw.ehealth.utils;

import org.apache.commons.lang.RandomStringUtils;

import rw.ehealth.model.Patient;

public abstract class IDGenerator {

	/**
	 * Generate tracking number.
	 *
	 * @return the string
	 */
	public static String generateTrackingNumber() {
		return "TRACK-" + RandomStringUtils.randomNumeric(6).toUpperCase();
	}

	/**
	 * Generate patient number.
	 *
	 * @param patient the patient
	 * @return the string
	 */
	public static String generatePatientNumber(Patient patient) {
		return "PN-" + RandomStringUtils.randomAlphanumeric(8).toUpperCase();
	}
	
	public static String generateAccessCode() {
		return "ACCESS-" + RandomStringUtils.randomNumeric(4);
	}

}
