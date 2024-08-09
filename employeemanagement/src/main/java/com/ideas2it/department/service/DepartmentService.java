package com.ideas2it.department.service;

import java.util.List;

import com.ideas2it.exception.EmployeeException;
import com.ideas2it.model.Department;

/**
 * This class handle the all operation related to department based on user request.
 * @author 
 *     - Sabarinathan
 */
public interface DepartmentService {

    /**
     * This method is used to add the department.
     * @param department 
     *     - accept the object contains department details
     */
    void addDepartment(Department department) throws EmployeeException;

    /**
     * This method is used to update the department.
     * @param newName string
     *     - accept the department ID and name that want to update
     */
    void updateDepartment(int id, String newName) throws EmployeeException;

    /**
     * This method is used to get the department using ID.
     * @param id
     *     - accept the integer value
     * @return Department
     *     - returns the object that contains department details
     */
    Department getDepartmentById(int id) throws EmployeeException;

    /**
     * This method is used to get the department using Name.
     * @param name
     *     - accept the string value
     * @return Department
     *     - returns the object that contains department details
     */
    Department getDepartmentByName(String name) throws EmployeeException;

    /**
     * This method is used to get all the department in the list.
     * @return List
     *     - returns the list that contains all the departments
     */
    List<Department> getAllDepartments() throws EmployeeException;

    /**
     * This method is used to remove the department.
     * @param id
     *    - accept the id as integer
     */
    void removeDepartment(int id) throws EmployeeException;
}