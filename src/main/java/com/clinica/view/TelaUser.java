package com.clinica.view;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;
import com.clinica.controller.IUserController;
import com.clinica.models.User;

public class TelaUser extends JPanel {

    public TelaUser(IUserController userController) {

        // Definir layout do painel principal
        this.setLayout(new GridBagLayout());

        // Painel interno para conter os componentes
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BorderLayout());
        innerPanel.setPreferredSize(new Dimension(600, 400)); // Ajuste o tamanho conforme necessário
        innerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margem ao redor

        // Título
        JLabel title = new JLabel("Lista de Usuários", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        innerPanel.add(title, BorderLayout.NORTH);

        // Obter lista de usuários do controlador
        List<User> users;
        try {
            users = userController.list();
        } catch (Exception e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
            users = Collections.emptyList();
        }

        // Painel para os usuários
        JPanel usersPanel = new JPanel();
        usersPanel.setLayout(new BoxLayout(usersPanel, BoxLayout.Y_AXIS));
        usersPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margem ao redor

        // Adicionar cada usuário ao painel
        for (User user : users) {
            JPanel userTile = new JPanel();
            userTile.setLayout(new FlowLayout(FlowLayout.LEFT));
            userTile.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            // Nome e CPF
            JLabel userInfo = new JLabel("Nome: " + user.getName() + " | CPF: " + user.getCpf());

            // Botão Editar
            JButton editButton = new JButton("Editar");
            editButton.addActionListener(e -> editarUsuario(userController, user));

            // Botão Excluir
            JButton deleteButton = new JButton("Excluir");
            deleteButton.addActionListener(e -> excluirUsuario(user, userController));

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

    private void editarUsuario(IUserController userController, User user) {
        this.removeAll(); // Remove todos os componentes da tela atual
        PanelCriarUser updatedTelaUser = new PanelCriarUser(userController,true, user); // Recria a tela
        this.setLayout(new BorderLayout()); // Define o layout
        this.add(updatedTelaUser, BorderLayout.CENTER); // Adiciona a nova tela
        this.revalidate(); // Atualiza o layout
        this.repaint();
    }

    private void excluirUsuario(User user, IUserController userController) {
        // Exibir um diálogo de confirmação antes de excluir
        int confirm = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja excluir o usuário " + user.getName() + "?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {  
                userController.delete(user.getId()); // Método do UserController para criar o usuário
                JOptionPane.showMessageDialog(this, "Usuário deletado!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);

                        this.removeAll(); // Remove todos os componentes da tela atual
                        TelaUser updatedTelaUser = new TelaUser(userController); // Recria a tela
                        this.setLayout(new BorderLayout()); // Define o layout
                        this.add(updatedTelaUser, BorderLayout.CENTER); // Adiciona a nova tela
            
                        this.revalidate(); // Atualiza o layout
                        this.repaint(); 
            } catch (Exception e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(this, "Erro ao deletar usuário: " + e.getMessage(), "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
