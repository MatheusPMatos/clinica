package com.clinica.view;

import javax.swing.*;
import java.awt.*;

import com.clinica.controller.IProfissionalController;
import com.clinica.models.Categoria;
import com.clinica.models.Profissional;

public class PanelCriarProf extends JPanel {
    private IProfissionalController profissionalController;
    private JTextField nomeField;
    private JTextField cpfField;
    private JComboBox<Categoria> categoriaComboBox;

    public PanelCriarProf(IProfissionalController profissionalController) {

        // Definir layout do painel
        this.setLayout(new GridLayout(4, 2, 10, 10)); // 4 linhas (nome, CPF, categoria, botão)

        // Labels
        JLabel nomeLabel = new JLabel("Nome:");
        JLabel cpfLabel = new JLabel("CPF:");
        JLabel categoriaLabel = new JLabel("Categoria:");

        // Campos de texto
        nomeField = new JTextField(20);
        cpfField = new JTextField(20);

        // ComboBox para categoria
        categoriaComboBox = new JComboBox<>(Categoria.values());

        // Botão de criar
        JButton criarButton = new JButton("Criar Profissional");

        // Adicionar componentes ao painel
        this.add(nomeLabel);
        this.add(nomeField);
        this.add(cpfLabel);
        this.add(cpfField);
        this.add(categoriaLabel);
        this.add(categoriaComboBox);
        this.add(new JLabel()); // Espaço vazio para alinhar
        this.add(criarButton);

        // Ação do botão
        criarButton.addActionListener(e -> criarProfissional());
    }

    private void criarProfissional() {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        Categoria categoria = (Categoria) categoriaComboBox.getSelectedItem();

        // Validar campos
        if (nome.isEmpty() || cpf.isEmpty() || categoria == null) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Criar o profissional e salvar usando o controlador
        try {
            Profissional profissional = new Profissional(nome, cpf, categoria);
            profissionalController.create(profissional); // Método para salvar no banco
            JOptionPane.showMessageDialog(this, "Profissional criado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            limparCampos(); // Limpar os campos após criação
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Erro ao criar profissional: " + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para limpar os campos de entrada
    private void limparCampos() {
        nomeField.setText("");
        cpfField.setText("");
        categoriaComboBox.setSelectedIndex(0); // Volta ao primeiro item
    }
}
