package com.example.esraarashad.httpurlconnectionexample.homepackage.presenter

import com.example.esraarashad.httpurlconnectionexample.homepackage.model.IHomeModel
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults
import com.example.esraarashad.httpurlconnectionexample.homepackage.view.IHomeView
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations

class HomePresenterTest {
    lateinit var presenter :HomePresenter
    lateinit var list: ArrayList<PeopleResults>

    @Mock
    lateinit var iHomeView: IHomeView
    @Mock
    lateinit var iHomeModel: IHomeModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = HomePresenter(iHomeView,iHomeModel)
        list= ArrayList<PeopleResults>()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun asyncPopular() {
        presenter.asyncPopular()
        verify(iHomeView).setRecyclerViewAndAdapter(list)
    }
}