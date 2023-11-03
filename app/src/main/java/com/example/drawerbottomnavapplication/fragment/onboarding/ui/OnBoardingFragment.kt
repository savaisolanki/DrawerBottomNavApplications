package com.example.drawerbottomnavapplication.fragment.onboarding.ui

import android.annotation.SuppressLint
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
import com.example.drawerbottomnavapplication.databinding.FragmentOnBoardingBinding
import com.example.drawerbottomnavapplication.fragment.onboarding.adapter.OnBoardingAdapter
import com.example.drawerbottomnavapplication.fragment.onboarding.interfacedata.ViewPagerInteraction
import com.example.drawerbottomnavapplication.utils.Utils
import com.google.android.material.tabs.TabLayoutMediator


class OnBoardingFragment : Fragment(), ViewPagerInteraction {

    private lateinit var binding: FragmentOnBoardingBinding
    private lateinit var onBoardingAdapter: OnBoardingAdapter
    private var viewPagerInteraction: ViewPagerInteraction? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_on_boarding, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = requireActivity() as MainActivity
        mainActivity.supportActionBar?.show()
        mainActivity.supportActionBar?.title = "OnBoarding"
        mainActivity.binding.bottomNavigation.visibility = View.GONE
        mainActivity.lockDrawer()
        mainActivity.binding.tbToolBar.setNavigationIcon(R.drawable.ic_arrow_back)
        mainActivity.binding.tbToolBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        Utils.menuHost(
            mainActivity as MenuHost,
            galleryIsVisible = false,
            imageIsVisible = false,
            cameraIsVisible = false
        )

        viewPagerAdapter()
        binding.vpViewPager.isUserInputEnabled = true


    }

    override fun onResume() {
        super.onResume()
        val mainActivity = requireActivity() as MainActivity
        mainActivity.supportActionBar?.show()
        mainActivity.supportActionBar?.title = "OnBoarding"
        mainActivity.binding.bottomNavigation.visibility = View.GONE
        mainActivity.lockDrawer()
        mainActivity.binding.tbToolBar.setNavigationIcon(R.drawable.ic_arrow_back)
        mainActivity.binding.tbToolBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        Utils.menuHost(
            mainActivity as MenuHost,
            galleryIsVisible = false,
            imageIsVisible = false,
            cameraIsVisible = false
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun viewPagerAdapter() {
        onBoardingAdapter = OnBoardingAdapter(childFragmentManager, lifecycle)
        binding.vpViewPager.adapter = onBoardingAdapter

        TabLayoutMediator(binding.tab, binding.vpViewPager) { _, _ -> }.attach()


    }


    override fun setCurrentItem(position: Int) {
        binding.vpViewPager.currentItem = position

    }

}