package com.example.esraarashad.httpurlconnectionexample.homepackage.presenter

import com.example.esraarashad.httpurlconnectionexample.homepackage.model.IHomeModel
import com.example.esraarashad.httpurlconnectionexample.homepackage.model.PopularPeopleModel.PeopleResults
import com.example.esraarashad.httpurlconnectionexample.homepackage.view.IHomeView
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import com.nhaarman.mockito_kotlin.*;
import org.mockito.Mock
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
        presenter = spy(HomePresenter(iHomeView,iHomeModel))
        list= ArrayList()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun asyncPopular() {
        val page : Int  = 1
        presenter.updatePage(page)
        verify(presenter).asyncOnLoadMorePages(eq(page))
    }
}