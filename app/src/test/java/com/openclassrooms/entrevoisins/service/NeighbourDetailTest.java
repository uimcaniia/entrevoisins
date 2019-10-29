package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.DetailNeighbourActivity;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NeighbourDetailTest {
    /**
     * TEST DANS LA DEUXIEME ACTIVITé
     * VUE DE DETAIL DU NEIGHBOUR
     */
    private DetailActivityService mActivityService;
    private NeighbourApiService mApiService;

    @Before
    public void setup() {
        mApiService = DI.getNeighbourApiService();
        mActivityService = new DetailActivityService(1);
    }

    /**
     * change le format du numùéro de téléphone
     */
    @Test
    public void transformedNumberPhone_InGoodFormat(){
        //on passe un numéro de téléphone en format brut
        String formatPhoneNumber = mActivityService.getPhoneNumberFormat("0629493243");
        // on vérifie que le format est bien le bon à savoir : +33 6 29 49 32 43 donc 17 caractères
        assertEquals(17, formatPhoneNumber.length());
    }

    /**
     * vérifie si le bon neighbour est bien celui sélectionné
     */
    @Test
    public void getNeighbourSelected_WithGoodId_WithSuccess(){
        // on sélectionne via l'Id du voisin (qui commence par 1, initialisé dans le setup)
        Neighbour neighbour = mActivityService.getNeighbourSelectedWithGoodId();
        // on sélectionne via son emplacement dans la liste (qui commence par 0)
        Neighbour neighbour2 = mApiService.getNeighbours().get(0);
        // on compare les 2 neighbours qui sont identique => même id
        assertEquals(neighbour.getId(), neighbour2.getId());
    }

    /**
     * change le status d'un neighbour en favoris si initialement ce n'est pas le même
     */
    @Test
    public void change_FavoriteStatusNeighbour_WithSuccess_IfInitialStatusIsNotTheSame(){
        //on récupère l'object Neighbour
        Neighbour neighbour = mActivityService.getNeighbourSelectedWithGoodId();
        //on met le status a true si il n'est pas de base;
        neighbour.setFavorite(true);
        // on change son status de favorit si la valeur initial est différente de la nouvelle valeur
        mActivityService.refreshFavoritNeighbour(false, true);
        assertFalse( neighbour.getFavorite());
    }

    /**
     * change le status d'un neighbour en favoris si initialement c'est le même
     */
    @Test
    public void NoChange_FavoriteStatusNeighbour_WithSuccess_IfStatusIsTheSame(){
        //on récupère l'object Neighbour
        Neighbour neighbour = mActivityService.getNeighbourSelectedWithGoodId();
        //on met le status a true si il n'est pas de base;
        neighbour.setFavorite(true);
        // on ne change pas son status de favorit si la valeur initial est identique de la nouvelle valeur
        mActivityService.refreshFavoritNeighbour(true, true);
        assertTrue(neighbour.getFavorite());
    }

}
