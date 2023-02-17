package com.sam.countrylistmvvmkotlindemo.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountriesService {
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api : CountriesApi

    init {
        api = Retrofit.Builder() // Create framwork for the retrofit which help to get information from backend
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())  // transform json code int o kotlin data classes
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //adapter that convert data classes file into mutable live data variable...
            .build()
            .create(CountriesApi::class.java)
    }

    fun getCountries(): Single<List<Country>>{
        return api.getCountries()
    }
}