package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    //---------------------AJOUT-----------------------------------
    /**
     * Ajoute un nouveau neighbour
     */
    @Test
    public void addNewNeighbourWithSuccess(){
        List<Neighbour> neighbours = service.getNeighbours();
        service.getNewNeighbour();
        List<Neighbour> neighbours2 = service.getNeighbours();
        assertEquals(neighbours.size(), neighbours2.size(), 1);
    }

    /**
     * change le status d'un neighbour pas en favoris
     */
    @Test
    public void changeNeighbourToFavoriteWithSuccess() {
        Neighbour neighbourToFavorite = service.getNeighbours().get(0);
        neighbourToFavorite.setFavorite(false);
        service.makeFavoriteNeighbour(neighbourToFavorite, true);
        Boolean isFavoriteNeighbour = neighbourToFavorite.getFavorite();
        assertEquals(true, isFavoriteNeighbour);
    }

    /**
     * change le status d'un neighbour en favoris
     */
    @Test
    public void changeNeighbourToNotFavoriteWithSuccess() {
        Neighbour neighbourToFavorite = service.getNeighbours().get(0);
        neighbourToFavorite.setFavorite(true);
        service.makeFavoriteNeighbour(neighbourToFavorite, false);
        Boolean isFavoriteNeighbour = neighbourToFavorite.getFavorite();
        assertEquals(false, isFavoriteNeighbour);
    }

    /**
     * Ajoute un neighbour en favoris
     */
    @Test
    public void getFavoriteNeighboursWithSuccess() {
        // on récupère les neighbours en favoris
        List<Neighbour> neighbours = service.getFavoriteNeighbour();
        //on vérifie que la liste ne contient que des neighbour en favoris
        assertThat(neighbours, hasItem(hasProperty("favorite", is(true))));
    }
    //--------------------------------------------------------
}
