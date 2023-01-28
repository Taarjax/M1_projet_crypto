package org.project;

import org.project.enumeration.Couleurs;
import org.project.enumeration.Jokers;
import org.project.enumeration.Valeurs;
import org.project.models.Carte;
import org.project.models.Paquet_de_cartes;
import org.project.models.Solitaire;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Paquet_de_cartes paquet = new Paquet_de_cartes();
        Solitaire solitaire = new Solitaire(paquet);
        paquet.remplir_paquet_de_carte();

        //Affiche les cartes du paquet
        System.out.println("Taille du paquet : " + paquet.getSize());
        System.out.println("--------------------------------");
//
//        paquet.melanger();
//        System.out.println(paquet);
//
//        solitaire.double_coupe();
        System.out.println(paquet);

        Carte six_de_pique = new Carte(Couleurs.PIQUE, Valeurs.ROI, null);
        System.out.println(six_de_pique.getValeurSelonOrdreBridge());
        Carte joker_noir = new Carte(null, null, Jokers.JOKER_NOIR);
        System.out.println(joker_noir.getValeurSelonOrdreBridge());






    }
}