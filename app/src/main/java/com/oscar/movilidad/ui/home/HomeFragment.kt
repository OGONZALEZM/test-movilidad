package com.oscar.movilidad.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.oscar.movilidad.R
import com.oscar.movilidad.databinding.HomeFragmentBinding
import com.oscar.movilidad.model.Country
import com.oscar.movilidad.network.Resource
import com.oscar.movilidad.ui.adapter.CountryAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: HomeFragmentBinding

    private lateinit var countryAdapter: CountryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)

        countryAdapter = CountryAdapter {
            showCountry(it)
        }
        binding.countryList.adapter = countryAdapter



        addObserver()
        getCountries()

        return binding.root
    }

    private fun addObserver() {
        viewModel.countriesLiveData.observe(viewLifecycleOwner) {
            countryAdapter.submitList(it)
        }
    }

    private fun getCountries() {
        viewModel.getCountries().observe(viewLifecycleOwner) {
            when(it.status) {
                Resource.Status.LOADING -> {
                    binding.message.visibility = View.VISIBLE
                    binding.message.text = resources.getText(R.string.loading)
                }
                Resource.Status.SUCCESS -> {
                    binding.message.visibility = View.GONE
                }
                Resource.Status.ERROR -> {
                    binding.message.text = it.message
                    if (it.data == false) getToken()
                }
            }
        }
    }

    private fun getToken() {
        viewModel.getAccessToken().observe(viewLifecycleOwner) {
            when(it.status) {
                Resource.Status.LOADING -> {
                    binding.message.visibility = View.VISIBLE
                    binding.message.text = resources.getText(R.string.getting_token)
                }
                Resource.Status.SUCCESS -> {
                    getCountries()
                }
                Resource.Status.ERROR -> {
                    binding.message.text = it.message
                }
            }
        }
    }

    private fun showCountry(country: Country) {
        val gmmIntentUri = Uri.parse("https://www.google.com/maps/place/country+${country.countryName.replace(" ", "+")}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

}