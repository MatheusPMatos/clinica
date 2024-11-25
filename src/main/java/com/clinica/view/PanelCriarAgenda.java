package com.clinica.view;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

import com.clinica.controller.IAgendamentoController;
import com.clinica.models.Agendamento;
import com.clinica.models.Profissional;
import com.clinica.models.User;

public class PanelCriarAgenda extends JPanel {
    private IAgendamentoController agendamentoController;
    private JComboBox<User> userComboBox;
    private JComboBox<Profissional> profissionalComboBox;
    private JSpinner dataSpinner;

    public PanelCriarAgenda(IAgendamentoController agendamentoController, List<User> users, List<Profissional> profissionais, boolean isEditAgenda, Agendamento agendamento) {
        this.agendamentoController = agendamentoController;

        // Definir layout do painel principal
        this.setLayout(new GridBagLayout());

        // Painel interno para centralizar e dimensionar os componentes
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new GridLayout(4, 2, 10, 10)); // 4 linhas (Usuário, Profissional, Data, Botão)
        innerPanel.setPreferredSize(new Dimension(400, 200)); // Ajuste o tamanho conforme necessário
        innerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margem interna

        // Labels
        JLabel userLabel = new JLabel("Usuário:");
        JLabel profissionalLabel = new JLabel("Profissional:");
        JLabel dataLabel = new JLabel("Data:");

        // ComboBox para selecionar usuários
        userComboBox = new JComboBox<>(users.toArray(new User[0]));

        userComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof User) {
                    value = ((User) value).getName();
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        

        // ComboBox para selecionar profissionais
        profissionalComboBox = new JComboBox<>(profissionais.toArray(new Profissional[0]));

        profissionalComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Profissional) {
                    value = ((Profissional) value).getName();
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });

        // Spinner para selecionar data
        dataSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dataSpinner, "dd/MM/yyyy HH:mm");
        dataSpinner.setEditor(dateEditor);

        if (isEditAgenda) {
            userComboBox.setSelectedItem(agendamento.getUser());;
            profissionalComboBox.setSelectedItem(agendamento.getProfissional());
            dataSpinner.setValue(agendamento.getData());
        }


        // Botão de criar agendamento
        JButton criarButton = new JButton(isEditAgenda? "Editar Agendamento" :"Criar Agendamento");

        // Adicionar componentes ao painel interno
        innerPanel.add(userLabel);
        innerPanel.add(userComboBox);
        innerPanel.add(profissionalLabel);
        innerPanel.add(profissionalComboBox);
        innerPanel.add(dataLabel);
        innerPanel.add(dataSpinner);
        innerPanel.add(new JLabel()); // Espaço vazio para alinhar
        innerPanel.add(criarButton);

        // Adicionar o painel interno ao painel principal
        this.add(innerPanel, new GridBagConstraints());

        // Ação do botão
        criarButton.addActionListener(e -> {
            if (isEditAgenda) {
                editarAgendamento(agendamento);
                return;
            }
            criarAgendamento();
        });
    }

    private void criarAgendamento() {
        User user = (User) userComboBox.getSelectedItem();
        Profissional profissional = (Profissional) profissionalComboBox.getSelectedItem();
        Date data = (Date) dataSpinner.getValue();

        // Validar campos
        if (user == null || profissional == null || data == null) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Criar o agendamento e salvar usando o controlador
        try {
            Agendamento agendamento = new Agendamento();
            agendamento.setProfissional(profissional);
            agendamento.setData(data);
            agendamento.setUser(user);

            agendamentoController.create(agendamento);
            JOptionPane.showMessageDialog(this, "Agendamento criado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            limparCampos(); // Limpar os campos após criação
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Erro ao criar agendamento: " + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarAgendamento(Agendamento agendamento) {
        User user = (User) userComboBox.getSelectedItem();
        Profissional profissional = (Profissional) profissionalComboBox.getSelectedItem();
        Date data = (Date) dataSpinner.getValue();

        // Validar campos
        if (user == null || profissional == null || data == null) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Criar o agendamento e salvar usando o controlador
        try {
            
            agendamento.setProfissional(profissional);
            agendamento.setData(data);
            agendamento.setUser(user);

            agendamentoController.edit(agendamento);
            JOptionPane.showMessageDialog(this, "Agendamento editado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            limparCampos(); // Limpar os campos após criação
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Erro ao editar agendamento: " + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para limpar os campos de entrada
    private void limparCampos() {
        userComboBox.setSelectedIndex(0);
        profissionalComboBox.setSelectedIndex(0);
        dataSpinner.setValue(new Date()); // Reseta para a data atual
    }
}
