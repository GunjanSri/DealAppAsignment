package com.nagarro.dealapplication.adapter;

import com.nagarro.dealapplication.model.Category;

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
public class CategoryListAdapterTest {

    CategoryListAdapter categoryListAdapter;

    @Before
    public void setUp() {

        categoryListAdapter = new CategoryListAdapter(
                RuntimeEnvironment.application
        );
    }

    @Test
    public void getItemCount_returnsExpectedItemSize(){
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("Food" , "https://www.icon.com"));
        categoryList.add(new Category("Movies" , "https://www.icon.com"));

        categoryListAdapter.setCategoryList(categoryList);

        assertThat(categoryListAdapter.getItemCount(), is(2));
    }
}