
package rw.ehealth.utils;

import java.time.LocalDate;

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
		LocalDate date = LocalDate.now();
		int year = date.getYear();
		return "PN-" + year+"-"+RandomStringUtils.randomAlphabetic(3).toUpperCase();
	}
	
	public static String generateAccessCode() {
		return "ACCESS-" + RandomStringUtils.randomNumeric(4);
	}

}
