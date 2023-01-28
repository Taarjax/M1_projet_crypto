package org.project.models;
import org.project.enumeration.Couleurs;
import org.project.enumeration.Jokers;
import org.project.enumeration.Valeurs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Paquet_de_cartes {
    private ArrayList<Carte> paquet_de_carte;

    public Paquet_de_cartes(){
        this.paquet_de_carte = new ArrayList<>();
    }

    /**
     * Méthode qui rempli le paquet de carte a partir de la classe Carte
     */
    public void remplir_paquet_de_carte(){
        //Ajout de toutes les cartes (SAUF JOKER)
        for(Couleurs couleur : Couleurs.values()){
            for(Valeurs valeur : Valeurs.values()){
                paquet_de_carte.add(new Carte(couleur, valeur, null));
            }
        }
        //Ajout des deux jokers
        paquet_de_carte.add(new Carte(null, null, Jokers.JOKER_NOIR));
        paquet_de_carte.add(new Carte(null, null, Jokers.JOKER_ROUGE));
    }

    /**
     * Méthode pour ajouter une carte au paquet
     * @param _carte la carte a ajouter
     */
    public void ajouter_carte(Carte _carte){
        paquet_de_carte.add(_carte);
    }

    public void ajouterPaquet(Paquet_de_cartes _paquet){
        for(Carte carte: _paquet.getPaquet_de_carte()){
            this.paquet_de_carte.add(carte);
        }
    }

    /**
     * Méthode pour vider le paquet de carte
     */
    public void vider(){
        this.paquet_de_carte.clear();
    }

    /**
     * Méthode de mélange du paquet
     */
    public void melanger(){
        Random random = new Random();
        for (int i = 0 ; i < paquet_de_carte.size(); i++){
            Collections.swap(paquet_de_carte, i, random.nextInt(paquet_de_carte.size()));
        }
    }

    /**
     * Méthode poura voir les informations du paquet de carte sous forme de chaine de caractères
     * @return la chaine de caractères
     */
    public String toString(){
        String str = "";
        for (Carte carte : paquet_de_carte) {
            str += carte.toString() + "\n";
        }
        return str;
    }

    /** GET AND SETTER */
    public ArrayList<Carte> getPaquet_de_carte(){
        return this.paquet_de_carte;
    }

    public void setPaquet_de_carte(ArrayList<Carte> _paquet){
        this.paquet_de_carte = _paquet;
    }

    /**
     * Méthode retournant la taille du paquet de carte
     * @return la taille du paquet
     */
    public int getSize(){
        return this.paquet_de_carte.size();
    }

    /**
     * Méthode pour obtenir une carte spécifique
     * @param index index de la carte
     * @return La carte demandée
     */
    public Carte getCarte(int index) {
        if (index < 0 || index >= paquet_de_carte.size()) {
            throw new IllegalArgumentException("L'index de la carte doit être compris entre 0 et la taille du paquet de cartes - 1.");
        }
        return paquet_de_carte.get(index);
    }

    /**
     * Méthode pour avoir la position du joker rouge dans le paquet
     * @return la position du joker rouge
     */
    public int getIndexJokerRouge(){
        for(int i=0; i < this.getSize() ;i++ ){
            if(this.getCarte(i).estJokerRouge()){
                return i ;
            }
        }
        return -1;
    }

    /**
     * Méthode pour avoir la position du joker noir dans le paquet
     * @return la position du joker noir
     */
    public int getIndexJokerNoir(){
        for(int i=0; i < this.getSize() ;i++ ){
            if(this.getCarte(i).estJokerNoir()){
                return i ;
            }
        }
        return -1;
    }

    /**
     * Méthode pour avoir la dernière carte du paquet
     * @return la dernière carte du paquet
     */
    public Carte getDerniereCarte(){
        return this.getCarte(this.getSize()-1);
    }

    /**
     * Méthode pour avoir la position du premier joker dans le paquet
     * @return la position du premier joker
     */
    public int getIndexPremierJoker(){
        return getIndexJokerNoir() < getIndexJokerRouge() ? getIndexJokerNoir() : getIndexJokerRouge();
    }

    /**
     * Méthode pour avoir la position du deuxième joker dans le paquet
     * @return la position du deuxième joker
     */
    public int getIndexSecondJoker(){
        return getIndexJokerNoir() > getIndexJokerRouge() ? getIndexJokerNoir() : getIndexJokerRouge();
    }

    /**
     * Méthode qui retourne le paquet au dessus du premier joker
     * @return le paquet au dessus du premier joker
     */
    public Paquet_de_cartes getPaquetAuDessusPremierJoker(){
        Paquet_de_cartes paquetAuDessusPremierJoker = new Paquet_de_cartes();
        for(int i = 0; i < this.getIndexPremierJoker() ; i++){
            paquetAuDessusPremierJoker.ajouter_carte(this.getCarte(i));
        }
        return paquetAuDessusPremierJoker;
    }

    /**
     * Méthode qui retourne le paquet en dessous du second joker
     * @return le paquet en dessous du second joker
     */
    public Paquet_de_cartes getPaquetEnDessousSecondJoker(){
        Paquet_de_cartes paquetEnDessousSecondJoker = new Paquet_de_cartes();
        for(int i = this.getIndexSecondJoker() + 1 ; i < this.getSize(); i++){
            paquetEnDessousSecondJoker.ajouter_carte(this.getCarte(i));
        }
        return paquetEnDessousSecondJoker;
    }

    /**
     * Méthode qui retourne le paquet entre les deux jokers
     * @return le paquet entre les deux jokers
     */
    public Paquet_de_cartes getPaquetEntreJokers(){
        Paquet_de_cartes paquetEntreJokers = new Paquet_de_cartes();
        for(int i = getIndexPremierJoker() ; i <= getIndexSecondJoker() ; i++){
            paquetEntreJokers.ajouter_carte(this.getCarte(i));
        }
        return paquetEntreJokers;
    }

    /**
     * Surcharge de la méthode equals pour comparer non pas la réference mais la valeur de l'objet
     * @param o l'objet a comparer
     * @return true si la valeur des deux objets sont égaux, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;
        if(!(o instanceof Paquet_de_cartes))
            return false;
        Paquet_de_cartes paquet = (Paquet_de_cartes) o;
        return paquet_de_carte.equals(paquet.paquet_de_carte);
    }


}
