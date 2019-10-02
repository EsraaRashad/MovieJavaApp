package com.example.esraarashad.httpurlconnectionexample.basemvp

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.NonNull
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

abstract class BaseActivity<Presenter: BasePresenter <*,*>>
    : AppCompatActivity(), BaseContract.BaseIView {

    abstract val presenter: Presenter
    @LayoutRes
    abstract override fun getLayoutResourceId(): Int
    abstract override fun onViewReady(bundle: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourceId())
//        implementation

        onViewReady(savedInstanceState)

        presenter.onViewReady()
    }

    override fun showLoading(){
        Toast.makeText(this, "Loading", Toast.LENGTH_LONG).show()
    }

    override fun hideLoading(){
        Toast.makeText(this, "Loaded", Toast.LENGTH_LONG).show()
    }

    override fun showError(@StringRes error: Int){
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showError(@NonNull error: String){
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        presenter.onViewDestroy()
        super.onDestroy()
    }
}