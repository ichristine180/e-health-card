package rw.ehealth.repo.user;

import org.springframework.data.repository.CrudRepository;

import rw.ehealth.model.security.Role;





public interface RoleDao extends CrudRepository<Role, Integer> {
	Role findByName(String name);
	
}
