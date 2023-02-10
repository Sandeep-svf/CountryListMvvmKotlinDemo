package com.sam.countrylistmvvmkotlindemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sam.countrylistmvvmkotlindemo.R
import com.sam.countrylistmvvmkotlindemo.model.Country

class CountryListAdapter(var countries : ArrayList<Country> ) : RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryViewHolder (
       LayoutInflater.from(parent.context).inflate(R.layout.item_country,parent,false)
    )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
       holder.bind(countries[position])
    }

    override fun getItemCount(): Int = countries.size



    class CountryViewHolder(view : View) : RecyclerView.ViewHolder(view){

        val countryName = view.name

        fun bind(country : Country){

            countryName.text = country.countryName
        }
    }
}