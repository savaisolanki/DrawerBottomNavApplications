package com.example.drawerbottomnavapplication.fragment.camera.ui

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
import com.example.drawerbottomnavapplication.databinding.FragmentCameraBinding
import com.example.drawerbottomnavapplication.utils.Utils

class CameraFragment : Fragment() {
    private lateinit var binding: FragmentCameraBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_camera, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).supportActionBar?.show()
        (requireActivity() as MainActivity).supportActionBar?.title = "Camera"
        (requireActivity() as MainActivity).binding.bottomNavigation.visibility = View.GONE
        (activity as MainActivity).lockDrawer()

        (activity as MainActivity).binding.tbToolBar.setNavigationIcon(R.drawable.ic_arrow_back)

        (activity as MainActivity).binding.tbToolBar.setNavigationOnClickListener {
            findNavController().popBackStack()

        }

        Utils.menuHost(
            requireActivity() as MenuHost,
            galleryIsVisible = true,
            imageIsVisible = true,
            cameraIsVisible = false
        )
    }

}