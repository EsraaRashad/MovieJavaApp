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
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class HomePresenterTest {
    lateinit var presenter :HomePresenter

    @Mock
    lateinit var iHomeView: IHomeView
    @Mock
    lateinit var iHomeModel: IHomeModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = HomePresenter(iHomeView,iHomeModel)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun asyncPopular() {
        val callback = argumentCaptor<(ArrayList<PeopleResults>?)-> Unit>()
        val list= ArrayList<PeopleResults>()

        Mockito.`when`(iHomeModel.asyncPopularModel (callback.capture()))
                .then{
                    callback.firstValue.invoke(list)
                }
        presenter.asyncPopular()
        verify(iHomeView).setRecyclerViewAndAdapter(list)
    }
}