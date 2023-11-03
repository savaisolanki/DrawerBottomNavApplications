package com.example.drawerbottomnavapplication.fragment.documentscanner.ui

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
import com.example.drawerbottomnavapplication.databinding.FragmentDocumnetScannerBinding
import com.example.drawerbottomnavapplication.utils.Utils


class DocumentScannerFragment : Fragment() {

    private lateinit var binding: FragmentDocumnetScannerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_documnet_scanner, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity = requireActivity() as MainActivity
        mainActivity.supportActionBar?.show()
        mainActivity.supportActionBar?.title = "Document Scanner"
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

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.accountFragment2)
        }

    }

    override fun onResume() {
        super.onResume()
        val mainActivity = requireActivity() as MainActivity
        mainActivity.supportActionBar?.show()
        mainActivity.supportActionBar?.title = "Document Scanner"
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

}