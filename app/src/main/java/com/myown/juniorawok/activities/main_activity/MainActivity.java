package com.myown.juniorawok.activities.main_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.myown.juniorawok.R;
import com.myown.juniorawok.adapters.MainRecyclerAdapter;
import com.myown.juniorawok.models.FlashHomeCombined;
import com.myown.juniorawok.network.models.HomeAPIResponse;
import com.myown.juniorawok.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityView{

    @BindView(R.id.main_recycler)
    RecyclerView mainRecycler;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    MainActivityPresenter mainActivityPresenter;

    MainRecyclerAdapter mainRecyclerAdapter;

    int mainProductsPage = 1;

    int maxPageLimit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Initializing the presenter for this activity.
        mainActivityPresenter = new MainActivityPresenter(this);

        // The following method call is responsible of setting up all of the configuration related
        // to recycler view.
        configureRecycler();

        // The following method call is responsible to perform the series of tasks that this activity
        // is supposed to perform.
        setupActivityTasks();
    }

    /**
     * In this method we are configuring mainRecycler according to our needs.
     */
    private void configureRecycler() {

        // Create a grid layout with two columns
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

        // Create a custom SpanSizeLookup where the first item spans both columns
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? 2 : 1;
            }
        });

        // Setting up the layout manager for recycler
        mainRecycler.setLayoutManager(layoutManager);

        // Setting up onScrollListener for recycler. Which will be used for pagination.
        mainRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                // The following condition is to check that if the user has reached the end of one
                // particular scroll.
                if ( (visibleItemCount + firstVisibleItemPosition) >=
                        totalItemCount && mainProductsPage < maxPageLimit)
                {
                    // If yes then increment the page number by one and call the presenter method
                    // to fetch more products.
                    ++mainProductsPage;
                    mainActivityPresenter.fetchHomeProducts(mainProductsPage);
                }
            }
        });
    }

    private void setupActivityTasks() {
        // Setting the visibility of the progress bar.
        progressBar.setVisibility(View.VISIBLE);

        // Calling the presenter method to fetch flash and home products.
        mainActivityPresenter.fetchProducts();
    }

    /**
     * This is the CallBack method from MainActivityPresenter which will be called when flash and
     * home products are fetched successfully.
     * I am using RxJava to combine the response from different Async Retrofit Calls.
     * @param flashHomeCombined contains the combined response flash and home API
     */
    @Override
    public void onCombinedProductsFetched(FlashHomeCombined flashHomeCombined) {
        progressBar.setVisibility(View.GONE);
        maxPageLimit = flashHomeCombined.homeAPIResponse.getoUTPUT().getnAVIGATION().getmAX_PAGES();
        mainRecyclerAdapter = new MainRecyclerAdapter(this,
                flashHomeCombined.flashAPIResponse.getoUTPUT().getdATA().getiTEMS(),
                flashHomeCombined.homeAPIResponse.getoUTPUT().getdATA().getiTEMS());
        mainRecycler.setAdapter(mainRecyclerAdapter);
    }

    /**
     * This is the CallBack method from MainActivityPresenter which will be called when home
     * products are fetched successfully.
     * This method will only be called on pagination.
     * On pagination since we have to just load home products. So, the param will just contain the
     * list of home products.
     * @param homeAPIResponse contains the list of home products. (On Pagination)
     */
    @Override
    public void onHomeProductsFetched(HomeAPIResponse homeAPIResponse) {
        mainRecyclerAdapter.loadMoreItems(homeAPIResponse.getoUTPUT().getdATA().getiTEMS());
        mainRecyclerAdapter.notifyDataSetChanged();
    }

    /**
     * This is the CallBack method from MainActivityPresenter which will be called if there is some
     * problem in fetching the products.
     * The reason of failure can be following:
     * - Session TimeOut
     * - Connection Reset
     * - Server Not Responding
     * - Any other http response than 202
     */
    @Override
    public void onProductsFetchFailed() {
        progressBar.setVisibility(View.GONE);
        Utils.showAlertDialog(getString(R.string.unable_to_fetch_data), this);
    }

    /**
     * This is the CallBack method from MainActivityPresenter which will be called if the network is
     * not available while the presenter tries to call the APIs.
     * The reason of failure can be following:
     * - Wifi is turned off
     * - Wifi is turned on but for some reason network is not available
     */
    @Override
    public void onNetworkFailure() {
        progressBar.setVisibility(View.GONE);
        Utils.showAlertDialog(getString(R.string.unable_to_connect_to_the_internet), this);
    }
}
