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

    public PanelCriarProf(IProfissionalController profissionalController,boolean isEditProf, Profissional profissional) {
        this.profissionalController = profissionalController;

      

        // Definir layout do painel principal
        this.setLayout(new GridBagLayout());

        // Painel interno para centralizar e dimensionar os componentes
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new GridLayout(4, 2, 10, 10)); // 4 linhas (nome, CPF, categoria, botão)
        innerPanel.setPreferredSize(new Dimension(400, 200)); // Ajuste o tamanho conforme necessário
        innerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margem interna

        // Labels
        JLabel nomeLabel = new JLabel("Nome:");
        JLabel cpfLabel = new JLabel("CPF:");
        JLabel categoriaLabel = new JLabel("Categoria:");

        // Campos de texto
        nomeField = new JTextField(20);
        cpfField = new JTextField(20);
        if (isEditProf) {
            nomeField.setText(profissional.getName());
            cpfField.setText(profissional.getCpf());
        }

        // ComboBox para categoria
        categoriaComboBox = new JComboBox<>(Categoria.values());

        // Botão de criar
        JButton criarButton = new JButton(isEditProf? "Editar Profissional":"Criar Profissional");

        // Adicionar componentes ao painel interno
        innerPanel.add(nomeLabel);
        innerPanel.add(nomeField);
        innerPanel.add(cpfLabel);
        innerPanel.add(cpfField);
        innerPanel.add(categoriaLabel);
        innerPanel.add(categoriaComboBox);
        innerPanel.add(new JLabel()); // Espaço vazio para alinhar
        innerPanel.add(criarButton);

        // Adicionar o painel interno ao painel principal
        this.add(innerPanel, new GridBagConstraints());

        // Ação do botão
        criarButton.addActionListener(e ->{
            if (isEditProf) {
                editarProfissional(profissional);
                return;
            }
            criarProfissional();
        });
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
    private void editarProfissional(Profissional profissional) {
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
            profissional.setCategoria(categoria);
            profissional.setCpf(cpf);
            profissional.setName(nome);
            profissionalController.edit(profissional); // Método para salvar no banco
            JOptionPane.showMessageDialog(this, "Profissional editado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            limparCampos(); // Limpar os campos após criação
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Erro ao editar profissional: " + e.getMessage(), "Erro",
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
