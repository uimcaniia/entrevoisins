package com.openclassrooms.entrevoisins.ui.neighbour_list;

import com.openclassrooms.entrevoisins.R;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;

import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

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

    @Override // @Override est facultatif mais permet au compilateur d'optimiser le bytecode
    protected void onCreate(Bundle savedInstanceState) { // Bundle sauvegarde d'information en cas de retour arrière vers cette activité. vaut null en cas de lancement pour la première fois

        super.onCreate(savedInstanceState); // "super"  fait appel à une méthode (ici "onCreate") ou un attribut qui appartient à la superclasse AppCompatActivity
        setContentView(R.layout.activity_list_neighbour); //indiquer l'interface graphique de notre activité. Ne pas utiliser deux fois à suivre cette méthode sinon la deuxième écrase la première.
        ButterKnife.bind(this); //remplace le findViewById
        setSupportActionBar(mToolbar);

        //-----------------------------AJOUT----------------------------------------------
        // liste des fragments : 0=> TOUS les neighbours, 1=> les neighbours mit en favorite
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        Fragment frag = NeighbourFragment.newInstance();
        fragments.add(frag);
        frag = FavoriteFragment.newInstance();
        fragments.add(frag);
        //---------------------------------------------------------------------------

        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager(), fragments); //on créer un adapter et on lui passe la liste des fragments (les 2 onglets)
        mViewPager.setAdapter(mPagerAdapter); // on passe l'adapter à la vue d'adapter (mViewPager) afin qu'elle puisse afficher des données

        //addOnPageChangeListener => Ajoute un écouteur qui sera appelé chaque fois que la page sera modifiée ou fera défiler de manière incrémentielle.
        //La classe ViewPager.OnPageChangeListener qui contient les appels nécessaires au TabLayout fourni, de sorte que la position de la tabulation reste synchronisée.
        //addOnTabSelectedListener => défini un écouteur pour être averti lorsqu'un état de sélection d'un onglet a été modifié.
        //Une classe TabLayout.OnTabSelectedListener qui contient les appels nécessaires au ViewPager fourni afin que la position de la tabulation reste synchronisée.
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
