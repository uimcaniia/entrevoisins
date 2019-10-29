package com.openclassrooms.entrevoisins.neighbour_list;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import static android.support.test.espresso.Espresso.onView;
import android.support.test.espresso.contrib.RecyclerViewActions;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;


@RunWith(AndroidJUnit4.class)
public class DetailNeighbourActivityTest{

    // This is fixed
    private ListNeighbourActivity mActivity;
    private static String mName;
    private static int ITEMS_COUNT = 12;
    private static int ITEMS_COUNT_FAVORITE = 3;
    private static int POSITION_FAVORITE = 1;
    private static int POSITION_NOT_FAVORITE = 0;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule  =
            new ActivityTestRule(ListNeighbourActivity.class);


    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        mName = "Caroline";
    }

    /**
     * When we click on item, the new window show the good name of the item
     */
    @Test
    public void onDetailNeighbourWindow_nameOfNeigbourSelectInTextView_mustBeCorrect() {
        // When click sur un des neighbours de la liste
        onView(withId(R.id.list_neighbours )).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        // la vue de détail affiche le bon nom du neighbour
        onView(withId(R.id.userName )).check(matches(withText(mName)));
    }

    /**
     * When we click on item neighbour, we can see the new activity who show we the details of the neighbour selected
     */
    @Test
    public void onMyNeighboursList_WhenNeighbourIsClick_ShowDetailNeighbourWindow() {
        //When click sur un neighbour
        onView(withId(R.id.list_neighbours )).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        // activité 2 bien lancée en vérifiant si le textView "a propos de moi" est visible
        onView(withId(R.id.aPropos )).check(matches(withText("A propos de moi")));
    }

    /**
     * When we click on the come back button (arrow form) in the view detail (2n activity) of the neighbour selected, we come back to the first activity
     */
    @Test
    public void onViewDetail_comeBackAction_showfirstActivity() {
        //When click sur un neighbour pas mis en favorit
        onView(withId(R.id.list_neighbours )).perform(RecyclerViewActions.actionOnItemAtPosition(POSITION_NOT_FAVORITE, click()));
        // sur la vue de détail, quand on click sur le bouton pour retourner en arrière
        onView(withId(R.id.btnBackToList )).perform(click());
        //on retrouve la première activité et la liste des neighbours
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
    }

    public int checkItemInFavoriteRecyclerView(){
        RecyclerView recyclerView = mActivityRule.getActivity().findViewById(R.id.list_neighbours_favorite);
        int itemCount = recyclerView.getAdapter().getItemCount();
        return itemCount;
    }

    /**
     * for a neighbour who is not Favorite, when we click on the favorite button (star form) in the view detail activity, after, the neighbour is added in the favorite list item
     */
    @Test
    public void onViewDetail_addFavoriteStatusAction_shouldAddesItemInMyFavoriteNeighbourList() {
        int itemCount = checkItemInFavoriteRecyclerView();
        //When click sur un neighbour pas mis en favorit
        onView(withId(R.id.list_neighbours )).perform(RecyclerViewActions.actionOnItemAtPosition(POSITION_NOT_FAVORITE, click()));
        // sur la vue de détail, quand on click sur le bouton pour le mettre en favorit
        onView(withId(R.id.btnAddFavoriteNeighbour )).perform(click());
        //lorsqu'on retourne sur la première activité en clickant sur le bouton back,
        onView(withId(R.id.btnBackToList )).perform(click());
        // on retrouve la première activité et la liste des neighbours, on click sur l'onglet pour afficher la list des neighbours favorite
        onView(allOf(withText("Favorites"), isDescendantOfA(withId(R.id.tabs)))).perform(click());
        // on a un favorit en +
        onView(withId(R.id.list_neighbours_favorite)).check(withItemCount(itemCount + 1));
    }

    /**
     * for a neighbour who already Favorite, when we click on the favorite button (star form) in the view detail activity, after, the neighbour is removed of the favorite list item
     */
    @Test
    public void onViewDetail_deleteFavoriteStatusAction_shouldRemoveItemInMyFavoriteNeighbourList() {
        int itemCount = checkItemInFavoriteRecyclerView();
        //When click sur un neighbour pas mis en favorit
        onView(withId(R.id.list_neighbours )).perform(RecyclerViewActions.actionOnItemAtPosition(POSITION_FAVORITE, click()));
        // sur la vue de détail, quand on click sur le bouton pour le mettre en favorit
        onView(withId(R.id.btnAddFavoriteNeighbour )).perform(click());
        //lorsqu'on retourne sur la première activité en clickant sur le bouton back,
        onView(withId(R.id.btnBackToList )).perform(click());
        // on retrouve la première activité et la liste des neighbours, on click sur l'onglet pour afficher la list des neighbours favorite
        onView(allOf(withText("Favorites"), isDescendantOfA(withId(R.id.tabs)))).perform(click());
        // on a un favorit en +
        onView(withId(R.id.list_neighbours_favorite)).check(withItemCount(itemCount - 1));
    }





}