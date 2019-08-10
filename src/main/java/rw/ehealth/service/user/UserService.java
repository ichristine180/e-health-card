
package rw.ehealth.service.user;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rw.ehealth.model.Employee;
import rw.ehealth.model.User;
import rw.ehealth.model.security.Role;
import rw.ehealth.model.security.UserRole;
import rw.ehealth.repo.medical.EmployeeRepository;
import rw.ehealth.repo.user.RoleDao;
import rw.ehealth.repo.user.UserRepository;

@Service

@Transactional
public class UserService implements IUserService {

	private static final Logger LOG = LoggerFactory.getLogger(IUserService.class);

	@Autowired
	private UserRepository userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private EmployeeRepository doctorrepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User createUser(User user) {
		User localUser = userDao.findByUsername(user.getUsername());
		if (localUser != null) {
			LOG.info("Account with {} username, exists", user.getUsername());
		} else {
			String encryptedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encryptedPassword);
			try {
				localUser = userDao.save(user);
			} catch (Exception e) {
				System.out.println(e.getMessage() + " JSJS");
			}
		}
		return localUser;
	}

	@Override
	public User encryptPass(User password) {
		String encryptedPassword = passwordEncoder.encode(password.getPassword());
		password.setPassword(encryptedPassword);

		return password;
	}

	@Override
	public User createUser(User user, Set<UserRole> userRoles) {
		for (UserRole ur : userRoles) {
			roleDao.save(ur.getRole());
		}
		user.getUserRoles().addAll(userRoles);
		try {

			return userDao.save(user);
		} catch (Exception e) {
			System.out.println(e.getMessage() + " JSJS");
			throw e;
		}
	}

	@Override
	public boolean checkUserExists(String username) {
		if (checkUsernameExists(username)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkUsernameExists(String username) {
		if (null != findByUsername(username)) {
			return true;
		}
		return false;
	}

	@Override
	public User saveUser(User user) {
		return userDao.save(user);
	}

	@Override
	public List<User> findUserList() {
		return userDao.findAll();
	}

	@Override
	public void enableUser(String username) {
		User user = findByUsername(username);
		user.setEnabled(true);
		userDao.save(user);
	}

	@Override
	public void disableUser(String username) {
		User user = findByUsername(username);
		user.setEnabled(false);
		userDao.save(user);
	}

	@Override
	public void updatePassword(String updatedPassword, String username) {
		userDao.updatePassword(updatedPassword, username);
	}

	public Iterable<Role> findAll() {
		try {
			return roleDao.findAll();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public Role findByName(String rolename) {
		try {
			return roleDao.findByName(rolename);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public long countDoctor() {
		try {
			return doctorrepo.count();
		} catch (Exception e) {
			throw e;
		}

	}

	public Employee findUserByUsername(String username) {
		try {
			return doctorrepo.findDoctor(username);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<Employee> finDoctors() {
		try {
			return doctorrepo.finDoctors();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public Employee findDoctor(String username) {
		try {
			return doctorrepo.findDoctor(username);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public Employee findByEmployeeId(Long id) {
		try {
			return doctorrepo.findByEmployeeId(id);
		} catch (Exception e) {
			throw e;
		}

	}
}

