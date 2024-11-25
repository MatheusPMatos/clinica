package com.clinica.Repository.Impl;

import com.clinica.Repository.RepositoryException;
import com.clinica.models.Profissional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.clinica.Repository.IProfissionalRepository;
import org.hibernate.query.Query;

import java.util.List;

public class ProfissionalRepository implements IProfissionalRepository {
    private final SessionFactory sessionFactory;

    public ProfissionalRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Profissional create(Profissional profissional) throws RepositoryException {
        Transaction transaction = null;
        try {
            System.out.println("CATEGORIA DESSA PORRA: "+profissional.getCategoria());
            Session session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            session.save(profissional);
            transaction.commit();
            return profissional;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                
            }
            throw new RepositoryException(e.getMessage());
       }
    }

    @Override
    public Profissional edit(Profissional profissional) throws RepositoryException {
        Transaction transaction = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            session.update(profissional);
            transaction.commit();

            return profissional;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                
            }
            throw new RepositoryException(e.getMessage());
       }
    }

    @Override
    public Profissional get(Long id) throws RepositoryException {
        Transaction transaction = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            Profissional prof = session.get(Profissional.class, id);
            transaction.commit();
            return prof;
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                
            }
            throw new RepositoryException(e.getMessage());
       }
    }

    @Override
    public void delete(Long id) throws RepositoryException {
        Transaction transaction = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            Profissional profissional = session.get(Profissional.class, id);
            session.delete(profissional);
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                
            }
            throw new RepositoryException(e.getMessage());
       }
    }

    @Override
    public List<Profissional> list() throws RepositoryException {
        Transaction transaction = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            List<Profissional> profissionals;
            transaction = session.beginTransaction();
            String hql = "FROM Profissional"; // HQL para selecionar todos os usuários
            Query<Profissional> query = session.createQuery(hql, Profissional.class);
            profissionals = query.getResultList();
            transaction.commit();
            return profissionals;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                
            }
            throw new RepositoryException(e.getMessage());
       }
    }
}
