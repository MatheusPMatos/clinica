package com.clinica.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.clinica.controller.IAgendamentoController;
import com.clinica.models.Agendamento;
import com.clinica.models.Profissional;
import com.clinica.models.User;

public class TelaAgenda extends JPanel {


    TelaAgenda(IAgendamentoController agendamentoController, List<User> userList, List<Profissional> profList){

    this.setLayout(new GridBagLayout());

        // Painel interno para conter os componentes
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BorderLayout());
        innerPanel.setPreferredSize(new Dimension(700, 400)); // Ajuste o tamanho conforme necessário
        innerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margem ao redor

        // Título
        JLabel title = new JLabel("Lista de Agendamentos", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        innerPanel.add(title, BorderLayout.NORTH);

        // Obter lista de usuários do controlador
        List<Agendamento> agendas;
        try {
            agendas = agendamentoController.list();
        } catch (Exception e) {
            System.out.println("Erro ao carregar Agendamentos: " + e.getMessage());
            agendas = Collections.emptyList();
        }

        // Painel para os usuários
        JPanel usersPanel = new JPanel();
        usersPanel.setLayout(new BoxLayout(usersPanel, BoxLayout.Y_AXIS));
        usersPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margem ao redor

        // Adicionar cada usuário ao painel
        for (Agendamento agenda : agendas) {
            JPanel userTile = new JPanel();
            userTile.setLayout(new FlowLayout(FlowLayout.LEFT));
            userTile.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            // Nome e CPF
            JLabel userInfo = new JLabel("Dia: " + agenda.getData() + " | Profissional: " + agenda.getProfissional().getName()+ " | Paciente: " + agenda.getUser().getName());

            // Botão Editar
            JButton editButton = new JButton("Editar");
            editButton.addActionListener(e -> editarAgendamento(agendamentoController,userList, profList, agenda));

            // Botão Excluir
            JButton deleteButton = new JButton("Excluir");
            deleteButton.addActionListener(e -> excluirAgendamento(agendamentoController,userList,profList, agenda));

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

    private void editarAgendamento(IAgendamentoController agendamentoController,  List<User> userList, List<Profissional> profList, Agendamento agendamento) {
        this.removeAll(); // Remove todos os componentes da tela atual
        PanelCriarAgenda updatedTelaUser = new PanelCriarAgenda(agendamentoController,userList,profList ,true, agendamento); // Recria a tela
        this.setLayout(new BorderLayout()); // Define o layout
        this.add(updatedTelaUser, BorderLayout.CENTER); // Adiciona a nova tela
        this.revalidate(); // Atualiza o layout
        this.repaint();
    }

    private void excluirAgendamento(IAgendamentoController agendamentoController, List<User> userList, List<Profissional> profList, Agendamento agendamento) {
        // Exibir um diálogo de confirmação antes de excluir
        int confirm = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja excluir o agendamento numero: " + agendamento.getId() + "?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {  
                agendamentoController.delete(agendamento.getId()); // Método do UserController para criar o usuário
                JOptionPane.showMessageDialog(this, "Agendamento deletado!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);

                        this.removeAll(); // Remove todos os componentes da tela atual
                        TelaAgenda updatedTelaAgenda = new TelaAgenda(agendamentoController,userList, profList ); // Recria a tela
                        this.setLayout(new BorderLayout()); // Define o layout
                        this.add(updatedTelaAgenda, BorderLayout.CENTER); // Adiciona a nova tela
            
                        this.revalidate(); // Atualiza o layout
                        this.repaint(); 
            } catch (Exception e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(this, "Erro ao deletar Agendamento: " + e.getMessage(), "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
