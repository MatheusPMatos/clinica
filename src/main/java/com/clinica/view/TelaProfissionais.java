package com.clinica.view;

import javax.swing.*;
import java.awt.*;

public class TelaProfissionais extends JPanel {

    public TelaProfissionais() {

        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("Lista de Profissionais", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(title, BorderLayout.NORTH);
        this.setVisible(true);
    }

}
