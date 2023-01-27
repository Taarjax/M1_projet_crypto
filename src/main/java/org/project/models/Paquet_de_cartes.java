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

    public void ajouter_carte(Carte c){
        paquet_de_carte.add(c);
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



}
