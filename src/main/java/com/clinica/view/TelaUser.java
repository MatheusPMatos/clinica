package com.clinica.view;

import javax.swing.*;
import java.awt.*;

public class TelaUser extends JPanel {
    // private IUserController userController;

    public TelaUser() {// IUserController userController) {

        // this.userController = userController;
        // // Criação da barra de menu (opcional)

        // this.setLayout(new BorderLayout());

        // // Título
        // JLabel title = new JLabel("Lista de Usuários", JLabel.CENTER);
        // title.setFont(new Font("Arial", Font.BOLD, 18));
        // this.add(title, BorderLayout.NORTH);

        // // Obter lista de usuários
        // List<User> users = userController.list();

        // // Criar o modelo e a lista
        // DefaultListModel<String> listModel = new DefaultListModel<>();
        // for (User user : users) {
        // listModel.addElement("Nome: " + user.getName() + " | CPF: " + user.getCpf());
        // }

        // JList<String> userList = new JList<>(listModel);
        // userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // userList.setVisibleRowCount(10);

        // // Adicionar a lista a um JScrollPane
        // JScrollPane scrollPane = new JScrollPane(userList);
        // panel.add(scrollPane, BorderLayout.CENTER);

        // // Adicionar o painel à janela
        // this.add(panel);

        // // Tornar a janela visível
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("Lista de Usuários", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(title, BorderLayout.NORTH);
        this.setVisible(true);
    }
}
