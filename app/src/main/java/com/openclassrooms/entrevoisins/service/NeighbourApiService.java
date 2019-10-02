package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    //-----------------------------------------------
    /**
     * ajoute ou retire le voisin en tant que favorite
     * @param neighbour
     * @param isFavorite
     */
    void makeFavoriteNeighbour(Neighbour neighbour, boolean isFavorite);

    /**
     * ajoute un voisin
     */
    void getNewNeighbour ();

    /**
     * récupère tous les neighbours favorite
     * @return
     */
    List<Neighbour> getFavoriteNeighbour();


    //-----------------------------------------------

}
