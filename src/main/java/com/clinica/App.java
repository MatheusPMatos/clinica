package com.clinica;

import javax.swing.JFrame;

import com.clinica.Database.DBFactory;
import com.clinica.Repository.Impl.AgendamentoRepository;
import com.clinica.Repository.Impl.ProfissionalRepository;
import com.clinica.Repository.Impl.UserRepository;
import com.clinica.controller.Impl.AgendamentoController;
import com.clinica.controller.Impl.ProfissionalController;
import com.clinica.controller.Impl.UserController;
import com.clinica.view.Telainicial;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        JFrame telainicial = new Telainicial(
            new UserController(new UserRepository(DBFactory.getSessionFactory())), 
            new ProfissionalController(new ProfissionalRepository(DBFactory.getSessionFactory())), 
            new AgendamentoController(new AgendamentoRepository(DBFactory.getSessionFactory())));

        telainicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telainicial.setSize(750, 500);
        telainicial.setVisible(true);

    }
}
