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

        //Affiche les cartes du paquet
        System.out.println("Taille du paquet : " + paquet.getSize());
        System.out.println("--------------------------------");

        //
//        System.out.println(paquet);
//
//        solitaire.double_coupe();
//        System.out.println(paquet);0
//        solitaire.coupe_simple();
//
//        System.out.println(paquet);
//
//        System.out.println(paquet.getDerniereCarte().getValeurSelonOrdreBridge());

        System.out.println(paquet);

//        solitaire.recul_joker_noir();
//        System.out.println("Paquet apres manip joker noir" + "\n"+ paquet);
//        solitaire.recul_joker_rouge();
//        System.out.println("Paquet apres manip joker rouge" + "\n"+ paquet);
//        solitaire.double_coupe();
//        System.out.println("Paquet apres manip double coupe" + "\n"+ paquet);
//        solitaire.coupe_simple();
//        System.out.println("Paquet apres manip coupe simple" + "\n"+ paquet);

//        Carte dame_de_coeur = new Carte(Couleurs.COEUR, Valeurs.DAME, null);
//        System.out.println(dame_de_coeur.getValeurSelonOrdreBridge());
//
//        Carte roi_de_pique = new Carte(Couleurs.PIQUE, Valeurs.ROI, null);
//        System.out.println(roi_de_pique.getValeurSelonOrdreBridge());
//
//        Carte joker_rouge = new Carte(null, null, Jokers.JOKER_ROUGE);
//        System.out.println(joker_rouge.getValeurSelonOrdreBridge());
//
//        Carte joker_noir = new Carte(null, null, Jokers.JOKER_NOIR);
//        System.out.println(joker_noir.getValeurSelonOrdreBridge());

        String msg = "hello";
        String msg_crypté = solitaire.crypter(msg);
        System.out.println("Message crypté : " + msg_crypté);
        String msg_decrypté = solitaire.decrypter(msg_crypté);
        System.out.println("Message decrypté : " + msg_decrypté);

    }
}