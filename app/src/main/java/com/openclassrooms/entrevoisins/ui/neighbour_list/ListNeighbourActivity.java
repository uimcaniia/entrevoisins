package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.openclassrooms.entrevoisins.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListNeighbourActivity extends AppCompatActivity {


    // UI Components
    @BindView(R.id.tabs) // barre onglet
    TabLayout mTabLayout;
    @BindView(R.id.toolbar) // barre titre
    Toolbar mToolbar;
    @BindView(R.id.container)
    ViewPager mViewPager;

    ListNeighbourPagerAdapter mPagerAdapter;

//@Override permet d'indiquer que l'on va redéfinir une méthode qui existait auparavant dans la classe parente
// @Override est facultatif mais permet au compilateur d'optimiser le bytecode
    @Override
    // Bundle sauvegarde d'information en cas de retour arrière vers cette activité. vaut null en cas de lancement pour la première fois
    protected void onCreate(Bundle savedInstanceState) {

        // "super"  fait appel à une méthode (ici "onCreate") ou un attribut qui appartient à la superclasse AppCompatActivity
        super.onCreate(savedInstanceState);

        // setContentView permet d'indiquer l'interface graphique de notre activité (ici "activity_list_neighbour"). Ne pas utiliser deux fois à suivre cette méthode sinon la deuxième écrase la première. 1 seule par oncreate
        setContentView(R.layout.activity_list_neighbour);
        //remplace le findViewById
        ButterKnife.bind(this);
        //this.configureViewPagerAndTabs();
        setSupportActionBar(mToolbar);

        //---------------------------------------------------------------------------
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        Fragment frag = NeighbourFragment.newInstance();
        fragments.add(frag);

        // liste des fragments : 1=> TOUS les neighbours, 2=> les neighbours mit en favorite

        frag = FavoriteFragment.newInstance();
        fragments.add(frag);
        //---------------------------------------------------------------------------

        //on créer un adapter et on lui passe la liste des fragments (les 2 onglets)
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager(), fragments);

        // on passe l'adapter à la recyclerView (mViewPager) afin qu'elle puisse affciher des données
        mViewPager.setAdapter(mPagerAdapter);
        //addOnPageChangeListener => Ajoute un écouteur qui sera appelé chaque fois que la page sera modifiée ou fera défiler de manière incrémentielle.
        //Une classe ViewPager.OnPageChangeListener qui contient les appels nécessaires au TabLayout fourni, de sorte que la position de la tabulation reste synchronisée.
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        //addOnTabSelectedListener => défini un écouteur pour être averti lorsqu'un état de sélection d'un onglet a été modifié.
        //Une classe TabLayout.OnTabSelectedListener qui contient les appels nécessaires au ViewPager fourni afin que la position de la tabulation reste synchronisée.
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
