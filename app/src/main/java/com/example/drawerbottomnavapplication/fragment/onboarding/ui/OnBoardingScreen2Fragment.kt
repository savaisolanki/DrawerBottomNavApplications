package com.example.drawerbottomnavapplication.fragment.onboarding.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.drawerbottomnavapplication.R
import com.example.drawerbottomnavapplication.activity.ui.MainActivity
import com.example.drawerbottomnavapplication.databinding.FragmentOnBoardingScreen2Binding
import com.example.drawerbottomnavapplication.fragment.onboarding.interfacedata.ViewPagerInteraction
import com.example.drawerbottomnavapplication.utils.Utils


class OnBoardingScreen2Fragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingScreen2Binding
    private var viewPagerInteraction: ViewPagerInteraction? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_on_boarding_screen2,
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
            viewPagerInteraction?.setCurrentItem(2)
        }

        binding.btnBack.setOnClickListener {
            viewPagerInteraction?.setCurrentItem(0)
        }
    }


}