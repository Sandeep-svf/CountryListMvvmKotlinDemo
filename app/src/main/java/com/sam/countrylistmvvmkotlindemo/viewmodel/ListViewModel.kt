package com.sam.countrylistmvvmkotlindemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sam.countrylistmvvmkotlindemo.model.CountriesService
import com.sam.countrylistmvvmkotlindemo.model.Country
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel : ViewModel() {

    private val countriesService = CountriesService()
    private val disposable = CompositeDisposable() // send view model using  Rx java to get the info from the service
    // when view model is close we need to close the connection...with the help of this variable....

    val countries = MutableLiveData<List<Country>>()  //
    val countryLoadError = MutableLiveData<Boolean>()   // true means error false means no error
    val loading = MutableLiveData<Boolean>()    // it define is data loading from server

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        // actual fun of view model is here...

      /*  val mockData : List<Country> = listOf(
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

        countries.value = mockData*/

        loading.value = true
        disposable.add(
            countriesService.getCountries() // call countriesSerives with fun getcountries which return single list country
                // we don't want to call this info from the main thread of the application...
                .subscribeOn(Schedulers.newThread()) // all process get countries does on the new thread
                .observeOn(AndroidSchedulers.mainThread()) // info need to be same thread user is seeing which is main thread
                // getting info on the main thread here...
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){ // what we will fo when we get info...
                    override fun onSuccess(t: List<Country>) {
                        countries.value = t
                        countryLoadError.value = false
                        loading.value = false

                    }

                    override fun onError(e: Throwable) {
                        countryLoadError.value = true
                        loading.value = false
                    }

                })

        )


    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}