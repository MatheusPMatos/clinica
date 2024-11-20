package com.clinica.Repository;

import java.util.List;

import com.clinica.models.User;

public interface IUserRepository {
     User create(User user)throws RepositoryException;
     User edit(User user)throws RepositoryException;
     void delete(Long id)throws RepositoryException;
     User get(Long id)throws RepositoryException;
     List<User> list()throws RepositoryException;
}
