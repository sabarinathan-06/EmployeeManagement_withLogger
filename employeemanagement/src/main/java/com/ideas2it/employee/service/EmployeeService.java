package com.ideas2it.employee.service;

import java.util.List;

import com.ideas2it.model.Employee;
import com.ideas2it.exception.EmployeeException;

/**
 * <p>
 * This class is responsible for maintaining Employee details by 
 * doing Employee related operations.
 * </p>
 */
public interface EmployeeService {

    /**
     * This method is used to create the employee.
     * @param employee
     *     - accept the employee reference
     */
    void createEmployee(Employee employee) throws EmployeeException;

    /**
     * This method is used to list the employees which is present.
     * @return List<Employee>
     *     - contains employee reference which is present
     */
    List<Employee> listEmployees() throws EmployeeException;

    /**
     * This method is used to get employee using employeeId.
     * @param id
     *    - accept the employee id
     */
    Employee getEmployeeById(int id) throws EmployeeException;

    /**
     * This method is used to update the employee attributes individually.
     * @param id
     *     - accept the employee id
     * @param name
     *     - accept the employee name
     * @param place
     *     - accept the place where they live
     * @param dob
     *     - accept the dateOfBirth
     * @param experience
     *     - accept the experience
     * @param departmentName
     *     - accept the department object
     */
    void updateEmployee(int id, String name, String place, String dob,
                               int experience, String departmentName) throws EmployeeException;

    /**
     * This method is used to remove the employee.
     * @param id
     *    - accept the string value
     */
    boolean removeEmployee(int id) throws EmployeeException;

    /**
     * This method is used to set the employee's salary.
     * @param experience
     *     - accept the integer value
     * @return double
     *     - it contains salary 
     */
    double setSalary(int experience);

    /**
     * This method is used to get retrieve employees using department.
     * @param id
     *     - accepts the  user id
     * @return List 
     *     - contains list of employee object
     */
    List<Employee> retrieveEmployeesByDepartment(int id) throws EmployeeException;

}