package com.devzillas.mytaskkotlin.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.devzillas.mytaskkotlin.R
import com.devzillas.mytaskkotlin.databinding.FragmentDetailsBinding
import com.devzillas.mytaskkotlin.utile.NetworkDataState
import com.devzillas.mytaskkotlin.viewmodel.DetailsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: DetailsFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailsBinding.bind(view)
        binding.apply {
            lifecycleScope.launch{
                delay(1000L)
                viewModel.client_information.observe(viewLifecycleOwner) {response ->
                    when (response) {
                        is NetworkDataState.Success -> {

                            response.data?.map { information ->
                                expandTextView.text = information.address
                                tvName.text = information.first_name
                                tvFamily.text = information.last_name
                                tvMobile.text = information.coordinate_mobile
                                paginationProgressBarDetailes.visibility = View.INVISIBLE

                            }
                        }
                        is NetworkDataState.Loading -> {
                            paginationProgressBarDetailes.visibility = View.VISIBLE
                        }

                    }
                }
            }

        }

    }
}