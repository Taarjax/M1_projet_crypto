package org.project.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project.enumeration.Couleurs;
import org.project.enumeration.Jokers;
import org.project.enumeration.Valeurs;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolitaireTest {
    private Solitaire solitaire;
    private Paquet_de_cartes paquet;

    /**
     * Pour les tests on ne prend pas tous le paquet pour faciliter les tests nous prenons uniquement les cartes de
     * trèfle + les 2 jokers
     */
    @BeforeEach @AfterEach
    public void init() {
        this.paquet = new Paquet_de_cartes();
        this.solitaire = new Solitaire(paquet);
        // On créer un paquet avec uniquement les carte de trèfle et les 2 jokers
        for (Couleurs couleur : Couleurs.values()) {
            for (Valeurs valeur : Valeurs.values()) {
                if (couleur == Couleurs.TREFFLE) {
                    paquet.ajouter_carte(new Carte(couleur, valeur, null));
                }
            }
        }
        paquet.ajouter_carte(new Carte(null, null, Jokers.JOKER_NOIR));
        paquet.ajouter_carte(new Carte(null, null, Jokers.JOKER_ROUGE));
    }


    @Test
    void recul_joker_noir() {
        //On test le cas lorsque le joker noir n'est pas en dernière position
        // De base il est à l'avant derniere position
        solitaire.recul_joker_noir();
        assertEquals(Jokers.JOKER_NOIR, paquet.getCarte(paquet.getSize()-1).getJoker());

        // A présent il est en dernière position, on verifie qu'il arrive a la position 2 lors du prochaine recul
        solitaire.recul_joker_noir();
        assertEquals(Jokers.JOKER_NOIR, paquet.getCarte(1).getJoker());
    }

    @Test
    void recul_joker_rouge() {
        // On vérifie que le joker rouge est bien à la dernière position avant de déplacer
        assertEquals(Jokers.JOKER_ROUGE, paquet.getCarte(paquet.getSize()-1).getJoker());
        solitaire.recul_joker_rouge();
        // On vérifie que le joker rouge est bien passé en troisième position après le déplacement
        assertEquals(Jokers.JOKER_ROUGE, paquet.getCarte(2).getJoker());

        // On place le joker rouge en avant dernière position
        Carte jokerRouge = paquet.getCarte(2);
        paquet.getPaquet_de_carte().remove(2);
        paquet.getPaquet_de_carte().add(paquet.getSize()-1, jokerRouge);

        // On vérifie que le joker rouge est bien en avant dernière position avant de déplacer
        assertEquals(Jokers.JOKER_ROUGE, paquet.getCarte(paquet.getSize()-2).getJoker());

        solitaire.recul_joker_rouge();
        // On vérifie que le joker rouge est bien passé en deuxième position après le déplacement
        assertEquals(Jokers.JOKER_ROUGE, paquet.getCarte(1).getJoker());

        //On test le cas ou le joker n'est pas en dernière ou avant dernière position
        solitaire.recul_joker_rouge();
        assertEquals(Jokers.JOKER_ROUGE, paquet.getCarte(3).getJoker());
    }
}