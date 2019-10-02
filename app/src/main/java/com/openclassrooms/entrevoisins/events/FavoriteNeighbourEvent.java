package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class FavoriteNeighbourEvent {

    /**
     * neighbour selected to show detail
     */

    public Neighbour neighbour;
    public Boolean b;

    /**
     * Constructor.
     *
     * @param neighbour
     * @param b
     */

    public FavoriteNeighbourEvent(Neighbour neighbour, boolean b) {
        this.neighbour = neighbour;
        this.b= b;

    }
}
