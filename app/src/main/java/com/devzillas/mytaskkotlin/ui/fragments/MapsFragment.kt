package com.devzillas.mytaskkotlin.ui.fragments

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.devzillas.mytaskkotlin.R
import com.devzillas.mytaskkotlin.data.model.Location
import com.devzillas.mytaskkotlin.data.model.Name
import com.devzillas.mytaskkotlin.databinding.FragmentMainBinding
import com.devzillas.mytaskkotlin.databinding.FragmentMapsBinding
import com.devzillas.mytaskkotlin.utile.NetworkDataState
import com.devzillas.mytaskkotlin.viewmodel.MapFragmentViewModel

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapsFragment : Fragment() {

    val location = Location()
    private val viewModel: MapFragmentViewModel by viewModels()
    lateinit var navController: NavController

    private val callback = OnMapReadyCallback { googleMap ->

        googleMap.setOnMapClickListener(object : GoogleMap.OnMapClickListener {
            override fun onMapClick(latlng: LatLng) {
                var latLng = latlng
                // Clears the previously touched position
                googleMap.clear();
                // Animating to the touched position
                googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                location.lat = listOf(latLng.latitude)
                location.long = listOf(latLng.longitude)
                val location = LatLng(latlng.latitude, latlng.longitude)
                googleMap.addMarker(MarkerOptions().position(location))
            }
        })
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMapsBinding.bind(view)
        navController = Navigation.findNavController(view)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        binding.apply {
            btnNextToDetails.setOnClickListener {
                if (location.lat.isEmpty()) {
                    Toast.makeText(context, "لطفا موقعیت مکانی را تعیین نمایید", Toast.LENGTH_LONG)
                        .show()
                } else {

                    putData(longtiute = location.long.get(0), latiute = location.lat.get(0))

                    viewModel.client_info.observe(viewLifecycleOwner) {
                        when (it) {
                            is NetworkDataState.Success -> {
                                paginationProgressBar.visibility = View.INVISIBLE
                                navController!!.navigate(R.id.action_mapsFragment_to_detailsFragment)
                            }
                            is NetworkDataState.Loading -> {
                                paginationProgressBar.visibility = View.VISIBLE
                            }
                            is NetworkDataState.Error -> {
                                Toast.makeText(context,"Error",Toast.LENGTH_LONG).show()
                            }
                            /* it.data?.map { information ->


                         }*/
                        }
                    }

                }

            }

        }
    }

    fun putData(latiute: Double, longtiute: Double) {
        val name = Name(
            address = requireArguments().getString("address").toString(),
            coordinate_mobile = requireArguments().getString("coordinate_mobile")
                .toString(),
            coordinate_phone_number = requireArguments().getString("coordinate_phone_number")
                .toString(),
            first_name = requireArguments().getString("first_name").toString(),
            gender = requireArguments().getString("gender").toString(),
            last_name = requireArguments().getString("last_name").toString(),
            lat = listOf(latiute),
            lng = listOf(longtiute),
            1
        )
        viewModel.address.postValue(name)
    }
}