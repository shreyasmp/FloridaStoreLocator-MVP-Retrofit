package shreyas.storelocator.activities.storelist;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import shreyas.storelocator.R;
import shreyas.storelocator.activities.storedetails.StoreDetailActivity;
import shreyas.storelocator.adapter.StoreResultsAdapter;
import shreyas.storelocator.databinding.StoreListActivityBinding;
import shreyas.storelocator.listener.StoreOnClickListener;
import shreyas.storelocator.model.StoreAddress;
import shreyas.storelocator.model.StoreResult;
import shreyas.storelocator.rest.idling.EspressoIdling;

import static shreyas.storelocator.utils.AppConstants.STORE_DETAILS;

public class StoreListActivity extends AppCompatActivity implements StoreListView, StoreOnClickListener {


    private static final String TAG = StoreListActivity.class.getSimpleName();
    private StoreListActivityBinding binding;
    private StoreListActions storeListActions;
    private StoreResultsAdapter storeResultsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storeListActions = new StoreListPresenter(this);

        binding = DataBindingUtil.setContentView(this, R.layout.store_list_activity);

        displayStoreViews();
    }

    private void displayStoreViews() {

        binding.storeToolbar.setTitle(getResources().getString(R.string.app_name));

        storeListActions.storeServiceCall();

        binding.storeLists.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
        binding.storeErrorMessage.setVisibility(View.GONE);

        binding.storeLists.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.storeLists.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.storeLists.getContext(), layoutManager.VERTICAL);
        binding.storeLists.addItemDecoration(dividerItemDecoration);

        storeResultsAdapter = new StoreResultsAdapter(getApplicationContext(), this);
        binding.storeLists.setAdapter(storeResultsAdapter);

    }

    @Override
    public void onItemClick(StoreAddress address) {
        Intent intent = new Intent(this, StoreDetailActivity.class);
        intent.putExtra(STORE_DETAILS, address);
        startActivity(intent);
    }

    @Override
    public void showStoreResults(StoreResult result) {
        if(result != null) {
            storeResultsAdapter.setStoreDetailsArrayListData(result.getStoreAddresses());
            binding.storeLists.scrollToPosition(0);
        }
    }

    @Override
    public void setStoreListVisibility(boolean visibility) {
        if(visibility) {
            binding.storeLists.setVisibility(View.VISIBLE);
        } else {
            binding.storeLists.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setLoader(boolean loader) {
        if(loader) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showErrorMessage() {
        binding.storeErrorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorMessage() {
        binding.storeErrorMessage.setVisibility(View.INVISIBLE);
    }

    @VisibleForTesting
    public IdlingResource getIdlingResource() {
        return EspressoIdling.getIdlingResource();
    }
}
