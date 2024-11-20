package com.clinica.Database;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBFactory {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Configura o SessionFactory usando o arquivo hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("Falha ao criar o SessionFactory: " + ex.getMessage());
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
    
}



