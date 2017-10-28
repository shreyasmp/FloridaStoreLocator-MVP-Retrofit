package shreyas.storelocator.rest.storesearch;

import retrofit2.Call;
import retrofit2.http.GET;
import shreyas.storelocator.model.StoreResult;

/**
 * Created by shreyasmp on 10/23/17.
 */

public interface StoreSearchAPI {

    @GET("stores.json")
    Call<StoreResult> getStoreSearchList();

}
