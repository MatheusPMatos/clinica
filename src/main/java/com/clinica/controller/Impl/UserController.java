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
    public User get(Long id) {

        try {
            return userRepository.get(id);
        } catch (RepositoryException e) {
            return new ControllerException(e.getMessage());
        }
    }

    @Override
    public List<User> list() {
        try {
            return userRepository.list(id);
        } catch (RepositoryException e) {
            return new ControllerException(e.getMessage());
        }
    }

    @Override
    public User create(User user) {
        try {
            return userRepository.create(user);
        } catch (RepositoryException e) {
            return new ControllerException(e.getMessage());
        }
    }

    @Override
    public User edit(User user) {
        try {
            return userRepository.edit(user);
        } catch (RepositoryException e) {
            return new ControllerException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            return userRepository.delete(id);
            ;
        } catch (RepositoryException e) {
            return new ControllerException(e.getMessage());
        }
    }

}
