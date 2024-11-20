package com.clinica.controller.Impl;

import com.clinica.Repository.IUserRepository;
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
        return null;
    }

    @Override
    public List<User> list() {
        return null;
    }
}
