package shreyas.storelocator.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by shreyasmp on 10/23/17.
 */

public class StoreResult {

    @SerializedName("stores")
    private ArrayList<StoreAddress> storeAddresses = null;

    public ArrayList<StoreAddress> getStoreAddresses() {
        return storeAddresses;
    }

    public void setStoreAddresses(ArrayList<StoreAddress> storeAddresses) {
        this.storeAddresses = storeAddresses;
    }

}
