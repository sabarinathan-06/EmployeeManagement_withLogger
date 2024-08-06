package com.ideas2it.helper;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
* This class is responsible for creating a session factory and creating a single instance for it.
*
* Author: Sabarinathan
*/
public class HibernateConfiguration {
    private static SessionFactory sessionFactory = null;

    /**
     * This method checks if the instance is null or the connection is closed,
then it creates a new instance; otherwise, it returns the existing instance.
     *
     * @return instance 
     *     - If it's null or connection is closed, returns new instance 
else returns existing instance.
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (HibernateException e) {
                System.out.println("Initial SessionFactory creation is failed." + e.getMessage());
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}