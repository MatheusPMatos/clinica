package com.clinica.controller;

import com.clinica.models.User;
import java.util.List;

public interface IUserController {

    User create(User user) throws ControllerException;

    User edit(User user) throws ControllerException;

    void delete(Long id) throws ControllerException;

    User get(Long id) throws ControllerException;

    List<User> list() throws ControllerException;
}
