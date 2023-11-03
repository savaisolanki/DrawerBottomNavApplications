package com.example.drawerbottomnavapplication.fragment.tablayout.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.drawerbottomnavapplication.fragment.stepview.ui.AddressDetailsFragment
import com.example.drawerbottomnavapplication.fragment.stepview.ui.BasicDetailsFragment
import com.example.drawerbottomnavapplication.fragment.stepview.ui.PaymentDetailsFragment

class TabLayoutPagerAdapter(fragment: FragmentActivity,private val list: List<Fragment>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return list[0]
            1 -> return list[1]
            2 -> return list[2]

        }
        return list[0]
    }
}
