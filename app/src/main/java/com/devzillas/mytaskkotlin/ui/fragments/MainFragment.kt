package com.devzillas.mytaskkotlin.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.devzillas.mytaskkotlin.R
import com.devzillas.mytaskkotlin.databinding.FragmentInformationClientBinding
import com.devzillas.mytaskkotlin.databinding.FragmentMainBinding
import com.devzillas.mytaskkotlin.utile.NetworkLinstener
import com.devzillas.mytaskkotlin.viewmodel.InformationViewModel
import com.devzillas.mytaskkotlin.viewmodel.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main){
    lateinit var navController: NavController
    private val viewModel: MainFragmentViewModel by viewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val  binding =  FragmentMainBinding.bind(view)
        binding.apply {
            btnNextLevel.setOnClickListener {
                Log.d("TAG", "onViewCreated:")
                viewModel.client_info
               // navController!!.navigate(R.id.action_mainFragment_to_informationClientFragment)
               viewModel.client_info.observe(viewLifecycleOwner) {
                   it.data?.address
                   Log.d("TAG", "onViewCreated:dsadadasdad")
                   /* it.data?.map { information ->


                    }*/
                }
            }
        }

    }

}
