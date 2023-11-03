package com.example.drawerbottomnavapplication.fragment.stepview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.drawerbottomnavapplication.R
import com.example.drawerbottomnavapplication.databinding.FragmentPaymentDetailsBinding


class PaymentDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPaymentDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_payment_details, container, false)
        return binding.root
    }


    fun validation(): Boolean {

        binding.tilCardHolderName.isErrorEnabled = false
        binding.tilCardNumber.isErrorEnabled = false
        binding.tilCardCvv.isErrorEnabled = false


        if (binding.etCardHolderName.text.toString().trim().isEmpty()) {
            binding.tilCardHolderName.error = "Please Enter Card Holder Name"
            binding.etCardHolderName.requestFocus()
            return false
        } else if (binding.etCardNumber.text.toString().trim().isEmpty()) {
            binding.tilCardNumber.error = "Please Enter Card Number"
            binding.etCardNumber.requestFocus()
            return false
        } else if (binding.etCardCvv.text.toString().trim().isEmpty()) {
            binding.tilCardCvv.error = "Please Enter Card Cvv"
            binding.etCardCvv.requestFocus()
            return false
        } else {
            return true
        }
    }


}