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
        paquet.melanger();
//        System.out.println(paquet);
//
//        solitaire.double_coupe();
//        System.out.println(paquet);
//        solitaire.coupe_simple();
//
//        System.out.println(paquet);
//
//        System.out.println(paquet.getDerniereCarte().getValeurSelonOrdreBridge());


        //String msg_a_crytpé = "Bonjour";
        //String msg_crypté = solitaire.crypter(msg_a_crytpé);




    }
}