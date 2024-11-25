package com.clinica.view;

import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.clinica.controller.IAgendamentoController;
import com.clinica.controller.IProfissionalController;
import com.clinica.controller.IUserController;
import com.clinica.models.Profissional;
import com.clinica.models.User;

public class Telainicial extends JFrame {
    private static final long serialVersionUID = -346123942816557733L;

    public Telainicial(IUserController userController, IProfissionalController profissionalController, IAgendamentoController agendamentoController) {

        super("Loja IFPR");

        JMenuBar barmenu = new JMenuBar();

        JMenu menuUsers = new JMenu("Pacientes");
        JMenuItem itemUserCreate = new JMenuItem("Criar");
        JMenuItem itemUserLista = new JMenuItem("Listar");
        menuUsers.add(itemUserLista);
        menuUsers.add(itemUserCreate);

        itemUserLista.addActionListener(e -> {
            // Remover todos os componentes do JFrame atual
            Telainicial.this.getContentPane().removeAll();

            // Adicionar o novo painel TelaUser
            Telainicial.this.add(new TelaUser(userController));

            // Revalidar e repintar a janela para refletir a mudança
            Telainicial.this.revalidate();
            Telainicial.this.repaint();
        });
        itemUserCreate.addActionListener(e -> {
            // Remover todos os componentes do JFrame atual
            Telainicial.this.getContentPane().removeAll();

            // Adicionar o novo painel TelaUser
            Telainicial.this.add(new PanelCriarUser(userController, false, null));

            // Revalidar e repintar a janela para refletir a mudança
            Telainicial.this.revalidate();
            Telainicial.this.repaint();
        });

        JMenu menuProfissionais = new JMenu("Profissionais");
        JMenuItem itemProfCreate = new JMenuItem("Criar");
        JMenuItem itemProfLista = new JMenuItem("Listar");

        menuProfissionais.add(itemProfLista);
        menuProfissionais.add(itemProfCreate);

        itemProfCreate.addActionListener(e -> {
            Telainicial.this.getContentPane().removeAll();

            // Adicionar o novo painel TelaUser
            Telainicial.this.add(new PanelCriarProf(profissionalController, false, null));

            // Revalidar e repintar a janela para refletir a mudança
            Telainicial.this.revalidate();
            Telainicial.this.repaint();
        });

        itemProfLista.addActionListener(e -> {
            // Remover todos os componentes do JFrame atual
            Telainicial.this.getContentPane().removeAll();

            // Adicionar o novo painel TelaUser
            Telainicial.this.add(new TelaProfissionais(profissionalController));

            // Revalidar e repintar a janela para refletir a mudança
            Telainicial.this.revalidate();
            Telainicial.this.repaint();
        });


        JMenu menuAgenda = new JMenu("Agenda");
        JMenuItem itemAgCreate = new JMenuItem("Criar");
        JMenuItem itemAgLista = new JMenuItem("Listar");

        itemAgLista.addActionListener(e -> {
            // Remover todos os componentes do JFrame atual


            Telainicial.this.getContentPane().removeAll();
            // Adicionar o novo painel TelaUser
            Telainicial.this.add(new TelaAgenda(agendamentoController,getUsers(userController), getProfs(profissionalController)));

            // Revalidar e repintar a janela para refletir a mudança
            Telainicial.this.revalidate();
            Telainicial.this.repaint();
        });

        itemAgCreate.addActionListener(e -> {
            // Remover todos os componentes do JFrame atual
            Telainicial.this.getContentPane().removeAll();

            // Adicionar o novo painel TelaUser
            Telainicial.this.add(new PanelCriarAgenda(agendamentoController,getUsers(userController), getProfs(profissionalController), false, null));

            // Revalidar e repintar a janela para refletir a mudança
            Telainicial.this.revalidate();
            Telainicial.this.repaint();
        });
       
        menuAgenda.add(itemAgLista);
        menuAgenda.add(itemAgCreate);

        

        JMenu menuAjuda = new JMenu("Ajuda");
        JMenuItem itemMenuSobre = new JMenuItem("Sobre");
        JMenuItem itemMenuSair = new JMenuItem("Sair");
        menuAjuda.add(itemMenuSobre);
        menuAjuda.add(itemMenuSair);

        setJMenuBar(barmenu);
        barmenu.add(menuUsers);
        barmenu.add(menuProfissionais);
        barmenu.add(menuAgenda);
        barmenu.add(menuAjuda);

        
        itemMenuSobre.addActionListener(arg -> JOptionPane.showMessageDialog(Telainicial.this,
                "Sistema de agendamento de consultas.\n" + "Desenvolvido por:\n\tMatheus\n\tDiego\n\tRarinaldo\n\tMarcos"));
        itemMenuSair.addActionListener(e -> System.exit(0));
    }

    private List<User> getUsers(IUserController userController) {
        try {
           return userController.list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Erro ao carregar tela de agendamento" + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return Collections.emptyList();
        }
    
        private List<Profissional> getProfs(IProfissionalController profissionalController) {
            try {
               return profissionalController.list();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(this, "Erro ao carregar tela de agendamento" + e.getMessage(), "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
            return Collections.emptyList();
            }

    


}
