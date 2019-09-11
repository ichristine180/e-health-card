
package rw.ehealth.utils;

import java.time.LocalDate;

import org.apache.commons.lang.RandomStringUtils;

import rw.ehealth.model.Patient;

public abstract class IDGenerator {

	/**
	 * Generate patient number.
	 *
	 * @param patient the patient
	 * @return the string
	 */
	public static String generatePatientNumber(Patient patient) {
		LocalDate date = LocalDate.now();
		int year = date.getYear();
		return (year + "-" + RandomStringUtils.randomAlphabetic(5)).toUpperCase();
	}

	public static String generateAccessCode() {
		return RandomStringUtils.randomNumeric(4);
	}

	/**
	 * @param hospitalCode
	 * @return
	 */
	public static String generateTrackingNumber(String hospitalCode) {
		LocalDate date = LocalDate.now();
		int year = date.getYear();
		return (hospitalCode + "-" + RandomStringUtils.randomAlphanumeric(6) + "-" + year).toUpperCase();
	}

	public static void main(String[] args) {
		System.out.println(IDGenerator.generateTrackingNumber("KFH"));
	}

}
