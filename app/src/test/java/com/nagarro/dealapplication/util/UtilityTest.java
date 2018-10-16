package com.nagarro.dealapplication.util;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import okhttp3.internal.Util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricTestRunner.class)
public class UtilityTest {

    Utility utility;
    Context contextMock;

    @Before
    public void setUp(){
        utility = new Utility();
        contextMock = mock(Context.class);
    }
}