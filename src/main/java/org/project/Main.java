package org.project;

import org.project.enumeration.Couleurs;
import org.project.enumeration.Jokers;
import org.project.enumeration.Valeurs;
import org.project.models.Carte;
import org.project.models.Paquet_de_cartes;
import org.project.models.Solitaire;

import java.util.Collections;

public class Main {
    public static void main(String[] args) throws Exception {
        Paquet_de_cartes paquet = new Paquet_de_cartes();
        Solitaire solitaire = new Solitaire(paquet);
        paquet.remplir_paquet_de_carte();


        System.out.println(paquet);
        //Affiche les cartes du paquet
        System.out.println("Taille du paquet : " + paquet.getSize());
        System.out.println("--------------------------------");


        String msg = "salutcava";
        String msg_crypté = solitaire.crypter(msg);
        System.out.println("Message crypté : " + msg_crypté);
        String msg_decrypté = solitaire.decrypter(msg_crypté);
        System.out.println("Message decrypté : " + msg_decrypté);

    }
}