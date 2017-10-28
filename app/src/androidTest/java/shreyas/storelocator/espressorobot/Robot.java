package shreyas.storelocator.espressorobot;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;

import shreyas.storelocator.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by shreyasmp on 10/27/17.
 */

public class Robot {

    public Robot progressBarHidden() {
        onView(withId(R.id.progress_bar)).check(ViewAssertions.matches(not(isDisplayed())));
        return this;
    }

    public Robot erroDisplayed() {
        onView(withId(R.id.store_error_message)).check(ViewAssertions.matches(isDisplayed()));
        return this;
    }

    public Robot listErrorMessage() {
        onView(withId(R.id.store_error_message)).check(ViewAssertions.matches(withText(R.string.error_results)));
        return this;
    }

    public Robot listDisplayed() {
        onView(withId(R.id.store_lists)).check(ViewAssertions.matches(isDisplayed()));
        return this;
    }

    public Robot hideList() {
        onView(withId(R.id.store_lists)).check(ViewAssertions.matches(not(isDisplayed())));
        return this;
    }

    public Robot clickOnChildItem() {
        onView(withId(R.id.store_lists)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        return this;
    }

    public Robot clickHomeIconBack() {
        onView(withContentDescription("Navigate up")).perform(click());
        return this;
    }
}
