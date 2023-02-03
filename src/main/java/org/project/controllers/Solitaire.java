package org.project.controllers;

import org.project.models.Carte;
import org.project.models.Paquet_de_cartes;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe qui représente le chiffrement solitaire
 */
public class Solitaire {
    private Paquet_de_cartes paquet;
    private String clef_de_cryptage = "";

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
     * Méthode qui coupe le paquet en deux paquets
     * On prend les n première carte du paquet, avec n étant la valeur suivant l'ordre du bridge de la dernière carte
     * On met ces n cartes en dessous du paquet à l'exclusion de la dernière carte que nous gardons à la même place
     */
    public void coupe_simple() {
        Carte derniereCarte = this.paquet.getDerniereCarte();
        int valeurBridgeDerniereCarte = derniereCarte.getValeurSelonOrdreBridge();
        Paquet_de_cartes paquetNCartes = new Paquet_de_cartes();
        Paquet_de_cartes paquetRestant = new Paquet_de_cartes();

        //On récupère les n cartes, avec n = valeurBridgeDerniereCarte
        for (int i = 0; i < valeurBridgeDerniereCarte; i++) {
            paquetNCartes.ajouter_carte(this.paquet.getPaquet_de_carte().get(i));
        }

        //On récupère le reste du paquet
        // -1 pour ne pas prendre la derniere carte
        for (int i = valeurBridgeDerniereCarte ; i < this.paquet.getSize() - 1; i++) {
            paquetRestant.ajouter_carte(this.paquet.getPaquet_de_carte().get(i));
        }

        //On vide le paquet
        this.paquet.vider();

        //On le remplie avec le nouvel ordre
        this.paquet.ajouterPaquet(paquetRestant);
        this.paquet.ajouterPaquet(paquetNCartes);
        this.paquet.ajouter_carte(derniereCarte);
    }

    /**
     * Méthode pour crypter une lettre
     */
    public char lecture_lettre() throws Exception {
        Carte premiereCarte = this.paquet.getPremiereCarte();
        int valeurBridgePremiereCarte = premiereCarte.getValeurSelonOrdreBridge();

        Carte carteM = this.paquet.getPaquet_de_carte().get(valeurBridgePremiereCarte);
        int valeurBridgeCarteM = carteM.getValeurSelonOrdreBridge();

        if (valeurBridgeCarteM == 53) {
            this.recul_joker_noir();
            this.recul_joker_rouge();
            this.double_coupe();
            this.coupe_simple();
            return this.lecture_lettre();
        }else{
            if (valeurBridgeCarteM > 26) {
                valeurBridgeCarteM = valeurBridgeCarteM - 26;
            }
        }

        return paquet.convertBridgeToLetter(valeurBridgeCarteM);
    }

    /**
     * Méthode pour génerer la clef de cryptage
     */
    public String generer_clef(int tailleMessage) throws Exception {;
        String clef = "";

        //Pour chaque lettre du message, on fait le mélange pseudo aléatoire et on récupère la lettre correspondante
        for (int i = 0; i < tailleMessage; i++) {
            this.recul_joker_noir(); // good
            this.recul_joker_rouge(); // good
            this.double_coupe(); //good
            this.coupe_simple(); // good
            clef += this.lecture_lettre();
        }
        this.clef_de_cryptage = clef;
        System.out.println("Clef de cryptage: " + this.clef_de_cryptage);
        return this.clef_de_cryptage;
    }

    /**
     * Méthode pour crypter un message
     */
    public String crypter(String message) throws Exception {
        System.out.println("Message à crypter: " + message);

        message = message.toLowerCase();
        message = message.replaceAll("[\\sàâäéèêëîïôöùûü]", "");

        String clef = this.generer_clef(message.length());
        String messageCrypte = "";

        for (int i = 0; i < message.length(); i++) {
            int valeurLettreMessage = this.paquet.convertLetterToBridge(message.charAt(i));
            int valeurLettreClef = this.paquet.convertLetterToBridge(clef.charAt(i));
            int valeurLettreCrypte = (valeurLettreMessage + valeurLettreClef) > 26 ? (valeurLettreMessage + valeurLettreClef) - 26 : (valeurLettreMessage + valeurLettreClef);
            messageCrypte += this.paquet.convertBridgeToLetter(valeurLettreCrypte);
        }
        return messageCrypte;
    }

    /**
     * Méthode pour décrypter un message
     */
    public String decrypter(String message) throws Exception {
        String clef = this.clef_de_cryptage;
        String messageDecrypte = "";

        for (int i = 0; i < message.length(); i++) {
            int valeurLettreMessage = this.paquet.convertLetterToBridge(message.charAt(i));
            int valeurLettreClef = this.paquet.convertLetterToBridge(clef.charAt(i));
            int valeurLettreDecrypte = (valeurLettreMessage - valeurLettreClef) < 1 ? (valeurLettreMessage - valeurLettreClef) + 26 : (valeurLettreMessage - valeurLettreClef);
            messageDecrypte += this.paquet.convertBridgeToLetter(valeurLettreDecrypte);
        }
        return messageDecrypte;
    }

}

