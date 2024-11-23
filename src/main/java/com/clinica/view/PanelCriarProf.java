package com.clinica.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.clinica.controller.IUserController;
import com.clinica.models.User;

public class PanelCriarProf extends JPanel {
    private IUserController userController;
    private JTextField nomeField;
    private JTextField cpfField;

    public PanelCriarProf(IUserController userController) {
        this.userController = userController;

        // Definir layout do painel
        this.setLayout(new GridLayout(3, 2));

        // Labels
        JLabel nomeLabel = new JLabel("Nome:");
        JLabel cpfLabel = new JLabel("CPF:");

        // Campos de texto
        nomeField = new JTextField(20);
        cpfField = new JTextField(20);

        // Botão de criar
        JButton criarButton = new JButton("Criar Usuário");

        // Adicionar componentes ao painel
        this.add(nomeLabel);
        this.add(nomeField);
        this.add(cpfLabel);
        this.add(cpfField);
        this.add(criarButton);

        // Ação do botão
        criarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarUsuario();
            }
        });
    }

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
