package com.example.drawerbottomnavapplication.fragment.tablayout.ui

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
import com.example.drawerbottomnavapplication.databinding.FragmentTabLayoutBinding
import com.example.drawerbottomnavapplication.fragment.stepview.ui.AddressDetailsFragment
import com.example.drawerbottomnavapplication.fragment.stepview.ui.BasicDetailsFragment
import com.example.drawerbottomnavapplication.fragment.stepview.ui.PaymentDetailsFragment
import com.example.drawerbottomnavapplication.fragment.tablayout.adapter.TabLayoutPagerAdapter
import com.example.drawerbottomnavapplication.utils.Utils
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class TabLayoutFragment : Fragment() {

    private lateinit var binding: FragmentTabLayoutBinding
    private lateinit var tabLayoutPagerAdapter: TabLayoutPagerAdapter
    private var basicDetailsFragments: BasicDetailsFragment = BasicDetailsFragment()
    private var addressDetailsFragment: AddressDetailsFragment = AddressDetailsFragment()
    private var paymentDetailsFragment: PaymentDetailsFragment = PaymentDetailsFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab_layout, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity = requireActivity() as MainActivity
        mainActivity.supportActionBar?.show()
        mainActivity.supportActionBar?.title = "TabLayout"
        mainActivity.binding.bottomNavigation.visibility = View.GONE
        mainActivity.lockDrawer()
        mainActivity.binding.tbToolBar.setNavigationIcon(R.drawable.ic_arrow_back)
        mainActivity.binding.tbToolBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        Utils.menuHost(mainActivity as MenuHost, galleryIsVisible = false, imageIsVisible = false, cameraIsVisible = false)

        binding.btnNext.setOnClickListener {
            when {
                binding.vpCategoryItem.currentItem == 0 && basicDetailsFragments.validation() -> {
                    binding.vpCategoryItem.currentItem += 1
                }
                binding.vpCategoryItem.currentItem == 1 && addressDetailsFragment.validation() -> {
                    binding.vpCategoryItem.currentItem += 1
                    val name = "submit"
                    binding.btnNext.text = name
                }
                binding.vpCategoryItem.currentItem == 2 && paymentDetailsFragment.validation() -> {
                    binding.vpCategoryItem.currentItem += 1
                    findNavController().popBackStack()
                }
            }
        }
        setTab()
        setViewPager()
        setTabClick()


    }

    override fun onResume() {
        super.onResume()

        val mainActivity = requireActivity() as MainActivity
        mainActivity.supportActionBar?.show()
        mainActivity.supportActionBar?.title = "TabLayout"
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

        setTab()
        setViewPager()
        setTabClick()

    }

    private fun setTab() {
        binding.tlCategoryItem.addTab(
            binding.tlCategoryItem.newTab().setText("Basic Details")
        )
        binding.tlCategoryItem.addTab(
            binding.tlCategoryItem.newTab().setText("Address")
        )
        binding.tlCategoryItem.addTab(
            binding.tlCategoryItem.newTab().setText("Payment")
        )
    }

    private fun setViewPager() {
        val arrayListFragments =
            arrayListOf(basicDetailsFragments, addressDetailsFragment, paymentDetailsFragment)

        tabLayoutPagerAdapter = TabLayoutPagerAdapter(requireActivity(), arrayListFragments)
        binding.vpCategoryItem.adapter = tabLayoutPagerAdapter
        TabLayoutMediator(binding.tlCategoryItem, binding.vpCategoryItem) { tab, position ->
            when (position) {
                0 -> tab.text = "Basic Details"
                1 -> tab.text = "Address"
                2 -> tab.text = "Payment"
            }
        }.attach()
    }

    private fun setTabClick(){
        binding.tlCategoryItem.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0,1-> {
                        binding.btnNext.text="Next"
                    }
                    else -> {
                        binding.btnNext.text="Submit"

                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }


}