package shreyas.storelocator.activities.storedetails;

import shreyas.storelocator.model.StoreAddress;

/**
 * Created by shreyasmp on 10/25/17.
 */

public interface StoreDetailView {

    void displayStoreDetailViews(StoreAddress address);

    void showStoreMap(StoreAddress address);

}
