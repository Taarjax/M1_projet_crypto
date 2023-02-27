package org.project;
import org.project.models.Paquet_de_cartes;
import org.project.controllers.Solitaire;
import org.project.view.mainView;

/**
 * Class main
 */
public class Main {
    public static void main(String[] args){
        Paquet_de_cartes paquet = new Paquet_de_cartes();
        paquet.remplir_paquet_de_carte();
        Solitaire solitaire = new Solitaire(paquet);

        mainView mainView = new mainView(paquet, solitaire);
        mainView.pack();
        mainView.setVisible(true);
    }
}