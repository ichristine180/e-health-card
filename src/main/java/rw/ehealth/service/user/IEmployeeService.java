
package rw.ehealth.service.user;

import java.util.List;

import rw.ehealth.model.Department;
import rw.ehealth.model.Employee;

public interface IEmployeeService {

	/**
	 * Find by employee id.
	 *
	 * @param id the id
	 * @return the employee
	 */
	Employee findByEmployeeId(long id);

	/**
	 * Find all employees.
	 *
	 * @return the list
	 */
	List<Employee> findAllEmployees();

	/**
	 * Creates the employee.
	 *
	 * @param employee the employee
	 * @return the employee
	 */
	Employee createEmployee(Employee employee);

	/**
	 * Adds the employee department.
	 *
	 * @param employee   the employee
	 * @param department the department
	 * @return the employee
	 */
	Employee addEmployeeDepartment(Employee employee, Department department);

}
