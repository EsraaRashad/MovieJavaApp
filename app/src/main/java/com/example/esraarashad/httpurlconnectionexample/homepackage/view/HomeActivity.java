package com.example.esraarashad.httpurlconnectionexample.homepackage.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.HomeDataNetwork;
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults;
import com.example.esraarashad.httpurlconnectionexample.R;
import com.example.esraarashad.httpurlconnectionexample.homepackage.presenter.HomePresenter;
import org.json.JSONException;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements IHomeView {

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private LinearLayoutManager layoutManager;
    private boolean isScrolling = false;
    int currentItems,totalItems , scrollOutItems;
    private ProgressBar progressBar;
    private int i=1;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Boolean isLoading = false;
    private ArrayList<PeopleResults> peopleResultsList;
    private HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        peopleResultsList = new ArrayList<>();
        presenter = new HomePresenter(this, new HomeDataNetwork());
        progressBar=findViewById(R.id.progress);
        swipeRefreshLayout = findViewById(R.id.simpleSwipeRefreshLayout);
        layoutManager=new LinearLayoutManager(HomeActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView = findViewById(R.id.my_recycler_view);
        //getAsyncPopularObj();
        progressBar.setVisibility(View.GONE);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                isScrolling=true;
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems=layoutManager.getChildCount();
                totalItems=layoutManager.getItemCount();
                scrollOutItems=layoutManager.findFirstVisibleItemPosition();
                if (isScrolling && (currentItems + scrollOutItems == totalItems)){
                    isScrolling = false ;
                    presenter.updatePage(i);
                    progressBar.setVisibility(View.VISIBLE);
                }

            }
        });

        // implement setOnRefreshListener event on SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // implement Handler to wait for 3 seconds and then update UI means update value of TextView
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // cancle the Visual indication of a refresh
                        // clear the list
                        getPeopleResultsList().clear();
                        mAdapter.notifyDataSetChanged();
                        getAsyncPopularObj();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        getAsyncPopularObj();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem mSearchItem = menu.findItem(R.id.menu_search);
        final SearchView mSearchView = (SearchView) mSearchItem.getActionView();
        View mCloseButton = mSearchView.findViewById(getResources().getIdentifier("android:id/search_close_btn",null,null));

        mSearchView.setQueryHint("Search by name...");
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.isEmpty()) {
                    if(!isLoading) {
                        //Clear then make a request for search
                        getPeopleResultsList().clear();
                        mAdapter.notifyDataSetChanged();
                        getAsyncSearch(newText);
                        }

                    else{
                        //Cancel the current async task and request the new one
                        isLoading=false;
                        getPeopleResultsList().clear();
                        mAdapter.notifyDataSetChanged();
                        getAsyncSearch(newText);
                    }
                } else {
                    //currentPage = 1
                    if(!isLoading) {
                        getPeopleResultsList().clear();
                        mAdapter.notifyDataSetChanged();
                        getAsyncPopularObj();
                    }
                    else {
                        isLoading = false;
                        getPeopleResultsList().clear();
                        Log.i("list",getPeopleResultsList().size()+"");
                        mAdapter.notifyDataSetChanged();
                        getAsyncPopularObj();
                    }
                }
                return false;
            }
        });

        //To override what happens when x is clicked on in searchView
        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mSearchView.getQuery().toString().isEmpty()) {
                    mSearchView.setQuery("", false);
                    mSearchView.clearFocus();
                    //currentPage = 1
                    getPeopleResultsList().clear();
                    mAdapter.notifyDataSetChanged();
                    getAsyncPopularObj();
                } else
                    mSearchView.onActionViewCollapsed();
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    public void setToastErrMsg(JSONException e){
        Toast.makeText(HomeActivity.this, e.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void sendSearchedText(String text) {

    }

    public void setRecyclerViewAndAdapter(ArrayList<PeopleResults> list){
        peopleResultsList.addAll(list);
        mAdapter = new MyAdapter( HomeActivity.this,list);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        notifyChangesInAdapter(mAdapter);
        progressBar.setVisibility(View.GONE);
    }

    public ArrayList<PeopleResults> getPeopleResultsList(){
        return peopleResultsList;
    }
    @Override
    public void notifyChangesInAdapter(MyAdapter adapter) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getAsyncPopularObj() {
        presenter.asyncPopular();
    }

    @Override
    public void getAsyncSearch(String text) {
        presenter.asyncSearch(text);
    }
}

