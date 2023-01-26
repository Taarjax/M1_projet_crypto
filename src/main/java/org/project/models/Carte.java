package org.project.models;

import org.project.enumeration.Couleurs;
import org.project.enumeration.Jokers;
import org.project.enumeration.Valeurs;

/**
 * Classe représentant une carte du paquet
 */
public class Carte {
    private Couleurs couleur;
    private Valeurs valeur;
    private Jokers joker;

    /**
     * Constrcteur de la classe
     * @param _couleur couleur de la classe
     * @param _valeur valeur de la carte ( Roi de trèfle, As de pique, ...) suivant l'ordre du bridge
     */
    public Carte(Couleurs _couleur, Valeurs _valeur, Jokers _joker){
        this.couleur = _couleur;
        this.valeur = _valeur;
        this.joker = _joker;
    }


    @Override
    public String toString(){
        String str = "";
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
    public Couleurs getCouleur(){
        return this.couleur;
    }

    public void setCouleur(Couleurs _couleur){
        this.couleur = _couleur;
    }

    public Valeurs getValeur(){
        return this.valeur;
    }

    public void setValeur(Valeurs _valeur){
        this.valeur = _valeur;
    }

    public Jokers getJoker(){return this.joker;}

    public int getValeurNumerique() {
        return this.valeur.ordinal() + 1;
    }

    public boolean estJokerNoir(){
        return this.joker == Jokers.JOKER_NOIR;
    }

    public boolean estJokerRouge(){
        return this.joker == Jokers.JOKER_ROUGE;
    }

}
