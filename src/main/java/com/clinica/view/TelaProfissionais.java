package com.clinica.view;

import javax.swing.*;

import com.clinica.controller.IProfissionalController;
import com.clinica.models.Profissional;
import java.util.List;
import java.awt.*;
import java.util.Collections;

public class TelaProfissionais extends JPanel {

    public TelaProfissionais(IProfissionalController profissionalController) {

         this.setLayout(new GridBagLayout());

        // Painel interno para conter os componentes
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BorderLayout());
        innerPanel.setPreferredSize(new Dimension(600, 400)); // Ajuste o tamanho conforme necessário
        innerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margem ao redor

        // Título
        JLabel title = new JLabel("Lista de Profissionais", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        innerPanel.add(title, BorderLayout.NORTH);

        // Obter lista de usuários do controlador
        List<Profissional> profissionais;
        try {
            profissionais = profissionalController.list();
        } catch (Exception e) {
            System.out.println("Erro ao carregar Profissionais: " + e.getMessage());
            profissionais = Collections.emptyList();
        }

        // Painel para os usuários
        JPanel usersPanel = new JPanel();
        usersPanel.setLayout(new BoxLayout(usersPanel, BoxLayout.Y_AXIS));
        usersPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margem ao redor

        // Adicionar cada usuário ao painel
        for (Profissional profissional : profissionais) {
            JPanel userTile = new JPanel();
            userTile.setLayout(new FlowLayout(FlowLayout.LEFT));
            userTile.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            // Nome e CPF
            JLabel userInfo = new JLabel("Nome: " + profissional.getName() + " | CPF: " + profissional.getCpf() + " | CAT: " + profissional.getCategoria().name());

            // Botão Editar
            JButton editButton = new JButton("Editar");
            editButton.addActionListener(e -> editarProf(profissionalController, profissional));

            // Botão Excluir
            JButton deleteButton = new JButton("Excluir");
            deleteButton.addActionListener(e -> excluirProf(profissionalController, profissional));

            // Adicionar componentes ao userTile
            userTile.add(userInfo);
            userTile.add(editButton);
            userTile.add(deleteButton);

            // Adicionar userTile ao painel de usuários
            usersPanel.add(userTile);
        }

        // Adicionar o painel de usuários ao centro do painel interno
        JScrollPane scrollPane = new JScrollPane(usersPanel);
        innerPanel.add(scrollPane, BorderLayout.CENTER);

        // Adicionar o painel interno ao centro do painel principal
        this.add(innerPanel, new GridBagConstraints());
    }

    private void editarProf(IProfissionalController profissionalController, Profissional profissional) {
        this.removeAll(); // Remove todos os componentes da tela atual
        PanelCriarProf updatedTelaUser = new PanelCriarProf(profissionalController,true, profissional); // Recria a tela
        this.setLayout(new BorderLayout()); // Define o layout
        this.add(updatedTelaUser, BorderLayout.CENTER); // Adiciona a nova tela
        this.revalidate(); // Atualiza o layout
        this.repaint();
    }

    private void excluirProf(IProfissionalController profissionalController, Profissional profissional) {
        // Exibir um diálogo de confirmação antes de excluir
        int confirm = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja excluir o Profissional " + profissional.getName() + "?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {  
                profissionalController.delete(profissional.getId()); // Método do UserController para criar o usuário
                JOptionPane.showMessageDialog(this, "Profissional deletado!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);

                        this.removeAll(); // Remove todos os componentes da tela atual
                        TelaProfissionais updatedTelaUser = new TelaProfissionais(profissionalController); // Recria a tela
                        this.setLayout(new BorderLayout()); // Define o layout
                        this.add(updatedTelaUser, BorderLayout.CENTER); // Adiciona a nova tela
            
                        this.revalidate(); // Atualiza o layout
                        this.repaint(); 
            } catch (Exception e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(this, "Erro ao deletar profissional: " + e.getMessage(), "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
