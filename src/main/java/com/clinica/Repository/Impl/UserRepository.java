package com.clinica.Repository.Impl;

import com.clinica.Repository.RepositoryException;
import com.clinica.models.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.clinica.Repository.IUserRepository;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepository implements IUserRepository {

     private final SessionFactory sessionFactory;

     public UserRepository(SessionFactory sessionFactory) {
          this.sessionFactory = sessionFactory;
     }

     @Override
     public User create(User user) throws RepositoryException {
          try {
               Session session = sessionFactory.getCurrentSession();
               Transaction transaction = session.beginTransaction();
               session.save(user);
               transaction.commit();
               return user;
          } catch (HibernateException e) {
               throw new RepositoryException(e.getMessage());
          }
     }

     @Override
     public User edit(User user) throws RepositoryException {
          try {
               Session session = sessionFactory.getCurrentSession();
               User actualUser = session.get(User.class, user.getId());

               Transaction transaction = session.beginTransaction();
               user.setId(actualUser.getId());
               session.save(user);
               transaction.commit();
               return user;
          } catch (HibernateException e) {
               throw new RepositoryException(e.getMessage());
          }
     }

     @Override
     public void delete(Long id) throws RepositoryException {
          try {
               Session session = sessionFactory.getCurrentSession();
               User user = session.get(User.class, id);

               Transaction transaction = session.beginTransaction();
               session.delete(user);
               transaction.commit();
          } catch (HibernateException e) {
               throw new RepositoryException(e.getMessage());
          }
     }

     @Override
     public User get(Long id) throws RepositoryException {
          try {
               Session session = sessionFactory.getCurrentSession();
               return session.get(User.class, id);
          } catch (HibernateException e) {
               throw new RepositoryException(e.getMessage());
          }
     }

     @Override
     public List<User> list() throws RepositoryException {
          try {
               List<User> users;

               Session session = sessionFactory.getCurrentSession();
               Transaction transaction = session.beginTransaction();               
               
               String hql = "FROM User"; // HQL para selecionar todos os usuários
               Query<User> query = session.createQuery(hql, User.class);
               users = query.getResultList();
               transaction.commit();
               return users;
          } catch (HibernateException e) {
               throw new RepositoryException(e.getMessage());
          }
     }
}
