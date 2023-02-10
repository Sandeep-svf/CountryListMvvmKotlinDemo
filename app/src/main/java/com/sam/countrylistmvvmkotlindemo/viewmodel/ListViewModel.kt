package com.sam.countrylistmvvmkotlindemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sam.countrylistmvvmkotlindemo.model.Country

class ListViewModel : ViewModel() {

    val countries = MutableLiveData<List<Country>>()  //
    val countryLoadError = MutableLiveData<Boolean>()   // true menas error false means no error
    val loading = MutableLiveData<Boolean>()    // it define is data loading from server

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        // actual fun of view model is here...

        val mockData : List<Country> = listOf(
            Country("countryA"),
            Country("CountryB"),
            Country("CountryC"),
            Country("CountryD"),
            Country("CountryE"),
            Country("CountryF"),
            Country("CountryG"),
            Country("CountryH"),
            Country("CountryI"),
            Country("CountryJ"))

        countryLoadError.value = false
        loading.value = false

        countries.value = mockData

    }


}