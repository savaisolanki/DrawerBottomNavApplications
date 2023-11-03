package com.example.drawerbottomnavapplication.utils

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import com.example.drawerbottomnavapplication.R

object Utils {

    fun menuHost(
        menuHost: MenuHost,
        galleryIsVisible: Boolean,
        imageIsVisible: Boolean,
        cameraIsVisible: Boolean


    ) {

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                val camera = menu.findItem(R.id.mnCamera)
                val gallery = menu.findItem(R.id.mnGallery)
                val image = menu.findItem(R.id.mnImage)

                if (camera != null) camera.isVisible = cameraIsVisible
                if (gallery != null) gallery.isVisible = galleryIsVisible
                if (image != null) image.isVisible = imageIsVisible

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }

        })

    }
}