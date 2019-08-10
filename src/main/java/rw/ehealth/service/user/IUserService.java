package rw.ehealth.service.user;


import java.util.List;


import java.util.Set;

import rw.ehealth.model.Employee;
import rw.ehealth.model.User;
import rw.ehealth.model.security.Role;
import rw.ehealth.model.security.UserRole;
public interface IUserService {
	User findByUsername(String username);



	boolean checkUserExists(String username);

	boolean checkUsernameExists(String username);

	void save(User user);

	User createUser(User user, Set<UserRole> userRoles);

	User saveUser(User user);

	List<User> findUserList();

	void enableUser(String username);

	void disableUser(String username);

	void updatePassword(String updatedPassword, String username);



	User encryptPass(User password);

    Role findByName(String rolename);

	User createUser(User user);
	long countDoctor();
	List<Employee>finDoctors();
	Employee findDoctor(String username);
	Employee findByEmployeeId(Long id);



}
