package rw.ehealth.utils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class PrescriptionsDto {
	@NotBlank(message = "please!! fill this feild is required")
	@NotNull(message = "Please fill in some text in this text field!")
	@Size(min = 5, max = 100, message = "this field should be  between 5 and 100 charcter!")
	private String name;
	private String patientTrackingNumber;
	public String getPatientTrackingNumber() {
		return patientTrackingNumber;
	}
	public void setPatientTrackingNumber(String patientTrackingNumber) {
		this.patientTrackingNumber = patientTrackingNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@NotBlank(message = "please!! fill this feild is required")
	@NotNull(message = "Please fill in some text in this text field!")
	@Size(min = 5, max = 200, message = "this field should be  between 50 and 200 charcter!")
	private String description;

}
