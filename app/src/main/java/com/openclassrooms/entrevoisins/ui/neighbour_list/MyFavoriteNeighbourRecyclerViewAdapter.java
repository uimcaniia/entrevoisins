package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.FavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.Toast.*;
import static android.widget.Toast.LENGTH_SHORT;

public class MyFavoriteNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoriteNeighbourRecyclerViewAdapter.ViewHolder>{


    private final List<Neighbour> mNeighbours;


    public MyFavoriteNeighbourRecyclerViewAdapter(List<Neighbour> items) {
        mNeighbours = items;
    }


    @Override
    // Creér une viewHolder(la vu de la cellule) à partir du layout xml représentant chaque ligne de la recyclerView. Celle ci sera appelé pour les première ligne visible de la recyclerView
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
// pour créer un layout a partir d'un xml il faut un "layoutInflater" pour ensuite le récup on utilise "inflate"
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_neighbour_favorite, parent, false);
        // ensuite on passe la vue du layout au "ViewHolder"
        return new ViewHolder(view);
    }

    @Override
    // applique une donnée à chacune des ligne visible de la recyclerView. On y met a jour les données et l'apparence
    // "ViewHolder" qui recoit la donnée et "position" la position qu'occupe l'élément dans la liste
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
       // System.out.println(neighbour);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                //télécharge la photo
                .load(neighbour.getAvatarUrl())
                // découpe l'avatar en cercle
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);
        //ajoute écouter au btn favorite
        holder.mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new FavoriteNeighbourEvent(neighbour, false));
            }
        });
        //---------------------------------------------------------
        holder.clickListener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent ( context , DetailNeighbourActivity.class );
                //On fait passer les données du neighbour sélectionné à la deuxième activité avec le systeme du Bundle
                // //(intéret est qu'il peut etre retournée par une classe indépendant de l'activity sur laquelle on travaille
                Bundle bundle = new Bundle();
                bundle.putInt("Id", neighbour.getId());
                intent.putExtras(bundle);
                context.startActivity (intent);
            }
        });
        //-----------------------------------------------------------------
    }

    @Override
    // retourne le nombre total de cellule que contiendra la liste
    public int getItemCount() {
        //System.out.println(mNeighbours.size());
        return mNeighbours.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_favorite_button)
        public ImageButton mFavoriteButton;

        //------------------------------------------------------------------
        public View clickListener;

        //------------------------------------------------------------------


        // prend en param la vue a afficher. on récup (butterKnife et param juste au dessus) dans ce constructeur l'avatar, le nom et le btn suppr
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.clickListener = view;
        }
    }

}
