package rw.ehealth.repo.user;
import java.util.List;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import rw.ehealth.model.User;


public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);


	@Override
	List<User> findAll();

	@Modifying
	@Query("update User u set u.password = :password where u.username = :username")
	void updatePassword(@Param("password") String password, @Param("username") String username);
}
