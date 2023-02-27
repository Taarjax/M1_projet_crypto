package org.project.models;

import org.project.enumeration.Couleurs;
import org.project.enumeration.Jokers;
import org.project.enumeration.Valeurs;

/**
 * Classe qui représente une carte
 */
public class Carte {
    private Couleurs couleur;
    private Valeurs valeur;
    private Jokers joker;

    /**
     * Constructeur de la classe
     * @param _couleur couleur de la classe
     * @param _valeur valeur de la carte (Roi de trèfle, As de pique, ...) suivant l'ordre du bridge
     */
    public Carte(Couleurs _couleur, Valeurs _valeur, Jokers _joker){
        this.couleur = _couleur;
        this.valeur = _valeur;
        this.joker = _joker;
    }

    /**
     * Méthode qui renvoie la couleur de la carte
     * @return la couleur de la carte
     */
    @Override
    public String toString(){
        String str;
        // Si ce n'est pas un joker
        if(this.couleur != null && this.valeur != null){
             str = valeur + " de " + couleur;
        }else{ // Joker
          str = "" + joker;
        }
        return str;
    }


    /**
     * GETTER AND SETTER
     */

    /**
     * Renvoie la couleur de la carte
     * @return la couleur de la carte
     */
    public Jokers getJoker(){return this.joker;}

    /**
     * Renvoie la valeur de la carte selon l'ordre du bridge
     * @return la valeur de la carte selon l'ordre du bridge
     */
    public int getValeurSelonOrdreBridge() {
        int valeur = 0;
        if(this.joker == null) {
            valeur = 0;
            switch (this.couleur) {
                case TREFLE:
                    valeur = this.valeur.ordinal() + 1;
                    break;
                case CARREAU:
                    valeur = this.valeur.ordinal() + 14;
                    break;
                case COEUR:
                    valeur = this.valeur.ordinal() + 27;
                    break;
                case PIQUE:
                    valeur = this.valeur.ordinal() + 40;
                    break;
            }
        }else {
            if(this.joker == Jokers.JOKER_NOIR || this.joker == Jokers.JOKER_ROUGE) {
                valeur = 53;
            }
        }
        return valeur;
    }

    /**
     * Vérifie si la carte est un joker noir
     * @return true si c'est un joker noir, false sinon
     */
    public boolean estJokerNoir(){
        return this.joker == Jokers.JOKER_NOIR;
    }

    /**
     * Vérifie si la carte est un joker rouge
     * @return true si c'est un joker rouge, false sinon
     */
    public boolean estJokerRouge(){
        return this.joker == Jokers.JOKER_ROUGE;
    }

    /**
     * Retourne la valeur de la carte
     * @return la valeur de la carte
     */
    public Valeurs getValeur() {
        return valeur;
    }

    /**
     * Retourne la couleur de la carte
     * @return la couleur de la carte
     */
    public Couleurs getCouleur() {
        return couleur;
    }




}
