package com.sam.countrylistmvvmkotlindemo.di

import com.sam.countrylistmvvmkotlindemo.model.CountriesService
import com.sam.countrylistmvvmkotlindemo.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CountriesService)
    fun inject(viewModel: ListViewModel)


}