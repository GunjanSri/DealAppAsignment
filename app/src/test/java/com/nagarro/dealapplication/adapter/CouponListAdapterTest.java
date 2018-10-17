package com.nagarro.dealapplication.adapter;

import com.nagarro.dealapplication.model.Coupon;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class CouponListAdapterTest {
    CouponListAdapter couponListAdapter;

    @Before
    public void setUp() {
        couponListAdapter = new CouponListAdapter(
                RuntimeEnvironment.application
        );
    }

    @Test
    public void getItemCount_returnsExpectedItemSize(){
        List<Coupon> couponList = createCouponList();

        couponListAdapter.setCouponList(couponList);

        assertThat(couponListAdapter.getItemCount(), is(2));
    }

    private List<Coupon> createCouponList(){
        List<Coupon> couponList = new ArrayList<>();
        couponList.add(new Coupon("name" , "description" , "icon" ,
                "location" , "validity" , "code"));
        couponList.add(new Coupon("name" , "description" , "icon" ,
                "location" , "validity" , "code"));

        return couponList;
    }
}