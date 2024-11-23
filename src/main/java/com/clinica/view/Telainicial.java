package com.clinica.view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.clinica.controller.IUserController;

public class Telainicial extends JFrame {
    private static final long serialVersionUID = -346123942816557733L;

    private TelaUser crudUser;

    // private JMenuBar barmenu;
    // private JMenu menuCadastro;
    // private JMenu menuCompra;
    // private JMenu menuAjuda;
    // private JMenuItem itemMenuClient;
    // private JMenuItem itemMenuFonecedor;
    // private JMenuItem itemMenuProduto;
    // private JMenuItem itemMenuSobre;
    // private JMenuItem itemMenuSair;

    public Telainicial() {

        super("Loja IFPR");

        JMenuBar barmenu = new JMenuBar();

        JMenu menuUsers = new JMenu("Pacientes");
        JMenuItem itemUserCreate = new JMenuItem("Criar");
        JMenuItem itemUserLista = new JMenuItem("Listar");
        menuUsers.add(itemUserLista);
        menuUsers.add(itemUserCreate);
        itemUserLista.addActionListener(e -> Telainicial.this.add(new TelaUser()).repaint());

        JMenu menuProfissionais = new JMenu("Profissionais");
        JMenuItem itemProfCreate = new JMenuItem("Criar");
        JMenuItem itemProfLista = new JMenuItem("Listar");

        menuProfissionais.add(itemProfCreate);
        menuProfissionais.add(itemProfLista);

        JMenu menuAgenda = new JMenu("Agenda");
        JMenuItem itemAgCreate = new JMenuItem("Criar");
        JMenuItem itemAgLista = new JMenuItem("Listar");

        itemProfLista.addActionListener(e -> Telainicial.this.add(new TelaProfissionais()).repaint());

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

        // new JOptionPane();
        itemMenuSobre.addActionListener(arg -> new JOptionPane().showMessageDialog(Telainicial.this,
                "Este é um exemplo\n" + "do uso de menus"));
        // new JOptionPane();
        itemMenuSair.addActionListener(e -> System.exit(0));
        // new JOptionPane();
    }
}
