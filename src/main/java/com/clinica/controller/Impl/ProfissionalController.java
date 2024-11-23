package com.clinica.controller.Impl;

import java.util.List;

import com.clinica.Repository.Impl.ProfissionalRepository;
import com.clinica.controller.ControllerException;
import com.clinica.controller.IProfissionalController;
import com.clinica.models.Profissional;

public class ProfissionalController implements IProfissionalController {
    private ProfissionalRepository profissionalRepository;

    public ProfissionalController(ProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }

    @Override
    public Profissional create(Profissional profissional) throws ControllerException {
        try {
            return profissionalRepository.create(profissional);
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public Profissional edit(Profissional profissional) throws ControllerException {
        try {
            return profissionalRepository.edit(profissional);
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public Profissional get(Long id) throws ControllerException {
        try {
            return profissionalRepository.get(id);
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws ControllerException {
        try {
            profissionalRepository.delete(id);
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public List<Profissional> list() throws ControllerException {
        try {
            return profissionalRepository.list();
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }
    }

}
