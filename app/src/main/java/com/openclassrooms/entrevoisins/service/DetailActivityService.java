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

    public String getPhoneNumberFormat(String strPhone) {
        StringBuffer sb = new StringBuffer();
        // on ajoute un espace tout les 2 chiffre du numéro de téléphone
        for (int i = 0; i < strPhone.length(); i += 2) {
            sb.append(strPhone.substring(i, i + 2));
            sb.append(" ");
        }
        // remplacement du "0" du début du téléphone par '33'
        String strFormatPhone = sb.substring(0, 0) + "+33 " + sb.substring(1, 14);
        return strFormatPhone;
    }

    public Neighbour getNeighbourSelectedWithGoodId(){
        for (int i = 0 ; i < neighbours.size() ; i++){
            if(neighbours.get(i).getId() == this.neighbourSelectedId){
                neighbourSelected = neighbours.get(i);
            }
        }return neighbourSelected;
    }

    public void  refreshFavoritNeighbour(boolean boolFav, boolean boolFavInitial){
        if(boolFav != boolFavInitial){
            mApiService.makeFavoriteNeighbour(neighbourSelected, boolFav);
        }
    }


}
