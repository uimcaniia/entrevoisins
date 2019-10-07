package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    //***********************************************************************************
    private List<Neighbour> mFavoriteNeighbours;
    //***********************************************************************************

    /**
     * {@inheritDoc}
     * retourne une liste factice de voisins généré dans DummyNeighbourGenerator.java
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour){
        neighbours.remove(neighbour);
    }

    //--------------------------------------------------------

    /**
     * ajoute ou retire le voisin en tant que favorite
     */
    @Override
    public void makeFavoriteNeighbour(Neighbour neighbour, boolean isFavorite) {
        if(neighbour.getFavorite() != isFavorite){
            neighbour.setFavorite(isFavorite);
        }
    }
    /**
     * ajoute un voisin
     */
    @Override
    public void getNewNeighbour () {
        neighbours.add(Neighbour.random());
    }

    /**
     * retourne la liste des voisins mit en favoris
     */
    @Override
    public List<Neighbour> getFavoriteNeighbour() {
        mFavoriteNeighbours = new ArrayList<>();
        int nbr = neighbours.size();
        for(int i = 0 ; i < nbr ; i++){
            boolean b = neighbours.get(i).getFavorite();
            if(b == true) {
                mFavoriteNeighbours.add(neighbours.get(i));
            }
        }
        return mFavoriteNeighbours;
    }

    //--------------------------------------------------------

}
