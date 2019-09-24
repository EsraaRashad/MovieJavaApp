package com.example.esraarashad.httpurlconnectionexample.homepackage.presenter;

import com.example.esraarashad.httpurlconnectionexample.homepackage.model.IHomeModel;
import com.example.esraarashad.httpurlconnectionexample.homepackage.view.IHomeView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.*;

public class HomePresenterTest {
    @Mock
    IHomeModel model;
    IHomeView view;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void asyncPopular() {
    }
}