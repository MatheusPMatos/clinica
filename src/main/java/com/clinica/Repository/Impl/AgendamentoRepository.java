package com.clinica.Repository.Impl;

import com.clinica.Repository.RepositoryException;
import com.clinica.models.Agendamento;
import org.hibernate.HibernateException;
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

        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.save(agendamento);
            transaction.commit();
            return agendamento;
        } catch (HibernateException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Agendamento edit(Agendamento agendamento) throws RepositoryException {
        try {
            Session session = sessionFactory.getCurrentSession();
            Agendamento actualAgendamento = session.get(Agendamento.class, agendamento.getId());
            ;

            agendamento.setId(actualAgendamento.getId());
            Transaction transaction = session.beginTransaction();
            session.save(agendamento);
            transaction.commit();

            return agendamento;
        } catch (HibernateException e) {
            throw new RepositoryException(e.getMessage());
        }

    }

    @Override
    public Agendamento get(Long id) throws RepositoryException {

        try {
            Session session = sessionFactory.getCurrentSession();
            return session.get(Agendamento.class, id);
        } catch (HibernateException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws RepositoryException {
        try {
            Session session = sessionFactory.getCurrentSession();
            Agendamento agendamento = session.get(Agendamento.class, id);

            Transaction transaction = session.beginTransaction();
            session.delete(agendamento);
            transaction.commit();
        } catch (HibernateException e) {
            throw new RepositoryException(e.getMessage());
        }

    }

    @Override
    public List<Agendamento> list() throws RepositoryException {

        try {
            Session session = sessionFactory.getCurrentSession();
            List<Agendamento> agendamentos;
            String hql = "FROM Agendamento"; // HQL para selecionar todos os usuários
            Query<Agendamento> query = session.createQuery(hql, Agendamento.class);
            agendamentos = query.getResultList();
            return agendamentos;
        } catch (HibernateException e) {
            throw new RepositoryException(e.getMessage());
        }
    }
}
