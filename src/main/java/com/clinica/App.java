package com.clinica;

import javax.swing.JFrame;

import com.clinica.Database.DBFactory;
import com.clinica.Repository.Impl.AgendamentoRepository;
import com.clinica.Repository.Impl.ProfissionalRepository;
import com.clinica.Repository.Impl.UserRepository;
import com.clinica.controller.IProfissionalController;
import com.clinica.controller.Impl.ProfissionalController;
import com.clinica.controller.Impl.UserController;
import com.clinica.view.TelaUser;
import com.clinica.view.Telainicial;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository(DBFactory.getSessionFactory());
        AgendamentoRepository agendamentoRepository = new AgendamentoRepository(DBFactory.getSessionFactory());
        ProfissionalRepository profissionalRepository = new ProfissionalRepository(DBFactory.getSessionFactory());

        UserController userController = new UserController(userRepository);
        ProfissionalController profissionalController = new ProfissionalController(profissionalRepository);
        JFrame telainicial = new Telainicial(userController, profissionalController);

        telainicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telainicial.setSize(700, 500);
        telainicial.setVisible(true);

    }
}
