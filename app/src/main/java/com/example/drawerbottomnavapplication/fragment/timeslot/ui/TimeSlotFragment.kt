package com.example.drawerbottomnavapplication.fragment.timeslot.ui

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
import com.example.drawerbottomnavapplication.databinding.FragmentTimeSlotBinding
import com.example.drawerbottomnavapplication.fragment.timeslot.adapter.TimeSlotAdapter
import com.example.drawerbottomnavapplication.fragment.timeslot.interfacedata.TimeSlotInterface
import com.example.drawerbottomnavapplication.fragment.timeslot.model.TimeSlotModel
import com.example.drawerbottomnavapplication.utils.Utils


class TimeSlotFragment : Fragment() {

    private lateinit var binding: FragmentTimeSlotBinding
    private lateinit var timeSlotAdapter: TimeSlotAdapter
    private val timeList: ArrayList<TimeSlotModel> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_time_slot, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        timeList.clear()

        (requireActivity() as MainActivity).supportActionBar?.show()
        (requireActivity() as MainActivity).supportActionBar?.title = "TimeSlot"
        Utils.menuHost(
            requireActivity() as MenuHost,
            galleryIsVisible = true,
            cameraIsVisible = false,
            imageIsVisible = false
        )
        (activity as MainActivity).binding.tbToolBar.setNavigationIcon(R.drawable.ic_menu)
        (activity as MainActivity).binding.tbToolBar.setNavigationOnClickListener {
            (activity as MainActivity).binding.drawerLayout.open()
        }
        timeSlotAdapter(timeList)

    }

    override fun onResume() {
        super.onResume()
        timeList.clear()

        (requireActivity() as MainActivity).supportActionBar?.show()
        (requireActivity() as MainActivity).supportActionBar?.title = "TimeSlot"
        Utils.menuHost(
            requireActivity() as MenuHost,
            galleryIsVisible = true,
            cameraIsVisible = false,
            imageIsVisible = false
        )
        (activity as MainActivity).binding.tbToolBar.setNavigationIcon(R.drawable.ic_menu)
        (activity as MainActivity).binding.tbToolBar.setNavigationOnClickListener {
            (activity as MainActivity).binding.drawerLayout.open()
        }
        timeSlotAdapter(timeList)

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun timeSlotAdapter(timeList: java.util.ArrayList<TimeSlotModel>) {
        this.timeList.add(TimeSlotModel("StepView", R.drawable.ic_do_not_step, true))
        this.timeList.add(TimeSlotModel("ViewPagerWithTimer", R.drawable.ic_auto, true))
        this.timeList.add(TimeSlotModel("OnBoarding", R.drawable.ic_scoreboard, true))
        this.timeList.add(TimeSlotModel("TabLayout", R.drawable.ic_tab, true))
        this.timeList.add(TimeSlotModel("Login", R.drawable.ic_login, true))

        timeSlotAdapter = TimeSlotAdapter(requireContext(), timeList, object : TimeSlotInterface {
            override fun itemClick(position: Int) {
                when (position) {
                    0 -> {
                        val action = TimeSlotFragmentDirections.actionMnTimeSlotToStepViewFragment()
                        findNavController().navigate(action)

                    }

                    1 -> {
                        val action =
                            TimeSlotFragmentDirections.actionMnTimeSlotToViewPager2Fragment()
                        findNavController().navigate(action)
                    }

                    2 -> {
                        val action =
                            TimeSlotFragmentDirections.actionMnTimeSlotToOnBoardingFragment()
                        findNavController().navigate(action)
                    }

                    3 -> {
                        val action =
                            TimeSlotFragmentDirections.actionMnTimeSlotToTabLayoutFragment()
                        findNavController().navigate(action)
                    }

                    4 -> {
                        val action = TimeSlotFragmentDirections.actionMnTimeSlotToLoginFragment()
                        findNavController().navigate(action)
                    }

                }

            }
        })

        timeSlotAdapter.notifyDataSetChanged()
        binding.rvTimeSlot.adapter = timeSlotAdapter

    }

}