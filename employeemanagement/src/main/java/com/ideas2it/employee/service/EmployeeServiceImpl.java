package com.ideas2it.employee.service;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.department.service.DepartmentService;
import com.ideas2it.department.service.DepartmentServiceImpl;
import com.ideas2it.model.Department;
import com.ideas2it.model.Employee;
import com.ideas2it.employee.dao.EmployeeDAO;
import com.ideas2it.employee.dao.EmployeeDAOImpl;
import com.ideas2it.exception.EmployeeException;

/**
 * <p>
 * This class is responsible for maintaining Employee details by
 * doing Employee related operations.
 * </p>
 * Author:
 * - Sabarinathan
 */
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    private final DepartmentService departmentService = new DepartmentServiceImpl();

    public void createEmployee(Employee employee) throws EmployeeException {
        employeeDAO.addOrUpdateEmployee(employee);

    }

    public List<Employee> listEmployees() throws EmployeeException {
        List<Employee> activeEmployees = new ArrayList<>();
        List<Employee> allEmployees = employeeDAO.getAllEmployees();
        for (Employee emp : allEmployees) {
            if (emp.getIsPresent() == 0) {
                activeEmployees.add(emp);
            }
        }
        return activeEmployees;
    }

    public Employee getEmployeeById(int id) throws EmployeeException {
        return employeeDAO.getEmployeeById(id);
    }

    public void updateEmployee(int id, String name, String place, String dob,
                               int experience, String departmentName) throws EmployeeException {
        Employee employee = employeeDAO.getEmployeeById(id);
        if (employee != null) {
            if (name != null && !name.isEmpty()) {
                employee.setName(name);
            }
            if (place != null && !place.isEmpty()) {
                employee.setPlace(place);
            }
            if (dob != null && !dob.isEmpty()) {
                employee.setDob(dob);
            }
            if (experience >= 0) {
                employee.setExperience(experience);
                employee.setSalary(setSalary(experience));
            }
            if (departmentName != null && !departmentName.isEmpty()) {
                Department department = departmentService.getDepartmentByName(departmentName);
                if (department != null) {
                    employee.setDepartment(department);
                }
            }
            employeeDAO.addOrUpdateEmployee(employee);
        }
    }

    public boolean removeEmployee(int id) throws EmployeeException {
        return employeeDAO.removeEmployee(id);
    }

    public List<Employee> retrieveEmployeesByDepartment(int id) throws EmployeeException {
        return employeeDAO.retrieveEmployeesByDepartment(id);
    }

    public double setSalary(int experience) {
        double salary;
        if (experience >= 0 && experience <= 3) salary = 520000;
        else if (experience > 3 && experience <= 5) {
            salary = 800000;
        } else if (experience > 5 && experience <= 8) {
            salary = 1200000;
        } else if (experience > 8 && experience <= 10) {
            salary = 1800000;
        } else {
            salary = 2500000;
        }
        return salary;
    }
}