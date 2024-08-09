package com.ideas2it.project.service;

import java.util.List;

import com.ideas2it.exception.EmployeeException;
import com.ideas2it.model.Employee;
import com.ideas2it.model.Project;
import com.ideas2it.project.dao.ProjectDAO;
import com.ideas2it.project.dao.ProjectDAOImpl;

/**
 * This class handle the all operation related to project based on user request.
 *
 * @author - Sabarinathan
 */
public class ProjectServiceImpl implements ProjectService {
    private final ProjectDAO projectDAO = new ProjectDAOImpl();

    public void addProject(Project project) throws EmployeeException {
        projectDAO.addProject(project);
    }

    public boolean isValidProjectName(String projectName) throws EmployeeException {
        return projectDAO.isValidProjectName(projectName);
    }

    public void updateProject(int id, String newName) throws EmployeeException {
        Project project = projectDAO.getProjectById(id);
        if (project != null) {
            project.setProjectName(newName);
            projectDAO.updateProject(id, project);
        }
    }

    public Project getProjectById(int id) throws EmployeeException {
        return projectDAO.getProjectById(id);
    }

    public List<Project> getAllProjects() throws EmployeeException {
        return projectDAO.getAllProjects();
    }

    public void removeProject(int id) throws EmployeeException {
        projectDAO.removeProject(id);
    }

    public void addEmployeeToProject(int employeeId, int projectId) throws EmployeeException {
        projectDAO.addEmployeeToProject(employeeId, projectId);
    }

    public void removeEmployeeFromProject(int employeeId, int projectId) throws EmployeeException {
        projectDAO.removeEmployeeFromProject(employeeId, projectId);
    }

    public List<Employee> retrieveEmployeesByProject(int projectId) throws EmployeeException {
        return projectDAO.retrieveEmployeesByProject(projectId);
    }
}