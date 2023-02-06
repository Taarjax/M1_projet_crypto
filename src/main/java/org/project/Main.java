package org.project;

import org.project.models.Paquet_de_cartes;
import org.project.controllers.Solitaire;
import org.project.view.mainView;

public class Main {
    public static void main(String[] args) throws Exception {
        Paquet_de_cartes paquet = new Paquet_de_cartes();
        Solitaire solitaire = new Solitaire(paquet);
        paquet.remplir_paquet_de_carte();

        System.out.println(paquet.getPaquet_de_carte().get(0).getValeur() + "_" + paquet.getPaquet_de_carte().get(0).getCouleur());

        mainView mainView = new mainView(paquet);
        mainView.pack();
        mainView.setVisible(true);
    }
}