package com.example.drawerbottomnavapplication.fragment.onboarding.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.drawerbottomnavapplication.R
import com.example.drawerbottomnavapplication.databinding.FragmentOnBoardingScreen1Binding
import com.example.drawerbottomnavapplication.fragment.onboarding.interfacedata.ViewPagerInteraction

class OnBoardingScreen1Fragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingScreen1Binding
    private var viewPagerInteraction: ViewPagerInteraction? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_on_boarding_screen1,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPagerInteraction = requireParentFragment() as? ViewPagerInteraction

        binding.tvSkip.setOnClickListener {
            findNavController().navigate(R.id.onBoardingScreen3Fragment)
        }

        binding.btnNext.setOnClickListener {
            viewPagerInteraction?.setCurrentItem(1)
        }


    }



}