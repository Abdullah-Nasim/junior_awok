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

        mainActivityPresenter = new MainActivityPresenter(this);

        configureRecycler();

        setupActivityTasks();
    }

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

        mainRecycler.setLayoutManager(layoutManager);

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

                if ( (visibleItemCount + firstVisibleItemPosition) >= totalItemCount && mainProductsPage < maxPageLimit)
                {
                    ++mainProductsPage;
                    mainActivityPresenter.fetchHomeProducts(mainProductsPage);
                }
            }
        });
    }

    private void setupActivityTasks() {
        progressBar.setVisibility(View.VISIBLE);
        mainActivityPresenter.fetchProducts();
    }

    @Override
    public void onCombinedProductsFetched(FlashHomeCombined flashHomeCombined) {
        progressBar.setVisibility(View.GONE);
        maxPageLimit = flashHomeCombined.homeAPIResponse.getoUTPUT().getnAVIGATION().getmAX_PAGES();
        mainRecyclerAdapter = new MainRecyclerAdapter(this,
                flashHomeCombined.flashAPIResponse.getoUTPUT().getdATA().getiTEMS(),
                flashHomeCombined.homeAPIResponse.getoUTPUT().getdATA().getiTEMS());
        mainRecycler.setAdapter(mainRecyclerAdapter);
    }

    @Override
    public void onHomeProductsFetched(HomeAPIResponse homeAPIResponse) {
        mainRecyclerAdapter.loadMoreItems(homeAPIResponse.getoUTPUT().getdATA().getiTEMS());
        mainRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNetworkFailure() {

    }
}
