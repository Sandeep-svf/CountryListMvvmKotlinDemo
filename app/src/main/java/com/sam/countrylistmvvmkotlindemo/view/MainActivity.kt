package com.sam.countrylistmvvmkotlindemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sam.countrylistmvvmkotlindemo.R
import com.sam.countrylistmvvmkotlindemo.adapter.CountryListAdapter
import com.sam.countrylistmvvmkotlindemo.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel    // lateintie -> instantiate this variable before use.....(otherwise crash)
    private val countriesAdapter = CountryListAdapter(arrayListOf())   // passing an empty list here...


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Instantiate view model
        // this is lifecycle typical way of doing this
        // reason of doing this-> android system and background will take care of updating thid view model
        // and destroyed when we dont need it so we will not have any memory loss or problem like that.
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()


        countriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }
        obserViewModel()
    }

    fun obserViewModel(){
        // simply call the variable from view modle...
        viewModel.countries.observe(this, Observer { countries ->
            countries?.let {
                countriesList.visibility = View.VISIBLE
                countriesAdapter.updateCountries(it) // it refer to variable countries..
            }
        })
        viewModel.countryLoadError.observe(this, Observer {isError ->
            isError?.let {
                list_error.visibility = if(it) View.VISIBLE else View.GONE
            }
        })
        viewModel.loading.observe(this, Observer {isLoading ->
            isLoading?.let {
                loading_view.visibility = if(it) View.VISIBLE else View.GONE
                // if(it)   -> if we have loadoing....

                if(it){
                    list_error.visibility = View.GONE
                    countriesList.visibility = View.GONE
                }
            }
        })
    }
}