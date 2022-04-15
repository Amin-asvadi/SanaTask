package com.devzillas.mytaskkotlin.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.devzillas.mytaskkotlin.R
import com.devzillas.mytaskkotlin.data.model.Name
import com.devzillas.mytaskkotlin.databinding.FragmentMainBinding
import com.devzillas.mytaskkotlin.utile.NetworkLinstener
import com.devzillas.mytaskkotlin.viewmodel.MapFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    lateinit var navController: NavController
    lateinit var radioButton: RadioButton
    lateinit var gender: String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val binding = FragmentMainBinding.bind(view)
        gender = ""
        binding.includeAddress.toggle.setOnCheckedChangeListener { radioGroup, i ->

            var rb = view.findViewById<RadioButton>(i)
            rb.setTextColor(Color.WHITE)
            rb.setOnCheckedChangeListener { compoundButton, b ->
                if (!b)
                    rb.setTextColor(Color.parseColor("#03A9F4"))
            }
            if (rb != null)
                gender = if (rb.text.toString() == "آقا") "Male" else "Female"
        }

        binding.apply {


            btnNextLevel.setOnClickListener {
                if (!NetworkLinstener.hasInternetConnection(requireContext())) {
                    Toast.makeText(context, "اینترنت را بررسی نمایید", Toast.LENGTH_LONG).show()
                } else if (gender.isEmpty() || icludeEdt.edtName.length() < 3 || icludeEdt.edtFamily.length() < 3 ||
                    icludeEdt.edtMobile.length() < 11
                ) {
                    Toast.makeText(context, "فیلد ها را تکمیل نمایید", Toast.LENGTH_LONG).show()
                } else if (icludeEdt.edtStaticMobile.length() < 11) {
                    icludeEdt.edtStaticMobile.setError("تعداد ارقام صحیح وارد نمایید")
                } else if (icludeEdt.edtMobile.length() < 11) {
                    icludeEdt.edtMobile.setError("تعداد ارقام صحیح وارد نمایید")
                } else {
                    val bundle = bundleOf(
                        "address" to includeAddress.edtGetAddress.text.toString(),
                        "coordinate_mobile" to icludeEdt.edtMobile.text.toString().trim(),
                        "coordinate_phone_number" to icludeEdt.edtStaticMobile.text.toString()
                            .trim(),
                        "first_name" to icludeEdt.edtName.text.toString(),
                        "gender" to gender,
                        "last_name" to icludeEdt.edtFamily.text.toString(),
                        "region" to icludeEdt.edtFamily.text.toString()
                    )
                    navController!!.navigate(R.id.action_mainFragment_to_mapsFragment, bundle)
                }
            }
        }
    }
}
