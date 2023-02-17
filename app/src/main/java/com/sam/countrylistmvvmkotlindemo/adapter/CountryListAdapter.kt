package com.sam.countrylistmvvmkotlindemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sam.countrylistmvvmkotlindemo.R
import com.sam.countrylistmvvmkotlindemo.model.Country
import com.sam.countrylistmvvmkotlindemo.utils.getProgressDrawable
import com.sam.countrylistmvvmkotlindemo.utils.loadImage
import kotlinx.android.synthetic.main.item_country.view.*


class CountryListAdapter(var countries : ArrayList<Country> ) : RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {


    // a way to update country whenever need to
    fun updateCountries(newCountries: List<Country>){
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryViewHolder (
       LayoutInflater.from(parent.context).inflate(R.layout.item_country,parent,false)
    )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
       holder.bind(countries[position])
    }

    override fun getItemCount(): Int = countries.size



    class CountryViewHolder(view : View) : RecyclerView.ViewHolder(view){

        private val countryName = view.name
        private val countryCapital = view.capital
        private val countryFlag = view.imageView
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(country : Country){

            countryName.text = country.countryName
            countryCapital.text = country.capital
            countryFlag.loadImage(country.flag, progressDrawable)
        }
    }
}