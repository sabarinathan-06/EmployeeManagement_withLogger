package com.ideas2it.employee.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ideas2it.department.service.DepartmentService;
import com.ideas2it.department.service.DepartmentServiceImpl;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.service.EmployeeServiceImpl;
import com.ideas2it.model.Department;
import com.ideas2it.model.Employee;
import com.ideas2it.exception.EmployeeException;
import com.ideas2it.util.Validation;

/**
 * This class handles all the operation related to employee based on user request.
 */
public class EmployeeController {
    private final DepartmentService departmentService;
    private final Employee employee;
    private final EmployeeService employeeService;
    private final Validation validator;
    Scanner scanner = new Scanner(System.in);
    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    public EmployeeController() {
        this.departmentService = new DepartmentServiceImpl();
        this.employee = new Employee();
        this.employeeService = new EmployeeServiceImpl();
        this.validator = new Validation();
    }

    /**
     * This method is used to display the choice for operation.
     */
    public void listOperation() {
        boolean continueRunning = true;
        while (continueRunning) {
            try {
                System.out.println("1 - Create Employee");
                System.out.println("2 - List Employee");
                System.out.println("3 - Update Employee");
                System.out.println("4 - Remove Employee");
                System.out.println("5 - List Employees by Department");
                System.out.println("6 - Exit");
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine());
                employeeOperation(choice);
                if (choice == 6) {
                    continueRunning = false;
                }
            } catch (NumberFormatException e) {
                logger.error("Invalid input.Please enter a valid integer choice");
}
        }
    }

    /**
     * This method is used to select the operation based on user request.
     */
    public void employeeOperation(int choice) {
        try {
            switch (choice) {
                case 1:
                    createEmployee();
                    break;

                case 2:
                    displayEmployees();
                    break;

                case 3:
                    while (true) {
                        try {
                            System.out.println("Enter ID of employee to update: ");
                            int updateId = Integer.parseInt(scanner.nextLine());
                            updateEmployee(updateId);
                            logger.info("Employee Updated for thr ID: {}", updateId);
                            break;
                        } catch (NumberFormatException e) {
                            logger.warn("Invalid input.Please enter valid integer choice");
                        }
                    }
                    break;

                case 4:
                    removeEmployee();
                    break;

                case 5:
                    displayEmployeeByDepartment();
                    break;

                case 6:
                    System.out.println("Exiting..!!!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try with valid choice");
            }
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * <p>
     * This method is used to create employee object with validation if the department not
     * equal to empty.
     * </p>
     */
    public void createEmployee() throws EmployeeException {
        int departmentSize = departmentService.getAllDepartments().size();
        if (departmentSize != 0) {
            System.out.println("Enter Name: ");
            String name = scanner.nextLine();
            while (validator.stringValidation(name)) {
                name = validator.nameValidation();
            }
            System.out.println("Enter Place: ");
            String place = scanner.nextLine();
            while (validator.stringValidation(place)) {
                place = validator.placeValidation();
            }
            System.out.println("Enter DateOfBirth (DD/MM/YYYY): ");
            String DOB = scanner.nextLine();
            LocalDate dob = validator.dateOfBirthValidation(DOB);
            System.out.println("Enter Experience: ");
            int experience;
            while (true) {
                try {
                    experience = scanner.nextInt();
                    if (validator.experienceValidation(experience)) {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter "
                            + "a valid integer for Experience: ");
                    scanner.next();
                }
            }
            double salary = employeeService.setSalary(experience);
            System.out.println(salary);
            int employeeId = 0;
            displayDepartments();
            System.out.println("Enter DepartmentID: ");
            int departmentId = scanner.nextInt();
            scanner.nextLine();
            Department department = departmentService.getDepartmentById(departmentId);
            int isPresent = employee.getIsPresent();
            employeeService.createEmployee(new Employee(employeeId, name, place,
                    dob.toString(), experience, salary,
                    department, isPresent));
            logger.info("Employee created with the name {}", name);
        } else {
            logger.warn("No department found!. First create the Department.");
        }
        System.out.println("-------------------------------");
    }

    /**
     * This method is used to update employee details if user ID is presented in the list.
     */
    public void updateEmployee(int updateId) throws EmployeeException {
        Employee empToUpdate = employeeService.getEmployeeById(updateId);
        if (empToUpdate != null) {
            choiceForUpdate();
            updateOperation(updateId);
        } else {
            logger.info("Employee with ID {} not found.", updateId);
        }
        System.out.println("-------------------------------");
    }

    /**
     * This method is used to display the option that want to update
     */
    public void choiceForUpdate() {
        System.out.println("Enter what you want to update:");
        System.out.println("1 - Name");
        System.out.println("2 - Place");
        System.out.println("3 - Date Of Birth");
        System.out.println("4 - Experience");
        System.out.println("5 - Department");
        System.out.print("Enter your choice: ");
    }

    /**
     * This method is used to update and to get which attribute they want to change.
     *
     * @Param integer
     * - that contains user id which want to update
     */
    public void updateOperation(int updateId) throws EmployeeException {
        int innerChoice = scanner.nextInt();
        switch (innerChoice) {
            case 1:
                System.out.println("Enter the name you want to update: ");
                String updatedName = scanner.nextLine();
                scanner.nextLine();
                while (validator.stringValidation(updatedName)) {
                    updatedName = validator.nameValidation();
                }
                employeeService.updateEmployee(updateId, updatedName, null, null, -1, null);
                break;

            case 2:
                System.out.println("Enter the place you want to update: ");
                String updatedPlace = scanner.nextLine();
                while (validator.stringValidation(updatedPlace)) {
                    updatedPlace = validator.placeValidation();
                }
                employeeService.updateEmployee(updateId, null, updatedPlace, null, -1, null);
                break;

            case 3:
                System.out.println("Enter the Date Of Birth you want to update: ");
                String updatedDOB = scanner.nextLine();
                LocalDate updatedDate = validator.dateOfBirthValidation(updatedDOB);
                employeeService.updateEmployee(updateId, null, null,
                        updatedDate.toString(), -1, null);
                break;

            case 4:
                System.out.println("Enter the experience you want to update: ");
                int updatedExperience;
                while (true) {
                    try {
                        updatedExperience = scanner.nextInt();
                        if (validator.experienceValidation(updatedExperience)) {
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a "
                                + "valid integer for Experience: ");
                        scanner.next();
                    }
                }
                employeeService.updateEmployee(updateId, null, null, null,
                        updatedExperience, null);
                break;

            case 5:
                displayDepartments();
                System.out.println("Enter the Department ID you want to update: ");
                int newDepartmentId = scanner.nextInt();
                String newDepartmentName = departmentService.getDepartmentById(newDepartmentId).getDepartmentName();
                employeeService.updateEmployee(updateId, null, null, null,
                        -1, newDepartmentName);
                logger.info("Department updated successfully for the ID {} ", updateId);
                break;

            default:
                System.out.println("Invalid choice. Please try with valid choice");
        }
    }

    /**
     * This method is used to display employees with all the attributes in a table format.
     */
    public void displayEmployees() throws EmployeeException {
        List<Employee> displayList = employeeService.listEmployees();
        if (displayList == null || displayList.isEmpty()) {
            System.out.println("No Employee record found");
        } else {
            System.out.printf("%-5s %-20s %-20s %-15s %-13s %-15s %-15s %-15s %n",
                    "ID", "Name", "Place", "Date of Birth", "Experience",
                    "Salary", "Department", "project");
            System.out.println("--------------------------------------------------------------"
                    + "---------------------------------------------------"
                    + "--------------------");
            for (Employee employee : displayList) {
                if (employee.getIsPresent() == 0) {
                    Department department = employee.getDepartment();
                    System.out.printf("%-5d %-20s %-20s %-15s %-13s %-15.2f %-15s %-15s \n",
                            employee.getId(), employee.getName(), employee.getPlace(),
                            employee.getDob(), employee.getExperience(),
                            employee.getSalary(), department.getDepartmentName(),
                            employee.displayProject());
                    System.out.println("----------------------------------------------------------"
                            + "-----------------------------------------------"
                            + "----------------------------");
                }
            }
        }
    }

    /**
     * This method is used to remove the employee object.
     */
    public void removeEmployee() throws EmployeeException {
        System.out.println("Enter ID of the employee to remove: ");
        int removeId = scanner.nextInt();
        boolean isRemoved = employeeService.removeEmployee(removeId);
        if (isRemoved) {
            logger.info("Employee with ID {} is deleted.", removeId);
        } else {
            logger.info("Employee with ID {} not found to remove employee.", removeId);
        }
        System.out.println("-------------------------------");
    }

    /**
     * This method is used to display all the departments in a table format.
     */
    private void displayDepartments() throws EmployeeException {
        List<Department> availableDepartments = departmentService.getAllDepartments();
        System.out.println("-------------------");
        System.out.printf("| %-5s | %-10s |\n", "Id", "Name");
        for (Department department : availableDepartments) {
            System.out.printf("| %-5s | %-10s |\n", department.getDepartmentId(),
                    department.getDepartmentName());
        }
        System.out.println("-------------------");
    }

    /**
     * This method is used to display the employee using department in a table format.
     */
    public void displayEmployeeByDepartment() throws EmployeeException {
        displayDepartments();
        System.out.println("Enter the department id");
        int id = scanner.nextInt();
        scanner.nextLine();
        List<Employee> employees = employeeService.retrieveEmployeesByDepartment(id);
        if (!employees.isEmpty()) {
            System.out.println("-------------------");
            String format = "| %-5s | %-10s |\n";
            System.out.format(format, "Id", "Name");
            for (Employee employee : employees) {
                System.out.format(format, employee.getId(), employee.getName());
            }
        } else {
            System.out.println("Id Not Found Enter Valid Id");
        }
    }
}