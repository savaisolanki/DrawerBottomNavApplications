package com.example.drawerbottomnavapplication.fragment.stepview.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.drawerbottomnavapplication.R
import com.example.drawerbottomnavapplication.databinding.FragmentBasicDetailsBinding
import java.util.Calendar


class BasicDetailsFragment : Fragment() {

    private lateinit var binding: FragmentBasicDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_basic_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpGender()
        setUpDatePicker()
        setSubSubscription()
    }

    fun validation(): Boolean {

        binding.tilFullName.isErrorEnabled = false
        binding.tilGender.isErrorEnabled = false
        binding.tilDob.isErrorEnabled = false


        if (binding.etFullName.text.toString().trim().isEmpty()) {
            binding.tilFullName.error = "Enter Full Name"
            binding.etFullName.requestFocus()
            return false
        } else if (binding.autoCompleteGender.text.toString().trim().isEmpty()) {
            binding.tilGender.error = "Please select from list"
            binding.autoCompleteGender.requestFocus()
            return false
        } else if (binding.etDob.text.toString().trim().isEmpty()) {
            binding.tilDob.error = "Please select date"
            binding.etDob.requestFocus()
            return false
        } else if (binding.autoSubscription.text.toString().trim().isEmpty()) {
            binding.tilSubscription.error = "Please select from list"
            binding.autoSubscription.requestFocus()
            return false
        } else {
            return true
        }
    }


    @SuppressLint("SetTextI18n")
    private fun setUpDatePicker() {
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val cYear = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(), { _, year, monthOfYear, dayOfMonth ->
                binding.etDob.setText("" + (monthOfYear + 1) + "/" + dayOfMonth + "/" + year)
                binding.tilDob.helperText = "Your age is ${cYear - year} years"

            }, year, month, day
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()

        binding.etDob.setOnClickListener {
            datePickerDialog.show()
        }

    }

    private fun setUpGender() {
        val itemsGender = listOf("Male", "Female")

        val gender = resources.getStringArray(R.array.gender)
        val adapterGender = ArrayAdapter(
            requireContext(),
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            itemsGender
        )
        binding.autoCompleteGender.setAdapter(adapterGender)

    }


    private fun setSubSubscription() {
        val subSubscription = resources.getStringArray(R.array.subscription)
        val itemsSubscription = listOf("1 Months Plan Ruppies:4000", "2 Months Plan Ruppies:6000","3 Months Plan Ruppies:8000")

        val adapterSubSubscription = ArrayAdapter(
            requireContext(),
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            subSubscription
        )
        binding.autoSubscription.setAdapter(adapterSubSubscription)


    }


}