package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user deletes a Neighbour
 */
public class FavoriteNeighbourEvent {

    /** neighbour selected to show detail  */
    public Neighbour neighbour;
    public Boolean b;

    /**
     * Constructor.
     * @param neighbour neighbour sélectionné
     * @param b status favoris ou non
     */
    public FavoriteNeighbourEvent(Neighbour neighbour, boolean b) {
        this.neighbour = neighbour;
        this.b= b;
    }
}
