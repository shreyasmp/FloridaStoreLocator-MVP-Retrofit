package shreyas.storelocator.activities.storelist;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shreyas.storelocator.model.StoreResult;
import shreyas.storelocator.rest.idling.EspressoIdling;
import shreyas.storelocator.rest.storesearch.StoreSearchServiceCall;

/**
 * Created by shreyasmp on 10/24/17.
 */

public class StoreListPresenter implements StoreListActions {

    private static final String TAG = StoreListPresenter.class.getSimpleName();
    private final StoreListView storeListView;
    private final StoreSearchServiceCall serviceCall;
    private StoreResult results;

    public StoreListPresenter(StoreListView storeListView) {
        this.storeListView = storeListView;
        this.serviceCall =  new StoreSearchServiceCall();
    }

    @Override
    public void storeServiceCall() {
        storeListView.setLoader(true);
        storeListView.hideErrorMessage();

        EspressoIdling.incrementCounter();

        serviceCall.getClient()
                .getStoreSearchList()
                .enqueue(new Callback<StoreResult>() {
                    @Override
                    public void onResponse(Call<StoreResult> call, Response<StoreResult> response) {
                        EspressoIdling.decrementCounter();
                        results = response.body();
                        if(results.getStoreAddresses().isEmpty()) {
                            storeListView.setStoreListVisibility(false);
                            storeListView.setLoader(false);
                            storeListView.showErrorMessage();
                        } else {
                            storeListView.setStoreListVisibility(true);
                            storeListView.setLoader(false);
                            storeListView.showStoreResults(results);
                        }
                    }

                    @Override
                    public void onFailure(Call<StoreResult> call, Throwable t) {
                        storeListView.setLoader(false);
                        storeListView.showErrorMessage();
                    }
                });
        Log.d(TAG, "URL: "+ serviceCall.getClient());
    }

    @Override
    public void showError() {
        storeListView.showErrorMessage();
    }

    @Override
    public void hideError() {
        storeListView.hideErrorMessage();
    }

    @Override
    public void setLoader() {
        storeListView.setLoader(true);
        storeListView.setLoader(false);
    }

    @Override
    public void setListVisiblity() {
        storeListView.setStoreListVisibility(true);
        storeListView.setStoreListVisibility(false);
    }

    @Override
    public void showStoreList() {
        storeListView.showStoreResults(results);
    }
}
