package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

public class DetailActivityService{

    private NeighbourApiService mApiService;
    private List<Neighbour> neighbours;
    private Neighbour neighbourSelected;
    private Integer neighbourSelectedId;

    public DetailActivityService(Integer intId) {
        mApiService = DI.getNeighbourApiService();
        neighbours = mApiService.getNeighbours();
        neighbourSelectedId = intId;
    }

    /**
     * Modification du format de téléphone pour passer d'un affichage type "0629493243" à "+33 06 29 49 32 43"
     * @param strPhone
     * @return
     */
    public String getPhoneNumberFormat(String strPhone) {
        StringBuffer sb = new StringBuffer(); // chaîne de caractère modifiable

        for (int i = 0; i < strPhone.length(); i += 2) {  // on ajoute un espace tout les 2 chiffres du numéro de téléphone
            sb.append(strPhone.substring(i, i + 2));
            sb.append(" ");
        }
        return sb.substring(0, 0) + "+33 " + sb.substring(1, 14); // remplacement du "0" du début du téléphone par '33'
    }

    /**
     * Parse la liste des neighbours en compararnt l' ID unique avec ceux de la liste afin de trouver et récupérer le bon neighbour
     * @return
     */
    public Neighbour getNeighbourSelectedWithGoodId(){
        for (int i = 0 ; i < neighbours.size() ; i++){
            if(neighbours.get(i).getId() == this.neighbourSelectedId){
                neighbourSelected = neighbours.get(i);
            }
        }
        return neighbourSelected;
    }

    /**
     * rafraichit le status de "favoris" d'un neighbour en comparant si sa valeur initial est différente de la nouvelle valeur
     * @param boolFav
     * @param boolFavInitial
     */
    public void  refreshFavoritNeighbour(boolean boolFav, boolean boolFavInitial){
        if(boolFav != boolFavInitial){
            mApiService.makeFavoriteNeighbour(neighbourSelected, boolFav);
        }
    }


}
