package com.clinica;

import javax.swing.JFrame;

import com.clinica.Database.DBFactory;
import com.clinica.Repository.Impl.UserRepository;
import com.clinica.controller.Impl.UserController;
import com.clinica.view.TelaUser;
import com.clinica.view.Telainicial;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        // DBFactory factory = new DBFactory();

        JFrame telainicial = new Telainicial();
        telainicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telainicial.setSize(700, 500);
        telainicial.setVisible(true);

    }
}
