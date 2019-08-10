
package rw.ehealth.service.user;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ehealth.model.Department;
import rw.ehealth.model.Employee;
import rw.ehealth.model.security.UserRole;
import rw.ehealth.repo.medical.EmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private EmployeeRepository empRepository;

	/*
	 *
	 * @see rw.ehealth.service.user.IEmployeeService#findByEmployeeId(long)
	 */
	@Override
	public Employee findByEmployeeId(long id) {
		return empRepository.findOne(id);
	}

	/*
	 *
	 * @see rw.ehealth.service.user.IEmployeeService#findAllEmployees()
	 */
	@Override
	public List<Employee> findAllEmployees() {
		return empRepository.findAll();
	}

	/*
	 *
	 * @see rw.ehealth.service.user.IEmployeeService#createEmployee(rw.ehealth.model.Employee)
	 */
	@Override
	public Employee createEmployee(Employee employee) {
		return empRepository.save(employee);
	}

	/*
	 *
	 * @see rw.ehealth.service.user.IEmployeeService#addEmployeeDepartment(rw.ehealth.model.Employee,
	 * rw.ehealth.model.Department)
	 */
	@Override
	public Employee addEmployeeDepartment(Employee employee, Department department) {
		Employee updateEmployee = new Employee();
		if (empRepository.findOne(employee.getEmployeeId()).getDepertment() != null) {
			return updateEmployee;
		}
		Set<UserRole> roles = employee.getUser().getUserRoles();
		UserRole role = roles.iterator().next();
		//if (role.getRole().getName().contains(department.getName())) {
			employee.setDepertment(department);
			updateEmployee = empRepository.save(employee);
		//}
		return updateEmployee;
	}
}
