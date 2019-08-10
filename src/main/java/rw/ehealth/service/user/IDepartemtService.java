package rw.ehealth.service.user;

import java.util.List;

import rw.ehealth.model.Department;

public interface IDepartemtService {

	List<Department>findDepartemt();
	Department findPerName(String name);
}
