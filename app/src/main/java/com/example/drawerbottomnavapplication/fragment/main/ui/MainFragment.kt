package com.example.drawerbottomnavapplication.fragment.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.drawerbottomnavapplication.R
import com.example.drawerbottomnavapplication.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private lateinit var binding:FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false)
        return binding.root
    }


}