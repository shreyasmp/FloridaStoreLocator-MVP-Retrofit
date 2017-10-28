package shreyas.storelocator.activities.storelist;

/**
 * Created by shreyasmp on 10/23/17.
 */

public interface StoreListActions {

    void storeServiceCall();

    void showError();

    void hideError();

    void setLoader();

    void setListVisiblity();

    void showStoreList();
}
