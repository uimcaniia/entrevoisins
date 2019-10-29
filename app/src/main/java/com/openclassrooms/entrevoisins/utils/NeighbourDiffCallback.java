package com.openclassrooms.entrevoisins.utils;

import android.support.v7.util.DiffUtil;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

public class NeighbourDiffCallback extends DiffUtil.Callback{

    private final List<Neighbour> oldNeighbour;
    private final List<Neighbour> newNeighbour;

    public NeighbourDiffCallback(List<Neighbour> newNeighbour, List<Neighbour> oldNeighbour) {
        this.newNeighbour = newNeighbour;
        this.oldNeighbour = oldNeighbour;
    }

    @Override
    public int getOldListSize() {
        return oldNeighbour.size();
    }

    @Override
    public int getNewListSize() {
        return newNeighbour.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldNeighbour.get(oldItemPosition).getId() == newNeighbour.get(newItemPosition).getId() ;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldNeighbour.get(oldItemPosition).equals(newNeighbour.get(newItemPosition));
    }
}