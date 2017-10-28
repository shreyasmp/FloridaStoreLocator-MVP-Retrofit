package shreyas.storelocator.runner;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import shreyas.storelocator.activities.storelist.StoreListActivity;
import shreyas.storelocator.espressorobot.Robot;

/**
 * Created by shreyasmp on 10/27/17.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RunRobotTest {

    @Rule
    public  IntentsTestRule<StoreListActivity> storeListActivityIntentsTestRule =
            new IntentsTestRule<>(StoreListActivity.class);

    private Robot robo = new Robot();

    @Before
    public void registerResource() {
        Espresso.registerIdlingResources(storeListActivityIntentsTestRule.getActivity().getIdlingResource());
    }

    @SmallTest
    public void initialScreenShow() {
        robo.progressBarHidden().listDisplayed();
    }

    @Test
    public void clickOnListChildItem() {
        robo.listDisplayed()
                .clickOnChildItem();
    }

    @Test
    public void clickHomeButtonBackOnChildItemClick() {
        robo.listDisplayed()
                .clickOnChildItem()
                .clickHomeIconBack();
    }

    @After
    public void unRegisterIdlingResource() {
        Espresso.unregisterIdlingResources(storeListActivityIntentsTestRule.getActivity().getIdlingResource());
    }
}
