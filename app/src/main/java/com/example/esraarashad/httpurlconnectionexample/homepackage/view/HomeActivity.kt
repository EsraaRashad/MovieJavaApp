package com.example.esraarashad.httpurlconnectionexample.homepackage.view

import android.os.Bundle
import android.os.Handler
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.HomeModel
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults
import com.example.esraarashad.httpurlconnectionexample.R
import com.example.esraarashad.httpurlconnectionexample.homepackage.presenter.HomePresenter
import org.json.JSONException
import java.util.ArrayList

class HomeActivity : AppCompatActivity(), IHomeView {

    private var recyclerView: RecyclerView? = null
    private var mAdapter: MyAdapter? = null
    private var layoutManager: LinearLayoutManager? = null
    private var isScrolling = false
    internal var currentItems: Int = 0
    internal var totalItems: Int = 0
    internal var scrollOutItems: Int = 0
    private var progressBar: ProgressBar? = null
    private val i = 1
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var isLoading: Boolean? = false
    var peopleResultsList: ArrayList<PeopleResults>? = null
        private set
    private var presenter: HomePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        peopleResultsList = ArrayList()
        presenter = HomePresenter(this, HomeModel())
        progressBar = findViewById(R.id.progress)
        swipeRefreshLayout = findViewById(R.id.simpleSwipeRefreshLayout)
        layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
        recyclerView = findViewById(R.id.my_recycler_view)
        //getAsyncPopularObj();
        progressBar!!.visibility = View.GONE

        recyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                isScrolling = true
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItems = layoutManager!!.childCount
                totalItems = layoutManager!!.itemCount
                scrollOutItems = layoutManager!!.findFirstVisibleItemPosition()
                if (isScrolling && currentItems + scrollOutItems == totalItems) {
                    isScrolling = false
                    presenter!!.updatePage(i)
                    progressBar!!.visibility = View.VISIBLE
                }

            }
        })
        // implement setOnRefreshListener event on SwipeRefreshLayout
        swipeRefreshLayout!!.setOnRefreshListener {
            // implement Handler to wait for 3 seconds and then update UI means update value of TextView
            Handler().postDelayed({
                // cancle the Visual indication of a refresh
                // clear the list
                peopleResultsList!!.clear()
//                mAdapter!!.notifyDataSetChanged()
                notifyChangesInAdapter(mAdapter!!)
                getAsyncPopularObj()
                swipeRefreshLayout!!.isRefreshing = false
            }, 3000)
        }
        getAsyncPopularObj()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        val mSearchItem = menu.findItem(R.id.menu_search)
        val mSearchView = mSearchItem.actionView as SearchView
        val mCloseButton = mSearchView.findViewById<View>(resources.getIdentifier("android:id/search_close_btn", null, null))

        mSearchView.queryHint = "Search by name..."
        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (!newText.isEmpty()) {
                    if (!(isLoading)!!) {
                        //Clear then make a request for search
                        Log.i("PeopleResultsList :",peopleResultsList!!.size.toString()+"")
                        peopleResultsList!!.clear()
                        Log.i("PeopleList cleared :",peopleResultsList!!.size.toString()+"")
//                        mAdapter!!.notifyDataSetChanged()
                        notifyChangesInAdapter(mAdapter!!)
                        getAsyncSearch(newText)
                    } else {
                        //Cancel the current async task and request the new one
                        isLoading = false
                        peopleResultsList!!.clear()
//                        mAdapter!!.notifyDataSetChanged()
                        notifyChangesInAdapter(mAdapter!!)
                        getAsyncSearch(newText)
                    }
                } else {
                    //currentPage = 1
                    if (!(isLoading)!!) {
                        peopleResultsList!!.clear()
//                        mAdapter!!.notifyDataSetChanged()
                        notifyChangesInAdapter(mAdapter!!)
                        getAsyncPopularObj()
                    } else {
                        isLoading = false
                        peopleResultsList!!.clear()
                        Log.i("list", peopleResultsList!!.size.toString() + "")
//                        mAdapter!!.notifyDataSetChanged()
                        notifyChangesInAdapter(mAdapter!!)
                        getAsyncPopularObj()
                    }
                }
                return false
            }
        })

        //To override what happens when x is clicked on in searchView
        mCloseButton.setOnClickListener {
            if (!mSearchView.query.toString().isEmpty()) {
                mSearchView.setQuery("", false)
                mSearchView.clearFocus()
                //currentPage = 1
                peopleResultsList!!.clear()
                //mAdapter!!.notifyDataSetChanged()
                notifyChangesInAdapter(mAdapter!!)
                getAsyncPopularObj()
            } else
                mSearchView.onActionViewCollapsed()
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun setToastErrMsg(e: JSONException) {
        Toast.makeText(this@HomeActivity, e.toString(), Toast.LENGTH_LONG).show()
    }

    override fun setRecyclerViewAndAdapter(list: ArrayList<PeopleResults>) {
        peopleResultsList!!.addAll(list)
        mAdapter = MyAdapter(this@HomeActivity, peopleResultsList!!)
        recyclerView!!.adapter = mAdapter
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.setItemViewCacheSize(20)
        recyclerView!!.isDrawingCacheEnabled = true
        notifyChangesInAdapter(mAdapter!!)
        progressBar!!.visibility = View.GONE
    }

    override fun notifyChangesInAdapter(adapter: MyAdapter) {
        adapter.notifyDataSetChanged()
    }

    override fun getAsyncPopularObj() {
        presenter!!.asyncPopular()
    }

    override fun getAsyncSearch(text: String) {
        presenter!!.asyncSearch(text)
    }
}

