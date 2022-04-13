package com.devzillas.mytaskkotlin.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import android.view.ViewGroup
import android.widget.Toast

import com.devzillas.mytaskkotlin.R
import com.devzillas.mytaskkotlin.databinding.FragmentInformationClientBinding
import com.devzillas.mytaskkotlin.utile.Resource
import com.devzillas.mytaskkotlin.viewmodel.InformationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InformationClientFragment : Fragment(R.layout.fragment_information_client) {


    private val viewModel: InformationViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentInformationClientBinding.bind(view)

        viewModel.client_information.observe(viewLifecycleOwner) {

            Log.d("TAG", "onViewCreated: $it")

                /*is Resource.Error -> {

                    it.message?.let { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                        Log.e("MyTag", "Error: $message")
                    }
                }
                is Resource.Loading -> {
                    Log.d("MyTag", "onViewCreated:")
                }*/

        }
    }


}