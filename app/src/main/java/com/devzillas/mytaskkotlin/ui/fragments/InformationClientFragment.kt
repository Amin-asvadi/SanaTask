package com.devzillas.mytaskkotlin.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import android.widget.Toast

import com.devzillas.mytaskkotlin.R
import com.devzillas.mytaskkotlin.viewmodel.InformationViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InformationClientFragment : Fragment(R.layout.fragment_information_client) {


    private val viewModel: InformationViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.client_information.observe(viewLifecycleOwner) {
            it.data?.map {information ->

    Log.d("TAG", "onViewCreated: ${information.address.indexOf("")}")
}

            /*when (it) {


               //     it.data?.let { newsResponse ->
                        Log.d("TAG", "onViewCreated: $it")
                        *//*  articleAdapter.submitList(newsResponse..toList())
                          val totalPages = newsResponse.totalResults / QUERY_PAGE_SIZE + 2
                          isLastPage = viewModel.breakingNewsPage == totalPages
                          if(isLastPage)
                              rvBreakingNews.setPadding(0,0,0,0)*//*
                 //   }


            }*/

        }
    }

}