package com.clinica.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.clinica.controller.IUserController;
import com.clinica.models.User;

public class PanelCriarUser extends JPanel {
    private IUserController userController;
    private JTextField nomeField;
    private JTextField cpfField;

    public PanelCriarUser(IUserController userController) {
        this.userController = userController;

        // Definir layout do painel
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margens entre os componentes
        gbc.anchor = GridBagConstraints.WEST; // Alinhar à esquerda

        // Labels
        JLabel nomeLabel = new JLabel("Nome:");
        JLabel cpfLabel = new JLabel("CPF:");

        // Campos de texto
        nomeField = new JTextField(20); // Largura em colunas
        cpfField = new JTextField(20);

        // Botão de criar
        JButton criarButton = new JButton("Criar Usuário");

        // Configuração e adição dos componentes ao painel

        // Nome Label
        gbc.gridx = 0; // Coluna 0
        gbc.gridy = 0; // Linha 0
        gbc.fill = GridBagConstraints.NONE; // Não expandir
        this.add(nomeLabel, gbc);

        // Nome Field
        gbc.gridx = 1; // Coluna 1
        gbc.gridy = 0; // Linha 0
        gbc.fill = GridBagConstraints.HORIZONTAL; // Expandir horizontalmente
        this.add(nomeField, gbc);

        // CPF Label
        gbc.gridx = 0; // Coluna 0
        gbc.gridy = 1; // Linha 1
        gbc.fill = GridBagConstraints.NONE;
        this.add(cpfLabel, gbc);

        // CPF Field
        gbc.gridx = 1; // Coluna 1
        gbc.gridy = 1; // Linha 1
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(cpfField, gbc);

        // Botão Criar
        gbc.gridx = 1; // Coluna 1
        gbc.gridy = 2; // Linha 2
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER; // Centralizar botão
        this.add(criarButton, gbc);

        // Ação do botão
        criarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarUsuario();
            }
        });
    }

    // Método para criar um usuário
    private void criarUsuario() {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();

        // Validar campos
        if (nome.isEmpty() || cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Criar o usuário e salvar usando o controlador
        try {
            User user = new User(nome, cpf);
            userController.create(user); // Método do UserController para criar o usuário
            JOptionPane.showMessageDialog(this, "Usuário criado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            limparCampos(); // Limpar os campos após criação
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Erro ao criar usuário: " + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para limpar os campos de entrada
    private void limparCampos() {
        nomeField.setText("");
        cpfField.setText("");
    }
}
