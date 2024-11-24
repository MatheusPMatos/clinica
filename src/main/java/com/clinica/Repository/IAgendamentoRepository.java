package com.clinica.Repository;

import java.util.List;
import com.clinica.models.Agendamento;

public interface IAgendamentoRepository {
     Agendamento create(Agendamento agendamento)throws RepositoryException;
     Agendamento edit(Agendamento agendamento)throws RepositoryException;
     Agendamento get(Long id)throws RepositoryException;
    void delete(Long id)throws RepositoryException;
    List<Agendamento> list()throws RepositoryException;
}
