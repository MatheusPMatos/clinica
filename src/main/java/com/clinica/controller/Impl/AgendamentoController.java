package com.clinica.controller.Impl;

import java.util.List;

import com.clinica.Repository.Impl.AgendamentoRepository;
import com.clinica.controller.ControllerException;
import com.clinica.controller.IAgendamentoController;
import com.clinica.models.Agendamento;

public class AgendamentoController implements IAgendamentoController {
    private AgendamentoRepository agendamentoRepository;

    public AgendamentoController(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    @Override
    public Agendamento create(Agendamento agendamento) throws ControllerException {
        try {
            return agendamentoRepository.create(agendamento);
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public Agendamento edit(Agendamento agendamento) throws ControllerException {
        try {
            return agendamentoRepository.edit(agendamento);
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public Agendamento get(Long id) throws ControllerException {
        try {
            return agendamentoRepository.get(id);
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws ControllerException {
        try {
            agendamentoRepository.delete(id);
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public List<Agendamento> list() throws ControllerException {
        try {
            return agendamentoRepository.list();
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }

}
