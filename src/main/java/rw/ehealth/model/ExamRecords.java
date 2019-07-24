package rw.ehealth.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "examRecords")
public class ExamRecords {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "examrecordid", nullable = false, updatable = false)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "examid")
	private Exams exams;
	public Exams getExams() {
		return exams;
	}
	public void setExams(Exams exams) {
		this.exams = exams;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "admissionid")
	private AdmissionInfo admissionInfo;
	private String datetaken;
	private String results;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public AdmissionInfo getAdmissionInfo() {
		return admissionInfo;
	}
	public void setAdmissionInfo(AdmissionInfo admissionInfo) {
		this.admissionInfo = admissionInfo;
	}
	public String getDatetaken() {
		return datetaken;
	}
	public void setDatetaken(String datetaken) {
		this.datetaken = datetaken;
	}
	public String getResults() {
		return results;
	}
	public void setResults(String results) {
		this.results = results;
	}
}
