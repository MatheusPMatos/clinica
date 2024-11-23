package com.clinica.controller;

import java.util.List;

import com.clinica.models.Agendamento;

public interface IAgendamentoController {
    Agendamento create(Agendamento agendamento) throws ControllerException;

    Agendamento edit(Agendamento agendamento) throws ControllerException;

    Agendamento get(Long id) throws ControllerException;

    void delete(Long id) throws ControllerException;

    List<Agendamento> list() throws ControllerException;
}
