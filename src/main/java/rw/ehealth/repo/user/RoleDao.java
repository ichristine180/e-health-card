package rw.ehealth.repo.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import rw.ehealth.model.security.Role;





public interface RoleDao extends CrudRepository<Role, Integer> {
	Role findByName(String name);
	@Query("SELECT r from Role r where r.name != 'ROLE_ADMIN'")
	List<Role> findAll();
	
}
