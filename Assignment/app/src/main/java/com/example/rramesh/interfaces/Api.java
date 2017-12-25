package com.example.rramesh.interfaces;

import com.example.rramesh.model.City;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ramesh on 12/23/2017.
 */

public interface Api {
    String BASE_URL = "http://express-it.optusnet.com.au/";

    @GET("sample.json")
    Call<List<City>> getCities();
}
