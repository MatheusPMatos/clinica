package com.clinica.controller.Impl;

import com.clinica.Repository.Impl.ProfissionalRepository;
import com.clinica.controller.IProfissionalController;

public class ProfissionalController {
    private ProfissionalRepository profissionalRepository;

    public ProfissionalController(ProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }

}
