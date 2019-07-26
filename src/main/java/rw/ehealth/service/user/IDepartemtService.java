package rw.ehealth.service.user;

import java.util.List;

import rw.ehealth.model.Departemt;

public interface IDepartemtService {

	List<Departemt>findDepartemt();
	Departemt findPerName(String name);
}
