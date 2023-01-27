package org.project.models;

import java.util.ArrayList;
import java.util.Collections;

public class Solitaire {
    private Paquet_de_cartes paquet;

    public Solitaire(Paquet_de_cartes _paquet) {
        this.paquet = _paquet;
    }

    /**
     * Méthode qui fais reculer le joker noir
     * Si celui-ci se trouve à la dernière position du paquet, il passe en haut du paquet et est permuté avec la seconde
     * carte, c'est à dire qu'il se retrouve en deuxieme positoon
     * Sinon on le permute avec la carte suivante
     */
    public void recul_joker_noir() {
        // Recherche de la position du joker noir
        int positionJokerNoir = -1;
        int positionDerniereCartePaquet = paquet.getSize() - 1;

        positionJokerNoir = paquet.getIndexJokerNoir();

        if (positionJokerNoir == positionDerniereCartePaquet) {
            Carte jokerNoir = paquet.getPaquet_de_carte().remove(positionJokerNoir);
            paquet.getPaquet_de_carte().add(1, jokerNoir);
        } else {
            // Sinon on le permute avec la carte juste derriere lui
            Collections.swap(paquet.getPaquet_de_carte(), positionJokerNoir, positionJokerNoir + 1);
        }
    }

    /**
     * Méthode qui fais reculer le joker rouge
     * S'il se trouve en dernière position alors il passe en troisième position
     * S'il se trouve en avant derniere position alors il passe en deuxième position
     * Sinon on le recule de 2 cartes
     */
    public void recul_joker_rouge() {
        int positionJokerRouge = -1;
        int positionDerniereCarte = paquet.getSize() - 1;
        int positionAvantDerniereCarte = paquet.getSize() - 2;

        positionJokerRouge = paquet.getIndexJokerRouge();

        if (positionJokerRouge == positionDerniereCarte) {
            Carte jokerRouge = paquet.getPaquet_de_carte().remove(positionJokerRouge);
            paquet.getPaquet_de_carte().add(2, jokerRouge);
        } else if (positionJokerRouge == positionAvantDerniereCarte) {
            Carte jokerRouge = paquet.getPaquet_de_carte().remove(positionJokerRouge);
            paquet.getPaquet_de_carte().add(1, jokerRouge);
        } else {
            Carte jokerRouge = paquet.getPaquet_de_carte().remove(positionJokerRouge);
            paquet.getPaquet_de_carte().add(positionJokerRouge + 2, jokerRouge);
        }
    }


    public void double_coupe() {
        int positionJokerNoir = paquet.getIndexJokerNoir();
        int positionJokerRouge = paquet.getIndexJokerRouge();
        int positionPremierJoker = positionJokerNoir < positionJokerRouge ? positionJokerNoir : positionJokerRouge;
        int positionSecondJoker = positionJokerNoir > positionJokerRouge ? positionJokerNoir : positionJokerRouge;
        Paquet_de_cartes paquet_au_dessus_premier_joker = new Paquet_de_cartes();
        Paquet_de_cartes paquet_en_dessous_second_joker = new Paquet_de_cartes();

        // On remplie le sous paquet de cartes au-dessus du premier joker
        for(int i = 0; i < positionPremierJoker ; i++){
            paquet_au_dessus_premier_joker.ajouter_carte(paquet.getCarte(i));
        }

        //On remplie le sous paquet de carte en-dessous du second joker
        for(int i = positionSecondJoker +1 ; i < paquet.getSize(); i++){
            paquet_en_dessous_second_joker.ajouter_carte(paquet.getCarte(i));
        }

//        System.out.println("paquet au dessus" +paquet_au_dessus_premier_joker);
//        System.out.println("paquet en dessous" + paquet_en_dessous_second_joker);

    }

}
