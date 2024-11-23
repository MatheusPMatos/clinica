package com.clinica.controller.Impl;

import com.clinica.Repository.IUserRepository;
import com.clinica.Repository.RepositoryException;
import com.clinica.controller.ControllerException;
import com.clinica.controller.IUserController;
import com.clinica.models.User;

import java.util.List;

public class UserController implements IUserController {
    private final IUserRepository userRepository;

    public UserController(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User get(Long id) throws ControllerException {

        try {
            return userRepository.get(id);
        } catch (RepositoryException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public List<User> list() throws ControllerException {
        try {
            return userRepository.list();
        } catch (RepositoryException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public User create(User user) throws ControllerException {
        try {
            return userRepository.create(user);
        } catch (RepositoryException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public User edit(User user) throws ControllerException {
        try {
            return userRepository.edit(user);
        } catch (RepositoryException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws ControllerException {
        try {
            userRepository.delete(id);
        } catch (RepositoryException e) {
            throw new ControllerException(e.getMessage());
        }
    }
}
