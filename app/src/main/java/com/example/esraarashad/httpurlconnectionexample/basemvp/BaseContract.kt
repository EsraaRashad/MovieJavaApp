package com.example.esraarashad.httpurlconnectionexample.basemvp

import android.os.Bundle
import android.support.annotation.LayoutRes

interface BaseContract {
    interface BaseIView{

        fun onViewReady(bundle: Bundle?)
        fun showLoading()
        fun hideLoading()
        fun showError(error: Int)
        fun showError(error: String)
        @LayoutRes
        fun getLayoutResourceId(): Int
    }

    interface BaseIPresenter{
        fun onViewReady()
        fun onViewDestroy()
    }

    interface BaseIRepository{

    }
}