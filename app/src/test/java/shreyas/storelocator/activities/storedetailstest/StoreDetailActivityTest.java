package shreyas.storelocator.activities.storedetailstest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import shreyas.storelocator.activities.storedetails.StoreDetailPresenter;
import shreyas.storelocator.activities.storedetails.StoreDetailView;
import shreyas.storelocator.model.StoreAddress;

import static org.mockito.Mockito.verify;

/**
 * Created by shreyasmp on 10/27/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class StoreDetailActivityTest {

    private StoreDetailPresenter storeDetailPresenter;

    @Mock
    private StoreDetailView storeDetailView;

    @Mock
    private StoreAddress storeAddress;

    @Before
    public void setUp() throws IOException {
        storeDetailPresenter = new StoreDetailPresenter(storeDetailView, storeAddress);
    }

    @Test
    public void showStoreDetails() throws IOException {
        storeDetailPresenter.init();

        verify(storeDetailView).displayStoreDetailViews(storeAddress);
    }

    @Test
    public void showStoreLocationOnMap() throws IOException {
        storeDetailPresenter.showStoreLocationOnMap();

        verify(storeDetailView).showStoreMap(storeAddress);
    }
}
