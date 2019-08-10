package rw.ehealth.service.user;

import java.util.List;

import rw.ehealth.model.Department;

public interface IDepartemtService {

	List<Department> findAllDepartemts();

	Department findPerName(String name);
}
