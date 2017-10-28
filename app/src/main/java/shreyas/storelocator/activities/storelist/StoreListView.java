package shreyas.storelocator.activities.storelist;

import shreyas.storelocator.model.StoreResult;

/**
 * Created by shreyasmp on 10/24/17.
 */

public interface StoreListView {

    void showStoreResults(StoreResult result);

    void setStoreListVisibility(boolean visibility);

    void setLoader(boolean loader);

    void showErrorMessage();

    void hideErrorMessage();
}
