package org.project;

import org.project.models.Paquet_de_cartes;
import org.project.controllers.Solitaire;
import org.project.view.mainView;

public class Main {
    public static void main(String[] args) throws Exception {
        Paquet_de_cartes paquet = new Paquet_de_cartes();
        Solitaire solitaire = new Solitaire(paquet);
        paquet.remplir_paquet_de_carte();

        mainView mainView = new mainView(paquet, solitaire);
        mainView.pack();
        mainView.setVisible(true);
    }
}