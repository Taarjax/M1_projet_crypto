package org.project;

import org.project.models.Paquet_de_cartes;
import org.project.controllers.Solitaire;
import org.project.view.mainView;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        Paquet_de_cartes paquet = new Paquet_de_cartes();
        paquet.remplir_paquet_de_carte();
        Solitaire solitaire = new Solitaire(paquet);


        // Sensé donné
        /*
        lvbjw
        fgb
        srs
        rlx
         */
//        System.out.println(solitaire.crypter("hello", solitaire.getPaquet_de_depart()));
//        System.out.println(solitaire.crypter("bjr", solitaire.getPaquet_de_depart()));
//        System.out.println(solitaire.crypter("oui", solitaire.getPaquet_de_depart()));
//        System.out.println(solitaire.crypter("non", solitaire.getPaquet_de_depart()));
//        solitaire.setClefDeBase(paquet.melanger());
//        System.out.println(solitaire.crypter("hello", solitaire.getPaquet_de_depart()));
//        System.out.println(solitaire.crypter("hello", solitaire.getPaquet_de_depart()));
//        System.out.println(solitaire.crypter("bjr", solitaire.getPaquet_de_depart()));




//
        mainView mainView = new mainView(paquet, solitaire);
        mainView.pack();
        mainView.setVisible(true);
    }
}