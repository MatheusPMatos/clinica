package com.clinica.Repository;

import java.util.List;

import com.clinica.models.Profissional;

public interface IProfissionalRepository {
    
    Profissional create(Profissional profissional)throws RepositoryException;
    Profissional edit(Profissional profissional)throws RepositoryException;
    Profissional get(Long id)throws RepositoryException;
    void delete(Long id)throws RepositoryException;
    List<Profissional> list()throws RepositoryException;
}
