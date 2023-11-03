package com.example.drawerbottomnavapplication.fragment.stepview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.drawerbottomnavapplication.R
import com.example.drawerbottomnavapplication.databinding.FragmentAddressDetailsBinding


class AddressDetailsFragment : Fragment() {

    private lateinit var binding: FragmentAddressDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_address_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun validation(): Boolean {

        binding.tilVillageName.isErrorEnabled = false
        binding.tilDistName.isErrorEnabled = false
        binding.tilPinCode.isErrorEnabled = false
        binding.tilFullAddress.isErrorEnabled = false


        if (binding.etVillageName.text.toString().trim().isEmpty()) {
            binding.tilVillageName.error = "Please Enter Village Name"
            binding.etVillageName.requestFocus()
            return false
        } else if (binding.etDistName.text.toString().trim().isEmpty()) {
            binding.tilDistName.error = "Please Enter District Name"
            binding.etDistName.requestFocus()
            return false
        } else if (binding.etPinCode.text.toString().trim().isEmpty()) {
            binding.tilPinCode.error = "Please Enter PinCode"
            binding.etPinCode.requestFocus()
            return false
        } else if (binding.etFullAddress.text.toString().trim().isEmpty()) {
            binding.tilFullAddress.error = "Please Enter Full Address"
            binding.etFullAddress.requestFocus()
            return false
        } else {
            return true
        }
    }


}