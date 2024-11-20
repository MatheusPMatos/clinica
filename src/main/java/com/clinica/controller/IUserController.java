package com.clinica.controller;

import com.clinica.models.User;
import  java.util.List;

public interface IUserController {
    
    User get(Long id);
    List<User> list();
}
