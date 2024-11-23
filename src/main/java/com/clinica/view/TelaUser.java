package com.clinica.view;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;
import com.clinica.controller.IUserController;
import com.clinica.models.User;

public class TelaUser extends JPanel {
    private IUserController userController;

    public TelaUser(IUserController userController) {
        this.userController = userController;

        // Definir layout do painel
        this.setLayout(new BorderLayout());

        // Título
        JLabel title = new JLabel("Lista de Usuários", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(title, BorderLayout.NORTH);

        // Obter lista de usuários do controlador
        List<User> users;
        try {
            users = userController.list();
        } catch (Exception e) {
            System.out.println("Erro ao carregar usuarios erro: " + e.getMessage());
            users = Collections.emptyList();

        }

        // Criar o modelo para a JList
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (User user : users) {
            listModel.addElement("Nome: " + user.getName() + " | CPF: " + user.getCpf());
        }

        // Criar a JList com o modelo
        JList<String> userList = new JList<>(listModel);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Adicionar a JList em um JScrollPane
        JScrollPane scrollPane = new JScrollPane(userList);

        // Adicionar o JScrollPane ao centro do painel
        this.add(scrollPane, BorderLayout.CENTER);
    }
}
