package com.clinica.Repository.Impl;

import com.clinica.Repository.RepositoryException;
import com.clinica.models.Agendamento;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.clinica.Repository.IAgendamentoRepository;
import org.hibernate.query.Query;

import java.util.List;

public class AgendamentoRepository implements IAgendamentoRepository {
    private final SessionFactory sessionFactory;

    public AgendamentoRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Agendamento create(Agendamento agendamento) throws RepositoryException {
        Transaction transaction = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            session.save(agendamento);
            transaction.commit();
            return agendamento;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                
            }
            throw new RepositoryException(e.getMessage());
       }
    }

    @Override
    public Agendamento edit(Agendamento agendamento) throws RepositoryException {
        Transaction transaction = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            session.update(agendamento);
            transaction.commit();

            return agendamento;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                
            }
            throw new RepositoryException(e.getMessage());
       }

    }

    @Override
    public Agendamento get(Long id) throws RepositoryException {
        Transaction transaction = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            Agendamento agenda = session.get(Agendamento.class, id);
            transaction.commit();
            return agenda;
        } catch (Exception e) {
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
            Agendamento agendamento = session.get(Agendamento.class, id);
            session.delete(agendamento);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                
            }
            throw new RepositoryException(e.getMessage());
       }

    }

    @Override
    public List<Agendamento> list() throws RepositoryException {
        Transaction transaction = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            List<Agendamento> agendamentos;
            transaction = session.beginTransaction();
            String hql = "FROM Agendamento"; // HQL para selecionar todos os usuários
            Query<Agendamento> query = session.createQuery(hql, Agendamento.class);
            agendamentos = query.getResultList();
            transaction.commit();
            return agendamentos;
        } catch (Exception e) {
            if (transaction != null) {
                 transaction.rollback();
                
            }
            throw new RepositoryException(e.getMessage());
       }
    }
}
