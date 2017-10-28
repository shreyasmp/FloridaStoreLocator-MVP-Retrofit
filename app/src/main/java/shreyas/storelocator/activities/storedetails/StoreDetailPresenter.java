package shreyas.storelocator.activities.storedetails;

import shreyas.storelocator.model.StoreAddress;

/**
 * Created by shreyasmp on 10/25/17.
 */

public class StoreDetailPresenter implements StoreDetailActions {

    private static final String TAG = StoreDetailPresenter.class.getSimpleName();
    private StoreDetailView storeDetailView;
    private StoreAddress storeAddress;

    public StoreDetailPresenter(StoreDetailView storeDetailView, StoreAddress storeAddress){
        this.storeDetailView = storeDetailView;
        this.storeAddress = storeAddress;
    }

    @Override
    public void init() {
        storeDetailView.displayStoreDetailViews(storeAddress);
    }

    @Override
    public void showStoreLocationOnMap() {
        storeDetailView.showStoreMap(storeAddress);
    }

}
