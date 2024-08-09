package com.ideas2it.department.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ideas2it.employee.controller.EmployeeController;
import com.ideas2it.exception.EmployeeException;
import com.ideas2it.helper.HibernateConfiguration;
import com.ideas2it.model.Department;

/**
 * <p>
 * This class is responsible for managing the Department entities in the database.
 * This class provides methods for performing CRUD (Create, Read, Update, Delete)
 * It utilizes JDBC for database connectivity and operations.
 * It holds a singleton pattern for managing the database connection and ensures that it is
 * a single instance of the database connection is used throughout the application.
 * </p>
 * Author:
 * - Sabarinathan
 */
public class DepartmentDAOImpl implements DepartmentDAO {

    private final SessionFactory sessionFactory;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    public DepartmentDAOImpl() {
        this.sessionFactory = HibernateConfiguration.getSessionFactory();
    }

    public void addDepartment(Department department) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(department);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while adding department {}", department.getDepartmentName());
            throw new EmployeeException("Error while adding department " + department.getDepartmentName(), e);
        }
    }

    public Department getDepartmentById(int id) throws EmployeeException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Department.class, id);
        } catch (HibernateException e) {
            logger.error("Error while getting department using ID: {}", id);
            throw new EmployeeException("Error while getting department " + id, e);
        }
    }

    public Department getDepartmentByName(String name) throws EmployeeException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Department WHERE departmentName = :name", Department.class)
                    .setParameter("name", name)
                    .uniqueResult();
        } catch (HibernateException e) {
            logger.error("Error while getting department using Name: {}", name);
            throw new EmployeeException("Error while getting department " + name, e);
        }
    }

    public void updateDepartment(Department department) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(department);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while updating Department for the ID: {}", department.getDepartmentId());
            throw new EmployeeException("Error while updating Department " + department.getDepartmentId(), e);
        }
    }

    public void removeDepartment(int id) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            Department department = session.get(Department.class, id);
            if (department != null) {
                transaction = session.beginTransaction();
                department.setIsPresent(0);
                session.update(department);
            }
            if (transaction != null) {
                transaction.commit();
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while removing Department {}", id);
            throw new EmployeeException("Error while removing Department " + id, e);
        }
    }

    public List<Department> getAllDepartments() throws EmployeeException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Department WHERE isPresent = 1", Department.class).getResultList();
        } catch (HibernateException e) {
            logger.error("Error while retrieving departments");
            throw new EmployeeException("Error while retrieving departments", e);
        }
    }
}