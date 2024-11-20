package com.clinica.Repository.Impl;

import com.clinica.Repository.RepositoryException;
import com.clinica.models.Agendamento;
import com.clinica.models.Profissional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(profissional);
            return profissional;
        }catch (HibernateException e){
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Profissional edit(Profissional profissional) throws RepositoryException {
        try {
            Session session =  sessionFactory.getCurrentSession();
            Profissional actualProfissional = session.get(Profissional.class, profissional.getId());;

            profissional.setId(actualProfissional.getId());
            session.save(profissional);

            return profissional;
        }catch (HibernateException e){
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Profissional get(Long id) throws RepositoryException {
        try {
            Session session =  sessionFactory.getCurrentSession();
            return session.get(Profissional.class,id);
        }catch (HibernateException e){
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws RepositoryException {
        try {
            Session session =  sessionFactory.getCurrentSession();
            Profissional profissional=  session.get(Profissional.class,id);
            session.delete(profissional);
        }catch (HibernateException e){
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<Profissional> list() throws RepositoryException {
        try {
            Session session = sessionFactory.getCurrentSession();
            List<Profissional> profissionals;
            String hql = "FROM Profissional"; // HQL para selecionar todos os usuários
            Query<Profissional> query = session.createQuery(hql, Profissional.class);
            profissionals = query.getResultList();
            return profissionals;
        } catch (HibernateException e){
            throw new RepositoryException(e.getMessage());
        }
    }
}
