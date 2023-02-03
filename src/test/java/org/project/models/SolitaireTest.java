package org.project.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project.controllers.Solitaire;
import org.project.enumeration.Couleurs;
import org.project.enumeration.Jokers;
import org.project.enumeration.Valeurs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SolitaireTest {
    private Solitaire solitaire;
    private Paquet_de_cartes paquet;

    /**
     * Pour les tests on ne prend pas tous le paquet pour faciliter les tests nous prenons uniquement les cartes de
     * trèfle + les 2 jokers
     */
    @BeforeEach
    @AfterEach
    public void init() {
        this.paquet = new Paquet_de_cartes();
        this.solitaire = new Solitaire(paquet);
        // On créer un paquet avec uniquement les carte de trèfle et les 2 jokers
        for (Couleurs couleur : Couleurs.values()) {
            for (Valeurs valeur : Valeurs.values()) {
                if (couleur == Couleurs.TREFLE) {
                    paquet.ajouter_carte(new Carte(couleur, valeur, null));
                }
            }
        }
        paquet.ajouter_carte(new Carte(null, null, Jokers.JOKER_NOIR));
        paquet.ajouter_carte(new Carte(null, null, Jokers.JOKER_ROUGE));
    }


    /**
     * Test de la méthode de déplacement du joker noir
     */
    @Test
    void test_recul_joker_noir() {
        //On test le cas lorsque le joker noir n'est pas en dernière position
        // De base il est à l'avant derniere position
        solitaire.recul_joker_noir();
        assertEquals(Jokers.JOKER_NOIR, paquet.getCarte(paquet.getSize() - 1).getJoker());

        // A présent il est en dernière position, on verifie qu'il arrive a la position 2 lors du prochaine recul
        solitaire.recul_joker_noir();
        assertEquals(Jokers.JOKER_NOIR, paquet.getCarte(1).getJoker());
    }

    /**
     * Méthode de test pour la méthode de déplacement du joker rouge
     */
    @Test
    void test_recul_joker_rouge() {
        // On vérifie que le joker rouge est bien à la dernière position avant de déplacer
        assertEquals(Jokers.JOKER_ROUGE, paquet.getCarte(paquet.getSize() - 1).getJoker());
        solitaire.recul_joker_rouge();
        // On vérifie que le joker rouge est bien passé en troisième position après le déplacement
        assertEquals(Jokers.JOKER_ROUGE, paquet.getCarte(2).getJoker());

        // On place le joker rouge en avant dernière position
        Carte jokerRouge = paquet.getCarte(2);
        paquet.getPaquet_de_carte().remove(2);
        paquet.getPaquet_de_carte().add(paquet.getSize() - 1, jokerRouge);

        // On vérifie que le joker rouge est bien en avant dernière position avant de déplacer
        assertEquals(Jokers.JOKER_ROUGE, paquet.getCarte(paquet.getSize() - 2).getJoker());

        solitaire.recul_joker_rouge();
        // On vérifie que le joker rouge est bien passé en deuxième position après le déplacement
        assertEquals(Jokers.JOKER_ROUGE, paquet.getCarte(1).getJoker());

        //On test le cas ou le joker n'est pas en dernière ou avant dernière position
        solitaire.recul_joker_rouge();
        assertEquals(Jokers.JOKER_ROUGE, paquet.getCarte(3).getJoker());
    }

    /**
     * Méthode de test pour la méthode double_coupe
     */
    @Test
    void test_double_coupe() {
        paquet.melanger();

        Paquet_de_cartes paquetAuDessusPremierJokerAvantCoupe = this.paquet.getPaquetAuDessusPremierJoker();
        Paquet_de_cartes paquetEnDessousDeuxiemeJokerAvantCoupe = this.paquet.getPaquetEnDessousSecondJoker();

        solitaire.double_coupe();

        //On récupère les paquets après la double coupe
        Paquet_de_cartes paquetAuDessusPremierJokerApresCoupe = paquet.getPaquetAuDessusPremierJoker();
        Paquet_de_cartes paquetEnDessousSecondJokerApresCoupe = paquet.getPaquetEnDessousSecondJoker();

        //On vérifie que le paquet au dessus du premier joker avant la coupe est égal au paquet en dessous du second joker après la coupe
        //Et que le paquet en dessous du second joker avant la coupe est égal au paquet au dessus du premier joker après la coupe
        assertEquals(paquetAuDessusPremierJokerAvantCoupe, paquetEnDessousSecondJokerApresCoupe );
        assertEquals(paquetEnDessousDeuxiemeJokerAvantCoupe, paquetAuDessusPremierJokerApresCoupe);
    }

    /**
     * Méthode de test pour la méthode coupe_simple
     */
    @Test
    void test_coupe_simple(){
        paquet.melanger();

        Carte derniereCarte = paquet.getDerniereCarte();
        int valeurBridgeDerniereCarte = derniereCarte.getValeurSelonOrdreBridge();

        Paquet_de_cartes paquetNCartes = new Paquet_de_cartes();
        Paquet_de_cartes paquetRestant = new Paquet_de_cartes();

        solitaire.coupe_simple();

        //On récupère les n cartes, avec n = valeurBridgeDerniereCarte
        for (int i = 0; i <= valeurBridgeDerniereCarte; i++) {
            paquetNCartes.ajouter_carte(paquet.getPaquet_de_carte().get(i));
        }

        //On récupère le reste du paquet
        // -1 pour ne pas prendre la derniere carte
        for (int i = valeurBridgeDerniereCarte + 1; i < paquet.getSize() - 1 ; i++) {
            paquetRestant.ajouter_carte(paquet.getPaquet_de_carte().get(i));
        }

        System.out.println(paquet);
        //On vérifie que le paquet n cartes est égal au n premieres cartes du paquet
        assertEquals(paquetNCartes.getPaquet_de_carte(), paquet.getPaquet_de_carte().subList(0, valeurBridgeDerniereCarte + 1));
        //On vérifie que paquetRestant est égal au reste du paquet
        assertEquals(paquetRestant.getPaquet_de_carte(), paquet.getPaquet_de_carte().subList(valeurBridgeDerniereCarte + 1, paquet.getSize() - 1));
        //On vérifie que la derniere carte du paquet est égale à la derniere carte du paquet avant la coupe
        assertEquals(derniereCarte, paquet.getPaquet_de_carte().get(paquet.getSize() - 1));
    }



}