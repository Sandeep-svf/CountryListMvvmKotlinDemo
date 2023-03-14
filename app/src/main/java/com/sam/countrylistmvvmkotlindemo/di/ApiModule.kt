package com.sam.countrylistmvvmkotlindemo.di

import com.sam.countrylistmvvmkotlindemo.model.CountriesApi
import com.sam.countrylistmvvmkotlindemo.model.CountriesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://raw.githubusercontent.com"

    @Provides
    fun provideCountriesApi(): CountriesApi{
        return Retrofit.Builder() // Create framwork for the retrofit which help to get information from backend
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())  // transform json code int o kotlin data classes
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //adapter that convert data classes file into mutable live data variable...
            .build()
            .create(CountriesApi::class.java)
    }

    @Provides
    fun provideCountriesService(): CountriesService {
        return CountriesService()
    }

}