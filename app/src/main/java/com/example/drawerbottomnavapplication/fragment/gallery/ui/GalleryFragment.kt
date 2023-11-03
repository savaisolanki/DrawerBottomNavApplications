package com.example.drawerbottomnavapplication.fragment.gallery.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.MenuHost
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.drawerbottomnavapplication.R
import com.example.drawerbottomnavapplication.activity.ui.MainActivity
import com.example.drawerbottomnavapplication.databinding.FragmentGalleryBinding
import com.example.drawerbottomnavapplication.fragment.gallery.adpter.ImageAdapter
import com.example.drawerbottomnavapplication.fragment.gallery.model.ImageModel
import com.example.drawerbottomnavapplication.utils.Utils
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID


class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding
    private var isExpanded: Boolean = false


    private lateinit var actionLauncher: ActivityResultLauncher<Intent>
    private var currentActionType: Int = 0


    companion object {
        private const val CAMERA_PERMISSION_REQUEST = 101
        private const val IMAGE_PICK_REQUEST = 103
    }

    private var imageUri: Uri? = null
    private lateinit var imageAdapter: ImageAdapter
    private val imageModelList = mutableListOf<ImageModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallery, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mainActivity = requireActivity() as MainActivity
        mainActivity.supportActionBar?.show()
        mainActivity.supportActionBar?.title = "Gallery"
        mainActivity.binding.bottomNavigation.visibility = View.GONE
        mainActivity.lockDrawer()
        mainActivity.binding.tbToolBar.setNavigationIcon(R.drawable.ic_arrow_back)
        mainActivity.binding.tbToolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }



        Utils.menuHost(
            mainActivity as MenuHost,
            galleryIsVisible = false,
            imageIsVisible = true,
            cameraIsVisible = true
        )

        binding.fbAdd.setOnClickListener {
            isExpanded = !isExpanded
            updateUI()
        }

        imageAdapter = ImageAdapter(requireContext(), imageModelList)
        binding.rvUploadImage.adapter = imageAdapter
        loadImageDataFromPreferences()


        binding.fbCamera.setOnClickListener {
            if (checkCameraPermission()) {
                openCamera()
            } else {
                requestCameraPermission()
            }
        }

        binding.fbGallery.setOnClickListener {
            if (checkGalleryPermission()) {
                openGallery()
            } else {
                requestGalleryPermission()
            }
        }

        actionLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data = result.data
                    if (data != null) {
                        if (currentActionType == 0) {
                            handleCameraResult(data)
                        } else if (currentActionType == 1) {
                            handleGalleryResult(data)
                        }
                    }
                }
            }

        updateUI()

        super.onViewCreated(view, savedInstanceState)
    }

    /*
    * This code for is fbAdd expand next gone view is visible else gone and
    * */

    private fun updateUI() {
        val visibility = if (isExpanded) View.VISIBLE else View.GONE
        with(binding) {
            fbGallery.visibility = visibility
            fbCamera.visibility = visibility
            tvGallery.visibility = visibility
            tvCamera.visibility = visibility
            fbAdd.setImageResource(if (isExpanded) R.drawable.ic_close else R.drawable.ic_add)
        }
    }

    /*
    *
    * This code for camera upload image
    *
    * */
    private fun checkCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            CAMERA_PERMISSION_REQUEST
        )
    }



    private fun openCamera() {
        currentActionType = 0
        val imageFile = createImageFile()
        imageUri = FileProvider.getUriForFile(
            requireContext(),
            "com.example.drawerbottomnavapplication.fileprovider",
            imageFile
        )
        val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        actionLauncher.launch(captureIntent)
        isExpanded = false
        updateUI()
    }

    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "IMG_$timeStamp.jpg"
        val storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imageFile = File(storageDir, imageFileName)

        Log.d("ImageFile", "Image file path: ${imageFile.absolutePath}")  // Log the image file path

        return imageFile
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun handleCameraResult(data: Intent?) {
        val imageFile = createImageFile()
        imageUri = FileProvider.getUriForFile(
            requireContext(),
            "com.example.drawerbottomnavapplication.fileprovider",
            imageFile
        )

        Log.d("CameraResult", "imageUri: $imageUri")  // Log imageUri here

        val imageModel = ImageModel(
            imageId = UUID.randomUUID().toString(),
            imagePath = imageUri?.toString() ?: ""
        )
        imageModelList.add(imageModel)
        saveImageDataToPreferences(imageUri?.toString() ?: "")
        isExpanded = false
        updateUI()
        imageAdapter.notifyDataSetChanged()
    }



    /*
    * This Code for upload multiple  image  from gallery
    *
    * */
    private fun checkGalleryPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestGalleryPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            IMAGE_PICK_REQUEST
        )
    }

    private fun openGallery() {
        currentActionType = 1
        val galleryIntent = Intent(Intent.ACTION_GET_CONTENT)
        galleryIntent.type = "image/*"
        galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        actionLauncher.launch(galleryIntent)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun handleGalleryResult(data: Intent?) {
        if (data?.clipData != null) {
            val clipData = data.clipData
            for (i in 0 until clipData?.itemCount!!) {
                val imageUri = clipData.getItemAt(i)?.uri
                if (imageUri != null) {
                    val imageModel = ImageModel(
                        imageId = UUID.randomUUID().toString(),
                        imagePath = imageUri.toString()
                    )

                    imageModelList.add(imageModel)
                    saveImageDataToPreferences(imageUri.toString() ?: "")

                    isExpanded = false
                    updateUI()
                }
            }
        } else if (data?.data != null) {
            val imageUri = data.data
            if (imageUri != null) {
                val imageModel = ImageModel(
                    imageId = UUID.randomUUID().toString(),
                    imagePath = imageUri.toString()
                )
                imageModelList.add(imageModel)
                saveImageDataToPreferences(imageUri.toString() ?: "")
                isExpanded = false
                updateUI()
            }
        }
        imageAdapter.notifyDataSetChanged()
    }


    private fun saveImageDataToPreferences(imagePath: String) {
        val sharedPreferences = requireContext().getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val imageSet = sharedPreferences.getStringSet("imageSet", mutableSetOf())?.toMutableSet()
        imageSet?.add(imagePath)
        editor.putStringSet("imageSet", imageSet)
        editor.apply()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadImageDataFromPreferences() {
        val sharedPreferences = requireContext().getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        val imageSet = sharedPreferences.getStringSet("imageSet", mutableSetOf())
        for (imagePath in imageSet.orEmpty()) {
            val imageModel = ImageModel(
                imageId = UUID.randomUUID().toString(),
                imagePath = imagePath
            )
            imageModelList.add(imageModel)
        }
        imageAdapter.notifyDataSetChanged()
    }


}