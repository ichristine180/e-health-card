package rw.ehealth.utils;

public class PrescriptionsDto {
	private String name;
	private Double quantity;
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
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private String description;

}
