package org.project.view;

import org.project.models.Carte;
import org.project.models.Paquet_de_cartes;

import javax.swing.*;
import java.awt.*;

public class mainView extends JFrame {
    private JPanel mainPanel;
    private JPanel cardPanel;

    public mainView(Paquet_de_cartes paquet) {
        initComponent();
        initCardPannel(paquet);
    }

    public void initComponent() {
        mainPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1200, 600));
        this.setTitle("Solitaire");
        this.setContentPane(mainPanel);
    }

    private void initCardPannel(Paquet_de_cartes paquet) {
        cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        int[] cardCounts = new int[]{13, 13, 13, 13};
        int row = 0;

        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new FlowLayout());
        JPanel jokerPanel = new JPanel();
        jokerPanel.setLayout(new FlowLayout());

        for (Carte carte : paquet.getPaquet_de_carte()) {
            JLabel label = new JLabel();
            ImageIcon imgIcon_carte;
            if (!carte.estJokerNoir() && !carte.estJokerRouge()) {
                imgIcon_carte = new ImageIcon("src\\main\\resources\\assets\\" + carte.getValeur() + "_" + carte.getCouleur() + ".png");
                rowPanel.add(label);
                cardCounts[row]--;
                if (cardCounts[row] == 0) {
                    cardPanel.add(rowPanel);
                    rowPanel = new JPanel();
                    rowPanel.setLayout(new FlowLayout());
                    row++;
                }
            } else {
                imgIcon_carte = new ImageIcon("src\\main\\resources\\assets\\" + carte.getJoker() + ".png");
                jokerPanel.add(label);
            }

            Image img_carte = imgIcon_carte.getImage();
            Image img_resize_carte = img_carte.getScaledInstance(76, 100, java.awt.Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img_resize_carte));
        }

        cardPanel.add(jokerPanel);
        cardPanel.add(rowPanel);
        mainPanel.add(cardPanel);
    }


}
