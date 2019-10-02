package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DetailActivityService;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

public class DetailNeighbourActivity extends AppCompatActivity{

    private Button mBtnFavorite;
    private ImageButton mBtnBack;
    private ImageView mUserAvatar;
    private TextView mUserNameWhite;
    private TextView mUserName;
    private TextView mUserSite;
    private TextView mUserPhone;
    private TextView mUserAdress;
    private TextView mUserDesc;

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private Neighbour neighbourSelected;
    private DetailActivityService mActivityService;

    Integer intId;
    String strName, strAvatar, strAbout, strAdress, strWeb, strPhone = "";
    Boolean boolFav, boolFavInitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);

        Bundle bundle = getIntent().getExtras();

        mApiService = DI.getNeighbourApiService();
        mActivityService = new DetailActivityService(bundle.getInt("Id"));
        neighbourSelected = mActivityService.getNeighbourSelectedWithGoodId();

        strName = neighbourSelected.getName();
        strAvatar = neighbourSelected.getAvatarUrl();
        strAbout = neighbourSelected.getAboutMe();
        strAdress = neighbourSelected.getAdress();
        strPhone = neighbourSelected.getPhone();
        strWeb = neighbourSelected.getWebSite();
        boolFav = neighbourSelected.getFavorite();
        boolFavInitial = boolFav;

        String strFormatPhone = mActivityService.getPhoneNumberFormat(strPhone);

        mBtnFavorite = (Button)findViewById(R.id.btnAddFavoriteNeighbour);
        mBtnFavorite = giveTheGoodBtnFavorite(mBtnFavorite, boolFav);

        mBtnBack = (ImageButton ) findViewById(R.id.btnBackToList);

        mUserAvatar = (ImageView)findViewById(R.id.userAvatar) ;
        Glide.with(mUserAvatar.getContext()).load(strAvatar).into(mUserAvatar);

        mUserNameWhite = (TextView )findViewById(R.id.userNameWhite);
        mUserNameWhite.setText(strName);
        mUserName = (TextView )findViewById(R.id.userName);
        mUserName.setText(strName);
        mUserSite = (TextView )findViewById(R.id.userSiteWeb);
        mUserSite.setText(strWeb);
        mUserPhone = (TextView )findViewById(R.id.userPhone);
        mUserPhone.setText(strFormatPhone);
        mUserAdress = (TextView )findViewById(R.id.userAdress);
        mUserAdress.setText(strAdress);
        mUserDesc = (TextView )findViewById(R.id.userDescription);
        mUserDesc.setText(strAbout);

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               comeBackFirstActivity();
            }
        });

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
        if(boolFav != boolFavInitial) {
        }
        finish();
    }

    private boolean addFavoriteAction(boolean boolFav, boolean boolFavInitial){
        if(boolFav == false){
            mBtnFavorite.setBackground(this.getResources().getDrawable(R.drawable.ic_star_yellow24dp));
            Toast.makeText(this, "Vous avez ajouté "+strName+" à vos voisin favorit", Toast.LENGTH_LONG).show();
            boolFav = true;
        }else{
            mBtnFavorite.setBackground(this.getResources().getDrawable(R.drawable.ic_star_border_yellow_24dp));
            Toast.makeText(this, "Vous avez retiré "+strName+" de vos voisin favorit", Toast.LENGTH_LONG).show();
            boolFav = false;
        }
        mActivityService.refreshFavoritNeighbour(boolFav, boolFavInitial);
        return boolFav;
    }

    private Button giveTheGoodBtnFavorite(Button btn, boolean b){
        if(b == true){
            btn.setBackground(this.getResources().getDrawable(R.drawable.ic_star_yellow24dp));
        }else{
            btn.setBackground(this.getResources().getDrawable(R.drawable.ic_star_border_yellow_24dp));
        }
        return btn;
    }

}