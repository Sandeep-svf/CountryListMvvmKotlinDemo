package com.sam.countrylistmvvmkotlindemo.model

import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.GET

interface CountriesApi {

    @GET("DevTides/countries/master/countriesV2.json")
    fun getCountries(@Field ("name ") name : String): Single<List<Country>>


}