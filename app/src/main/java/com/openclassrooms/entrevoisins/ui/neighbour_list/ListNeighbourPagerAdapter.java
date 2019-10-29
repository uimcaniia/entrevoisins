package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

//Mise en page qui permet à l’utilisateur de faire glisser à gauche et à droite des "pages" de contenu qui sont généralement des fragments différents
public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    private final ArrayList fragments;

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
        //return NeighbourFragment.newInstance();
        return (Fragment) fragments.get(position);
    }


    /** get the number of pages */
    @Override
    public int getCount() {
        //return mFragmentList.size();
        return 2;
    }
}