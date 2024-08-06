package com.ideas2it.department.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.department.dao.DepartmentDAO;
import com.ideas2it.department.dao.DepartmentDAOImpl;
import com.ideas2it.department.service.DepartmentManagement;
import com.ideas2it.exception.EmployeeException;
import com.ideas2it.model.Department;
import com.ideas2it.model.Employee;

/**
 * This class handle the all operation related to department based on user request.
 * @author 
 *     - Sabarinathan
 */
public class DepartmentServiceImpl implements DepartmentManagement {
    private DepartmentDAO departmentDAO = new DepartmentDAOImpl();

    public void addDepartment(Department department) throws EmployeeException {
        departmentDAO.addDepartment(department);
    }

    public void updateDepartment(int id, String newName) throws EmployeeException {
        Department department = departmentDAO.getDepartmentById(id);
        if (department != null) {
            department.setDepartmentName(newName);
            departmentDAO.updateDepartment(department);
        }
    }

    public Department getDepartmentById(int id) throws EmployeeException {
        return departmentDAO.getDepartmentById(id);
    }

    public Department getDepartmentByName(String name) throws EmployeeException {
        return departmentDAO.getDepartmentByName(name);
    }

    public List<Department> getAllDepartments() throws EmployeeException {
        return departmentDAO.getAllDepartments();
    }

    public int getDepartmentSize() throws EmployeeException {
        return departmentDAO.getDepartmentSize();
    }

    public List<Department> getDepartments() throws EmployeeException {
        List<Department> activeDepartment = new ArrayList<>();
        List<Department> allDepartment = departmentDAO.getAllDepartments();
        for (Department emp : allDepartment) {
            if (emp.getIsPresent() == 1) {
                activeDepartment.add(emp);
            }
        }
        return activeDepartment;
    }

    public void removeDepartment(int id) throws EmployeeException {
        departmentDAO.removeDepartment(id);
    }
}