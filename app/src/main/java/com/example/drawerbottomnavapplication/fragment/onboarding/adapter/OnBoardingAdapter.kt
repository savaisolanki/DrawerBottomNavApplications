package com.example.drawerbottomnavapplication.fragment.onboarding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.drawerbottomnavapplication.fragment.onboarding.ui.OnBoardingScreen1Fragment
import com.example.drawerbottomnavapplication.fragment.onboarding.ui.OnBoardingScreen2Fragment
import com.example.drawerbottomnavapplication.fragment.onboarding.ui.OnBoardingScreen3Fragment


class OnBoardingAdapter(
    fragmentManager: FragmentManager, lifecycle: Lifecycle
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnBoardingScreen1Fragment()
            1 -> OnBoardingScreen2Fragment()
            2 -> OnBoardingScreen3Fragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}
