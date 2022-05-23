package com.oscar.movilidad.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blongho.country_data.World
import com.oscar.movilidad.R
import com.oscar.movilidad.model.Country

class CountryAdapter(
    val onItemSelected: (country: Country) -> (Unit)
): ListAdapter<Country, CountryAdapter.ViewHolder>(object: DiffUtil.ItemCallback<Country>(){
    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.countryName == newItem.countryName
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.name)
        val capital = view.findViewById<TextView>(R.id.capital)
        val region = view.findViewById<TextView>(R.id.region)
        val iconButton = view.findViewById<ImageView>(R.id.icon)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = getItem(position)

        holder.name.text = country.countryName

        val infoCountry = World.getCountryFrom(country.countryShortName.lowercase())
        holder.capital.text = infoCountry.capital
        holder.region.text = infoCountry.continent
        holder.iconButton.setImageResource(infoCountry.flagResource)

        holder.itemView.setOnClickListener {
            onItemSelected(country)
        }

    }


}