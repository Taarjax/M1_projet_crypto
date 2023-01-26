package org.project;

import org.project.enumeration.Couleurs;
import org.project.enumeration.Valeurs;
import org.project.models.Carte;
import org.project.models.Paquet_de_cartes;
import org.project.models.Solitaire;

public class Main {
    public static void main(String[] args) {
        Paquet_de_cartes paquet = new Paquet_de_cartes();
        Solitaire solitaire = new Solitaire(paquet);
        paquet.remplir_paquet_de_carte();

        //Affiche les cartes du paquet
        System.out.println(paquet);
        System.out.println("Taille du paquet : " + paquet.getSize());
        System.out.println("--------------------------------");

        solitaire.recul_joker_noir();
        System.out.println(paquet);
        solitaire.recul_joker_noir();
        System.out.println(paquet);


    }
}