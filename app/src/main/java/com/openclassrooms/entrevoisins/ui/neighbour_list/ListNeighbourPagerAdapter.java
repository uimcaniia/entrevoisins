package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

//Mise en page qui permet à l’utilisateur de faire glisser à gauche et à droite des "pages"
//de contenu qui sont généralement des fragments différents
public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    private final ArrayList fragments;

    //FragmentManager interagit avec les fragments qui se trouvent dans une Activity
    public ListNeighbourPagerAdapter(FragmentManager fm, ArrayList fragments) {
        super(fm);
        this.fragments = fragments;
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return (Fragment) fragments.get(position);
        //return NeighbourFragment.newInstance();
    }


    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        //return mFragmentList.size();
        return 2;
    }
}