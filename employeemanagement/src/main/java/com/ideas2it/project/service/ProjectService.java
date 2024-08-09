package com.ideas2it.project.service;

import java.util.List;

import com.ideas2it.exception.EmployeeException;
import com.ideas2it.model.Employee;
import com.ideas2it.model.Project;

/**
 * This class handle the all operation related to project based on user request.
 *
 * @author - Sabarinathan
 */
public interface ProjectService {

    /**
     * This method is used to add the Project.
     *
     * @param project - accept the object contains Project details
     */
    void addProject(Project project) throws EmployeeException;

    /**
     * This method is used to check that project name is present or not in the project.
     *
     * @param projectName - accept the string value
     */
    boolean isValidProjectName(String projectName) throws EmployeeException;

    /**
     * This method is used to update the project.
     *
     * @param id      - accept the new project id
     * @param newName - accept the new project name
     */
    void updateProject(int id, String newName) throws EmployeeException;

    /**
     * This method is used to get the department using ID.
     *
     * @param id - accept the project id integer value
     */
    Project getProjectById(int id) throws EmployeeException;

    /**
     * This method is used to get all the department.
     *
     * @return List
     * - returns the list that contains all the departments
     */
    List<Project> getAllProjects() throws EmployeeException;

    /**
     * This method is used to remove the department.
     */
    void removeProject(int id) throws EmployeeException;

    /**
     * This method is used to add employee to the project.
     *
     * @param employeeId - accepts the employee id
     * @param projectId  - accepts the project id
     */
    void addEmployeeToProject(int employeeId, int projectId) throws EmployeeException;

    /**
     * This method is used to remove employee from the project.
     *
     * @param employeeId - accepts the employee id
     * @param projectId  - accepts the project id
     */
    void removeEmployeeFromProject(int employeeId, int projectId) throws EmployeeException;

    /**
     * This method is used to retrieve employees by project.
     *
     * @param projectId - accepts the project id
     */
    List<Employee> retrieveEmployeesByProject(int projectId) throws EmployeeException;

}