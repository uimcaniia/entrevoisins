package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;




public class DetailNeighbourEvent {

    /** neighbour selected to show detail*/

    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */

    public DetailNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;

    }

}
