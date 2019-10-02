package com.openclassrooms.entrevoisins.utils;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Matcher;
import org.w3c.dom.Text;

public class FavoriteListAction implements ViewAction {


    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on specific button";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.item_list_favorite_button);
        // Maybe check for null
        button.performClick();
    }



}
