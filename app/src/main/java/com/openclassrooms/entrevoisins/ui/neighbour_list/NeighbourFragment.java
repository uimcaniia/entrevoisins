package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DetailNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;


public class NeighbourFragment extends Fragment{


    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;
    FloatingActionButton mAddButton;


    /**
     * Create and return a new instance
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance() {
        NeighbourFragment fragment = new NeighbourFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService(); // pour utiliser les méthodes getNeighbours() et deleteNeighbour()

        //mAddButton = (Button)findViewById(R.id.add_new_neighbour);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        //Context context = view.getContext();
        View drawer = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        configBtnAddNewNeighbour(drawer);
       // mRecyclerView = (RecyclerView) drawer;
        Context context = drawer.getContext();
        mRecyclerView = (RecyclerView) drawer.findViewById(R.id.list_neighbours);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context)); // va positionner les élément en ligne, par défault de haut en bas
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        initList();
        return drawer;
        //return view;
    }

    private void configBtnAddNewNeighbour(View drawer) {
        mAddButton = (FloatingActionButton)drawer.findViewById(R.id.add_new_neighbour);
        mAddButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mApiService.getNewNeighbour();
                initList();
            }
        });
    }
    /**
     * Init the List of neighbours
     */
    private void initList() {

        mNeighbours = mApiService.getNeighbours();
        //setAdapter => attache l'adapter à la recyclerView
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours)); // va s'ocuper du contenu et de leur vue
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

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    // transmet les la demande de supprimer un voisin au click.
    // Envoie une nouvelle instance de l'évènement "DeleteNeighbourEvent" à la méthode "deleteNeighbour" de l'interface "NeighbourApiService"
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        // NeighbourApiService est implémenter par "DI", "DummyNeighbourApiService" et "NeighbourFragment (mApiService)"
        mApiService.deleteNeighbour(event.neighbour);

        //-----------------------------------------------------------------------------
        //fragment 1 envoie les infos sous le nom d'une action précise via un Intent et le LocalBroadcastManager pour que fragment 2 réactualise son affichage de son voisin favorit supprimé
        Context context = getContext();
        final Intent intent = new Intent("INIT_LIST_FAVORITE_NEIGHBOURS");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        //-----------------------------------------------------------------------------
        initList();
    }

    //-----------------------------------------------------------------------------
    @Subscribe
    public void onDetailNeighbourEvent(DetailNeighbourEvent event) {
        //mApiService.detailNeighbour(event.neighbour);
    }



    //----------------------------------------------------------------------------


}
