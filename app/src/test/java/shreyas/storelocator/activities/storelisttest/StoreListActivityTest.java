package shreyas.storelocator.activities.storelisttest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import shreyas.storelocator.activities.storelist.StoreListPresenter;
import shreyas.storelocator.activities.storelist.StoreListView;
import shreyas.storelocator.model.StoreResult;

import static org.mockito.Mockito.verify;

/**
 * Created by shreyasmp on 10/27/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class StoreListActivityTest {

    private StoreListPresenter storeListPresenter;

    @Mock
    private StoreListView storeListView;

    @Before
    public void setUp() throws IOException {
        storeListPresenter = new StoreListPresenter(storeListView);
    }

    @Test
    public void showError() throws IOException {
        storeListPresenter.showError();

        verify(storeListView).showErrorMessage();
    }

    @Test
    public void hideError() throws IOException {
        storeListPresenter.hideError();

        verify(storeListView).hideErrorMessage();
    }

    @Test
    public void setLoaderTrue() throws IOException {
        storeListPresenter.setLoader();

        verify(storeListView).setLoader(true);
    }

    @Test
    public void setLoaderFalse() throws IOException {
        storeListPresenter.setLoader();

        verify(storeListView).setLoader(false);
    }

    @Test
    public void setListVisibilityTrue() throws IOException {
        storeListPresenter.setListVisiblity();

        verify(storeListView).setStoreListVisibility(true);
    }

    @Test
    public void setListVisibilityFalse() throws IOException {
        storeListPresenter.setListVisiblity();

        verify(storeListView).setStoreListVisibility(false);
    }

    @Test
    public void showStoreList() throws IOException {
        storeListPresenter.showStoreList();

        StoreResult storeResult = null;

        verify(storeListView).showStoreResults(storeResult);
    }
}
