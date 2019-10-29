package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
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

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class NeighbourFragment extends Fragment{

    private NeighbourApiService mApiService;
    private RecyclerView mRecyclerView;
    FloatingActionButton mAddButton;

    /** Create and return a new instance of NeighbourFragment} */
    public static NeighbourFragment newInstance() {
        NeighbourFragment fragment = new NeighbourFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        configBtnAddNewNeighbour(view);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_neighbours);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context)); // va positionner les élément en ligne, par défault de haut en bas
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        initList();
        return view;
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


    /** Init the List of neighbours */
    private void initList() {
        List<Neighbour> mNeighbours = mApiService.getNeighbours();
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours)); //setAdapter => attache l'adapter à la recyclerView
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this); //enregistre l’objet destination en temps que receveur auprès d’EventBus, en prenant en compte le cycle de vie de l’activité ou du fragment
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
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {     // transmet les la demande de supprimer un voisin au click.
        mApiService.deleteNeighbour(event.neighbour);

        //-----------------------AJOUT------------------------------------------------------
        //fragment 0 envoie les infos sous le nom d'une action précise via un Intent et le LocalBroadcastManager pour que fragment 1 réactualise son affichage de son voisin favorit supprimé
        Context context = getContext();
        final Intent intent = new Intent("INIT_LIST_FAVORITE_NEIGHBOURS");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        //-----------------------------------------------------------------------------

        initList();
    }

}
