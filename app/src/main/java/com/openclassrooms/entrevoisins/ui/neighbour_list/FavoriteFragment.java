package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DetailNeighbourEvent;
import com.openclassrooms.entrevoisins.events.FavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment {


    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private List<Neighbour> mFavoriteNeighbours;
    private RecyclerView mRecyclerView;
    private Integer intId;

    //----------------------------------------------------------------------------
    //fragment 2 s'abonne l'action de supprimer un voisins favorit si il a été supprimer de l'autre fragment spécifique pour récupérer les données
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if ("INIT_LIST_FAVORITE_NEIGHBOURS".equals(intent.getAction()) == true)
            {
               initList();
            }
        }
    };
    //----------------------------------------------------------------------------
    /**
     * Create and return a new instance
     * @return @{@link FavoriteFragment}
     */
    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(broadcastReceiver, new IntentFilter("INIT_LIST_FAVORITE_NEIGHBOURS"));
        mApiService = DI.getNeighbourApiService(); // pour utiliser dans la classe DI, les méthodes makeFavoriteNeighbour() et takeOffFavoriteNeighbour() venant de la classe DummyNeighbourApiService implémenter par NeighbourApiService
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_neighbour_list, container, false);

        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context)); // va positionner les élément en ligne, par défault de haut en bas
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    /**
     * Init the List of neighbours
     */
    public void initList() {
        mFavoriteNeighbours = mApiService.getFavoriteNeighbour();
    System.out.println("Init exécuté!!");
        mRecyclerView.setAdapter(new MyFavoriteNeighbourRecyclerViewAdapter(mFavoriteNeighbours));
    }

    @Override
    public void onStart() {
        super.onStart();
        //enregistre l’objet destination en temps que receveur auprès d’EventBus, en prenant en compte le cycle de vie de l’activité ou du fragment
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(broadcastReceiver);
    }
    /**
     * Fired if the user clicks on a favorite button
     * @param event
     */
    @Subscribe
     public void onFavoriteNeighbour(FavoriteNeighbourEvent event) {
        mApiService.makeFavoriteNeighbour(event.neighbour, false);
        initList();
    }


}
