package com.openclassrooms.entrevoisins.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

import static com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator.FAKE_DUMMY_NEIGHBOURS;

/**
 * Model object representing a Neighbour
 */
//seul les types primitifs sont acceptés en tant qu’Extra d’un intent pour envoyé les données dans la nouvelle activité : passer nos objets en tant que Serializable
public class Neighbour {

    /** Identifier */
    private Integer id;

    /** Full name */
    private String name;

    /** Avatar */
    private String avatarUrl;

    //---------------------------------------------------------------------------
    /** A propos de moi */
    private String aboutMe;

    /** Neighbour Adress */
    private String adress;

    /** Neighbour phone */
    private String phone;

    /** Neighbour web Site */
    private String webSite;

    /** Neighbour is favorite */
    private boolean favorite;

    //---------------------------------------------------------------------------

    /**
     * Constructor
     * @param id
     * @param name
     * @param avatarUrl
     * @param aboutMe
     * @param adress
     * @param phone
     * @param webSite
     * @param favorite
     *
     */
    public Neighbour(Integer id, String name, String avatarUrl, String aboutMe, String adress, String phone, String webSite, boolean favorite) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.aboutMe = aboutMe;
        this.adress = adress;
        this.phone = phone;
        this.webSite = webSite;
        this.favorite = favorite;
    }


    public String getAboutMe(){
        return aboutMe;
    }

    public void setAboutMe(){
        this.aboutMe = aboutMe;
    }

    public String getAdress(){
        return adress;
    }

    public void setAdress(String adress){
        this.adress = adress;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getWebSite(){
        return webSite;
    }

    public void setWebSite(){
        this.webSite = webSite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean getFavorite() {
        return favorite;
    }

    /**
     * Generate random user
     */
    public static Neighbour random(){
        return FAKE_DUMMY_NEIGHBOURS.get(new Random().nextInt(FAKE_DUMMY_NEIGHBOURS.size()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
