package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.support.design.widget.FloatingActionButton;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DetailActivityService;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

public class DetailNeighbourActivity extends AppCompatActivity{

    private FloatingActionButton mBtnFavorite;
    private DetailActivityService mActivityService;

    String strName, strAvatar, strAbout, strAdress, strWeb, strPhone = "";
    Boolean boolFav, boolFavInitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail_neighbour);

        Bundle bundle = getIntent().getExtras();
        NeighbourApiService mApiService = DI.getNeighbourApiService();

        //On récupère l'ID unique du neighbour passé avec Bundle pour récupérer ses infos avec une nouvelle instance de DetailActivityService
        mActivityService = new DetailActivityService(bundle.getInt("Id"));
        Neighbour neighbourSelected = mActivityService.getNeighbourSelectedWithGoodId();

        // On affiche les infos du neighbour
        strName = neighbourSelected.getName();
        strAvatar = neighbourSelected.getAvatarUrl();
        strAbout = neighbourSelected.getAboutMe();
        strAdress = neighbourSelected.getAdress();
        strPhone = neighbourSelected.getPhone();
        strWeb = neighbourSelected.getWebSite();

        boolFav = neighbourSelected.getFavorite(); // servira pour le status du bouton
        boolFavInitial = boolFav; // on sauvegarde la valeur initial du status de favoris du neighbour

        String strFormatPhone = mActivityService.getPhoneNumberFormat(strPhone); //formatage du numéro de téléphone

        mBtnFavorite = (FloatingActionButton)findViewById(R.id.btnAddFavoriteNeighbour);
        mBtnFavorite = giveTheGoodBtnFavorite(mBtnFavorite, boolFav);

        ImageButton mBtnBack = (ImageButton) findViewById(R.id.btnBackToList);

        ImageView mUserAvatar = (ImageView) findViewById(R.id.userAvatar);
        Glide.with(mUserAvatar.getContext()).load(strAvatar).override(Target.SIZE_ORIGINAL).into(mUserAvatar);

        TextView mUserNameWhite = (TextView) findViewById(R.id.userNameWhite);
        mUserNameWhite.setText(strName);
        TextView mUserName = (TextView) findViewById(R.id.userName);
        mUserName.setText(strName);
        TextView mUserSite = (TextView) findViewById(R.id.userSiteWeb);
        mUserSite.setText(strWeb);
        TextView mUserPhone = (TextView) findViewById(R.id.userPhone);
        mUserPhone.setText(strFormatPhone);
        TextView mUserAdress = (TextView) findViewById(R.id.userAdress);
        mUserAdress.setText(strAdress);
        TextView mUserDesc = (TextView) findViewById(R.id.userDescription);
        mUserDesc.setText(strAbout);

        // action du bouton retour (flèche)
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               comeBackFirstActivity();
            }
        });

        // action du bouton favoris (étoile)
        mBtnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolFav = addFavoriteAction(boolFav, boolFavInitial);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void comeBackFirstActivity() {
        finish();
    }

    /**
     * action du bouton pour ajouter ou retirer le neighbour en tant que favoris.
     * On vérifie le status (true ou false) du bouton, on lui change son background.
     * on affiche un toast pour indiquer la modification à l'utilisateur.
     * On affecte la nouvelle valeur du status (true ou fase) du bouton et on change le status du neighbour
     * @param boolFav status actuel du bouton pour soit ajouter, soit retirer de la liste des favoris
     * @param boolFavInitial valeur initial du status de favoris du neighbour à l'ouverture de cette activité
     * @return valeur actuelle du status de favoris
     */
    private boolean addFavoriteAction(boolean boolFav, boolean boolFavInitial){
        if(!boolFav){
            mBtnFavorite.setImageResource(R.drawable.ic_star_yellow24dp);
            Toast.makeText(this, "Vous avez ajouté "+strName+" à vos voisin favorit", Toast.LENGTH_LONG).show();
            boolFav = true;
        }else{
            mBtnFavorite.setImageResource(R.drawable.ic_star_border_yellow_24dp);
            Toast.makeText(this, "Vous avez retiré "+strName+" de vos voisin favorit", Toast.LENGTH_LONG).show();
            boolFav = false;
        }
        mActivityService.refreshFavoritNeighbour(boolFav, boolFavInitial);
        return boolFav;
    }

    /**
     * affiche la bonne image au chargement de l'activité du bouton favorit en fonction du status de favoris qu'à le neighbour
     * @param btn bouton dont il faut afficher le bon background
     * @param b status favoris ou non du neighbour au démarrage de l'acticité
     * @return
     */
    private FloatingActionButton giveTheGoodBtnFavorite(FloatingActionButton btn, boolean b){
        if(b){
            btn.setImageResource(R.drawable.ic_star_yellow24dp);
        }else{
            btn.setImageResource(R.drawable.ic_star_border_yellow_24dp);
        }
        return btn;
    }

}
