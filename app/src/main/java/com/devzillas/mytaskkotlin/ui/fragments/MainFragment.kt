package com.devzillas.mytaskkotlin.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.devzillas.mytaskkotlin.R
import com.devzillas.mytaskkotlin.databinding.FragmentInformationClientBinding
import com.devzillas.mytaskkotlin.utile.NetworkLinstener
import com.devzillas.mytaskkotlin.viewmodel.InformationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), View.OnClickListener {
    lateinit var navController: NavController
    private var _binding: FragmentInformationClientBinding? = null




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view :View = inflater.inflate(R.layout.fragment_main, container, false)
            return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.send_information_btn).setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.send_information_btn -> navController!!.navigate(R.id.action_mainFragment_to_informationClientFragment)
        }
    }
}