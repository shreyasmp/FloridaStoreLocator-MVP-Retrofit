package shreyas.storelocator.rest.idling;

import android.support.test.espresso.IdlingResource;

/**
 * Created by shreyasmp on 10/27/17.
 */

public class EspressoIdling {

    public static TrackIdlingResource trackIdlingResource = new TrackIdlingResource("STORE");

    public static void incrementCounter() {
        trackIdlingResource.incrementCounter();
    }

    public static void decrementCounter() {
        trackIdlingResource.decrementCounter();
    }

    public static IdlingResource getIdlingResource() {
        return trackIdlingResource;
    }
}
