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


    /**
     * Méthode intervertissant le paquet situé au dessus du premier joker avec le paquet
     * situé en dessous du second joker
     */
    public void double_coupe() {
        //On récupère les paquets avant, entre et au dessus des jokers
        Paquet_de_cartes paquet_au_dessus_premier_joker = this.paquet.getPaquetAuDessusPremierJoker();
        Paquet_de_cartes paquetEntreJokers = this.paquet.getPaquetEntreJokers();
        Paquet_de_cartes paquet_en_dessous_second_joker = this.paquet.getPaquetEnDessousSecondJoker();

        //On vide le paquet
        this.paquet.vider();

        //On le remplie avec le nouvel ordre
        paquet.ajouterPaquet(paquet_en_dessous_second_joker);
        paquet.ajouterPaquet(paquetEntreJokers);
        paquet.ajouterPaquet(paquet_au_dessus_premier_joker);
    }

    /**
     * Méthode qui permute la carte en haut du paquet avec la carte située à la position
     * indiquée par la valeur de la carte en haut du paquet
     */
}
