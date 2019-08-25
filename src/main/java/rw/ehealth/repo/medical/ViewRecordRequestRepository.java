
package rw.ehealth.repo.medical;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rw.ehealth.model.ViewRecordRequest;

@Repository
public interface ViewRecordRequestRepository extends JpaRepository<ViewRecordRequest, Long> {

	/**
	 * Find view record request bypatient.
	 *
	 * @param patientNumber the patient number
	 * @param requestDate   the request date
	 * @return the view record request
	 */
	/*
	@Query("SELECT r FROM ViewRecordRequest r JOIN r.patient p WHERE p.patientNumber=:patientNumber and r.requestStatus is 'APPROVED' and r.requestDate=:requestDate")
	ViewRecordRequest findViewRecordRequestBypatient(@Param("patientNumber") String patientNumber,
			@Param("requestDate") String requestDate);
*/
	/**
	 * Find view record request.
	 *
	 * @param patientNumber the patient number
	 * @param requestDate   the request date
	 * @return the view record request
	 */
	@Query("SELECT r FROM ViewRecordRequest r JOIN r.admission a WHERE a.patientTrackingNumber=:patientTrackingNumber and r.isActive is true")
	ViewRecordRequest findViewRecordRequest(@Param("patientTrackingNumber") String patientTrackingNumber);
	@Query("SELECT r FROM ViewRecordRequest r JOIN r.admission a WHERE a.patientTrackingNumber=:patientTrackingNumber")
	ViewRecordRequest findViewRecordBystatus(@Param("patientTrackingNumber") String patientTrackingNumber);
	@Query("SELECT r FROM ViewRecordRequest r WHERE r.accessCode=:accessCode")
	ViewRecordRequest findViewRecordRequestByAccessCode(@Param("accessCode") String accessCode);

	/**
	 * @param pnumber
	 * @return
	 */
	/*@Query("SELECT a from ViewRecordRequest a where a.patient.patientNumber = :pNumber")
	List<ViewRecordRequest> viewHistory(@RequestParam("pNumber") String pNumber);
	*/

}
