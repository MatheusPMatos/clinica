package com.clinica.controller;

import java.util.List;
import com.clinica.models.Profissional;

public interface IProfissionalController {
    Profissional create(Profissional profissional) throws ControllerException;

    Profissional edit(Profissional profissional) throws ControllerException;

    Profissional get(Long id) throws ControllerException;

    void delete(Long id) throws ControllerException;

    List<Profissional> list() throws ControllerException;
}
