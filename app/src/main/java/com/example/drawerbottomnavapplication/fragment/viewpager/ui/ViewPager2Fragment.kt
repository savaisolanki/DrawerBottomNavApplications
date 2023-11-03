package com.example.drawerbottomnavapplication.fragment.viewpager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.drawerbottomnavapplication.R
import com.example.drawerbottomnavapplication.activity.ui.MainActivity
import com.example.drawerbottomnavapplication.databinding.FragmentViewPager2Binding
import com.example.drawerbottomnavapplication.fragment.viewpager.adapter.AutometicViewPagerAdapter
import com.example.drawerbottomnavapplication.fragment.viewpager.`interface`.AutometicViewPagerInterface
import com.example.drawerbottomnavapplication.fragment.viewpager.model.AutoMeticViewPagerModel
import com.example.drawerbottomnavapplication.utils.Utils
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask


class ViewPager2Fragment : Fragment() {

    private lateinit var binding: FragmentViewPager2Binding
    private lateinit var autometicViewPagerAdapter: AutometicViewPagerAdapter
    private val imageList: ArrayList<AutoMeticViewPagerModel> = ArrayList()


    private lateinit var timer: Timer
    private var currentPage = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_pager2, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageList.clear()

        val mainActivity = requireActivity() as MainActivity
        mainActivity.supportActionBar?.show()
        mainActivity.supportActionBar?.title = "AutoMeticViewPager"
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

        viewPagerAutometic(imageList)
    }


    override fun onResume() {
        super.onResume()

        val mainActivity = requireActivity() as MainActivity
        mainActivity.supportActionBar?.show()
        mainActivity.supportActionBar?.title = "AutoMeticViewPager"
        mainActivity.binding.bottomNavigation.visibility = View.GONE
        mainActivity.lockDrawer()
        mainActivity.binding.tbToolBar.setNavigationIcon(R.drawable.ic_arrow_back)
        mainActivity.binding.tbToolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        Utils.menuHost(
            mainActivity as MenuHost,
            galleryIsVisible = false,
            imageIsVisible = false,
            cameraIsVisible = false
        )
    }

    private fun viewPagerAutometic(imageList: java.util.ArrayList<AutoMeticViewPagerModel>) {
        this.imageList.add(AutoMeticViewPagerModel(R.drawable.ic_do_not_step))
        this.imageList.add(AutoMeticViewPagerModel(R.drawable.ic_auto))
        this.imageList.add(AutoMeticViewPagerModel(R.drawable.ic_scoreboard))
        this.imageList.add(AutoMeticViewPagerModel(R.drawable.ic_tab))

        autometicViewPagerAdapter = AutometicViewPagerAdapter(requireContext(),
            imageList,
            object : AutometicViewPagerInterface {
                override fun itemClick(position: Int) {
                    Toast.makeText(requireContext(), "$position", Toast.LENGTH_SHORT).show()
                }
            })
        binding.vpViewPager.adapter = autometicViewPagerAdapter


        val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                when (position) {
                    0 -> {
                        Toast.makeText(requireContext(), "$position", Toast.LENGTH_SHORT).show()
                    }

                    1 -> {
                        Toast.makeText(requireContext(), "$position", Toast.LENGTH_SHORT).show()
                    }

                    2 -> {
                        Toast.makeText(requireContext(), "$position", Toast.LENGTH_SHORT).show()
                    }

                    3 -> {
                        Toast.makeText(requireContext(), "$position", Toast.LENGTH_SHORT).show()
                    }
                }
                currentPage = position
            }
        }

        binding.vpViewPager.registerOnPageChangeCallback(onPageChangeCallback)


        lifecycleScope.launch(Dispatchers.Main) {
            timer = Timer()
            timer.scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    if (currentPage == imageList.size) {
                        currentPage = 0
                    }
                    binding.vpViewPager.post {
                        binding.vpViewPager.currentItem = currentPage++
                    }
                }
            }, 3000L, 3000L)
        }

        binding.tab.removeAllTabs()

        TabLayoutMediator(binding.tab, binding.vpViewPager) { _, _ -> }.attach()
    }


}