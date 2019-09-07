package rw.ehealth.utils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class ConsultationDto {
	@NotBlank(message = "please!! fill this feild is required")
	@NotNull(message = "Please fill in some text in this text field!")
	@Size(min = 50, max = 400, message = "this field should be  between 50 and 400 charcter!")
	private String description;

	private String patientTrackingNumber;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPatientTrackingNumber() {
		return patientTrackingNumber;
	}

	public void setPatientTrackingNumber(String patientTrackingNumber) {
		this.patientTrackingNumber = patientTrackingNumber;
	}

	/*
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConsultationDto [description=" + description + ", patientTrackingNumber=" + patientTrackingNumber + "]";
	}

}
