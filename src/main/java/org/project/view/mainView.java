package org.project.view;

import org.project.controllers.Solitaire;
import org.project.models.Carte;
import org.project.models.Paquet_de_cartes;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class mainView extends JFrame {
    private JPanel mainPanel;
    private JButton melange_btn;
    private JTextField inputReponse;
    private JButton crypterButton;
    private JButton decrypterButton;
    private JTextField inputMessage;
    private JPanel contentPanel;
    private JButton paquet_btn;
    private JButton exporterButton;
    private JButton importerButton;
    private ArrayList paquet_apres_melange_clone;
    private Paquet_de_cartes clef_de_base = new Paquet_de_cartes();


    public mainView(Paquet_de_cartes paquet, Solitaire solitaire) {
        clef_de_base.remplir_paquet_de_carte();
        paquet_apres_melange_clone = (ArrayList) paquet.getPaquet_de_carte().clone();
        this.setContentPane(mainPanel);
        this.setTitle("Solitaire");
        this.setSize(1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Action bouton afficher le paquet de carte
        paquet_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(paquet);
                JFrame frame = new JFrame("Paquet de carte");
                frame.setSize(1200, 600);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);

                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(4, 13));

                JButton validateKeyButton = new JButton("Valider");
                validateKeyButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clef_de_base.setPaquet_de_carte(paquet_apres_melange_clone);
                        solitaire.setClefDeBase(clef_de_base);
                        solitaire.setPaquet_de_depart(clef_de_base);

                    }
                });

                for (Carte carte : paquet.getPaquet_de_carte()) {
                    JLabel label = new JLabel();
                    ImageIcon imgIcon_carte;

                    if (carte.estJokerNoir() || carte.estJokerRouge()) {
                        imgIcon_carte = new ImageIcon("src\\main\\resources\\assets\\" + carte.getJoker() + ".png");
                    } else {
                        imgIcon_carte = new ImageIcon("src\\main\\resources\\assets\\" + carte.getValeur() + "_" + carte.getCouleur() + ".png");
                    }
                    Image img_carte = imgIcon_carte.getImage();
                    Image img_resize_carte = img_carte.getScaledInstance(76, 100, java.awt.Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(img_resize_carte));
                    panel.add(label);
                }
                frame.add(panel);
                panel.add(validateKeyButton);
                frame.setVisible(true);
            }
        });

        // Action bouton Melanger le paquet de carte
        melange_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paquet.melanger();
                paquet_apres_melange_clone = (ArrayList<Carte>) paquet.getPaquet_de_carte().clone();
                System.out.println("Melange");
                System.out.println(paquet);
            }
        });

        //Action bouton crypter
        crypterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = inputMessage.getText();
                try {
                    String reponse = solitaire.crypter(message, solitaire.getPaquet_de_depart());
                    inputReponse.setText(reponse);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        decrypterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = inputReponse.getText();
                try {
                    String reponse = solitaire.decrypter(message, solitaire.getClefDeBase());
                    inputMessage.setText(reponse);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        importerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers texte (.txt)", "txt");
                fileChooser.setFileFilter(filter);

                int result = fileChooser.showOpenDialog(mainPanel);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    paquet.importPaquet(selectedFile);
                }
            }
        });
        exporterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paquet.exportPaquet();
            }
        });
        //Check conv button
    }
}