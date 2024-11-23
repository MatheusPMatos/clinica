package com.clinica.controller.Impl;

import com.clinica.Repository.Impl.AgendamentoRepository;
import com.clinica.controller.IAgendamentoController;

public class AgendamentoController {
    private AgendamentoRepository agendamentoRepository;

    public AgendamentoController(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

}
