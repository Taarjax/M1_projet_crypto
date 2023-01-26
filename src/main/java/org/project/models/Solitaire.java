package org.project.models;

import java.util.ArrayList;
import java.util.Collections;

public class Solitaire {
    private Paquet_de_cartes paquet;

    public Solitaire(Paquet_de_cartes _paquet){
        this.paquet = _paquet;
    }

    public void recul_joker_noir(){
        // Recherche de la position du joker noir
        int positionJokerNoir = -1;
        for(int i = 0; i < paquet.getSize(); i++){
            if(paquet.getCarte(i).estJokerNoir()){
                positionJokerNoir = i;
                break;
            }
        }

        // Permutation du joker noir avec la carte précédente
        // Si il est a la derniere position du paquet il passe derriere la premiere carte du paquet
        if(positionJokerNoir == paquet.getSize() - 1){
            Collections.swap(paquet.getPaquet_de_carte(), positionJokerNoir, paquet.getPaquet_de_carte().indexOf(paquet.getCarte(1)));
        }else {
            // Sinon on le permute avec la carte juste derriere lui
            Collections.swap(paquet.getPaquet_de_carte(), positionJokerNoir, positionJokerNoir+1);
        }

    }
}
