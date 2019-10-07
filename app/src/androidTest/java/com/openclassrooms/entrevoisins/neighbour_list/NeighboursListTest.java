
package com.openclassrooms.entrevoisins.neighbour_list;


import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;


import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import com.openclassrooms.entrevoisins.utils.FavoriteListAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private ListNeighbourActivity mActivity;

    //-----------------------------------------------
    private static int ITEMS_COUNT_FAVORITE = 3;
    private static int POSITION_FAVORITE = 1;
    //-----------------------------------------------

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule  =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        int neighbourItemCount = checkItemInNeighbourRecyclerView();
        // Given : We remove the element at position 2
        onView(withId(R.id.list_neighbours)).check(withItemCount(neighbourItemCount));
        // When perform a click on a delete icon
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(withId(R.id.list_neighbours)).check(withItemCount(neighbourItemCount-1));
    }

    //----------------------------------------------------
    public int checkItemInFavoriteRecyclerView(){
        RecyclerView recyclerView = mActivityRule.getActivity().findViewById(R.id.list_neighbours_favorite);
        int favoriteItemCount = recyclerView.getAdapter().getItemCount();
        return favoriteItemCount;
    }

    public int checkItemInNeighbourRecyclerView(){
        RecyclerView recyclerView = mActivityRule.getActivity().findViewById(R.id.list_neighbours);
        int neighbourItemCount = recyclerView.getAdapter().getItemCount();
        return neighbourItemCount;
    }

    /**
     * When we click for to see the favorite list tab, we see just favorite neighbours
     */
    @Test
    public void onFavoriteNeighbourView_showOnlyFavoredNeighbour() {
        int neighbourItemCount = checkItemInNeighbourRecyclerView();
        int favoriteItemCount = checkItemInFavoriteRecyclerView();
        // sur le premier onglet on a la liste complète
        onView(withId(R.id.list_neighbours)).check(withItemCount(neighbourItemCount));
        // on click sur l'onglet pour afficher la list des neighbours favorite
        onView(allOf(withText("Favorites"), isDescendantOfA(withId(R.id.tabs)))).perform(click());
        // on retrouve que les favorit (3)
        onView(withId(R.id.list_neighbours_favorite)).check(withItemCount(favoriteItemCount));
    }

    /**
     * When we delete an item in the list neighbour tab, the item is no more shown in the favorite tab also.
     */
    @Test
    public void onMyNeighboursList_deleteActionOnFavoriteNeighbourItem_shouldRemoveItemInMyFavoriteNeighbourList(){
        int neighbourItemCount = checkItemInNeighbourRecyclerView();
        int favoriteItemCount = checkItemInFavoriteRecyclerView();
        // sur le premier onglet on a la liste complète
        onView(withId(R.id.list_neighbours)).check(withItemCount(neighbourItemCount));
        //on supprime un neighbour qui est aussi dans la liste des favorits
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(POSITION_FAVORITE, new DeleteViewAction()));
        // on click ensuite sur l'onglet pour afficher la list des neighbours favorite encore existant
        onView(allOf(withText("Favorites"), isDescendantOfA(withId(R.id.tabs)))).perform(click());
        // le neighbour supprimé s'est fait aussi supprimé
        onView(withId(R.id.list_neighbours_favorite)).check(withItemCount(favoriteItemCount-1));
    }

    /**
     * When we click on the favorite button (star form) on the item in the favorite tab, the item is take off of this favorite list tab(not in the list neighbour tab)
     */
    @Test
    public void onMyFavoriteNeighbourList_deleteFavoriteStatusAction_shouldRemoveItemInMyFavoriteNeighbourList(){
        int favoriteItemCount = checkItemInFavoriteRecyclerView();
        // on click sur l'onglet pour afficher la liste des neighbours favorite
        onView(allOf(withText("Favorites"), isDescendantOfA(withId(R.id.tabs)))).perform(click());
        // on click sur le bouton pour retirer le neighbour de la liste des favorit
        onView(withId(R.id.list_neighbours_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new FavoriteListAction()));
        //le neighbour s'est fait retirer de la liste des favorit
        onView(withId(R.id.list_neighbours_favorite)).check(withItemCount(favoriteItemCount-1));
    }
    /**
     * When we click on the add neighbour button (plus form) in the list tab, a new neighbour added
     */
    @Test
    public void onNeighbourList_addNewNeighbourAction_shouldaddItemInNeighbourList(){
        int neighbourItemCount = checkItemInNeighbourRecyclerView();
        onView(withId(R.id.add_new_neighbour)).perform(click());
        // on click sur le bouton pour ajouter  un neighbour à la liste des voisins
        onView(withId(R.id.list_neighbours)).check(withItemCount(neighbourItemCount+1));
    }
    //---------------------------------------------------------------------------------------------
}