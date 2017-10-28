package shreyas.storelocator.activities.storedetails;

import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import shreyas.storelocator.R;
import shreyas.storelocator.databinding.StoreLocatorDetailActivityBinding;
import shreyas.storelocator.model.StoreAddress;

import static shreyas.storelocator.utils.AppConstants.STORE_DETAILS;

/**
 * Created by shreyasmp on 10/25/17.
 */

public class StoreDetailActivity extends AppCompatActivity implements StoreDetailView, OnMapReadyCallback {

    private static final String TAG = StoreDetailActivity.class.getSimpleName();
    private StoreLocatorDetailActivityBinding binding;

    private StoreDetailActions actions;
    private SupportMapFragment map;

    private double storeLat;
    private double storeLng;
    private String storeName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.store_locator_detail_activity);

        binding.detailStoreToolbar.setTitle(R.string.app_name);
        setSupportActionBar(binding.detailStoreToolbar);

        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material, null);
        upArrow.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        StoreAddress address = (StoreAddress) getIntent().getSerializableExtra(STORE_DETAILS);
        actions = new StoreDetailPresenter(this, address);
        actions.init();
        actions.showStoreLocationOnMap();
    }

    @Override
    public void displayStoreDetailViews(StoreAddress address) {

        Picasso.with(this).load(address.getStoreLogoURL()).into(binding.detailStoreLogo);
        String completeAddress = address.getAddress() + "\n"
                + address.getCity()
                + " "
                + address.getState()
                + " "
                + address.getZipcode();
        binding.detailStoreAddress.setText(completeAddress);
        binding.detailStoreContactNumber.setText(address.getPhone());
        binding.detailStoreid.setText(address.getStoreID());
    }


    @Override
    public void showStoreMap(StoreAddress address) {

        map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.store_map);
        map.getMapAsync(this);

        storeLat = address.getLatitude();
        storeLng = address.getLongitude();
        storeName = address.getName();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng storeLoc = new LatLng(storeLat, storeLng);
        googleMap.addMarker(new MarkerOptions().position(storeLoc).title(storeName));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(storeLoc));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(storeLoc)
                .zoom(15)
                .bearing(0)
                .tilt(45)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
    }
}
