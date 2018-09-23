package com.nagarro.dealapplication.api;

import com.nagarro.dealapplication.viewmodel.OfferViewModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestServiceProtocol {
    static final String OFFER_DETAILS = "/guest/{guestId}/app-instance/{appInstance}";

    @GET(OFFER_DETAILS)
    Call<OfferViewModel> getOffers();
}
