package com.example.drawerbottomnavapplication.fragment.product.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.drawerbottomnavapplication.R
import com.example.drawerbottomnavapplication.activity.ui.MainActivity
import com.example.drawerbottomnavapplication.databinding.FragmentProductBinding
import com.example.drawerbottomnavapplication.utils.Utils


class ProductFragment : Fragment() {
    private lateinit var binding: FragmentProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).supportActionBar?.show()
        (requireActivity() as MainActivity).supportActionBar?.title="Product"
        Utils.menuHost(
            requireActivity() as MenuHost,
            galleryIsVisible = true,
            cameraIsVisible = true,
            imageIsVisible = false
        )
        (activity as MainActivity).binding.tbToolBar.setNavigationIcon(R.drawable.ic_menu)
        (activity as MainActivity).binding.tbToolBar.setNavigationOnClickListener {
            (activity as MainActivity).binding.drawerLayout.open()
        }

    }

}