package com.example.drawerbottomnavapplication.fragment.addtocart.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.drawerbottomnavapplication.R
import com.example.drawerbottomnavapplication.activity.ui.MainActivity
import com.example.drawerbottomnavapplication.databinding.FragmentAddToCartBinding
import com.example.drawerbottomnavapplication.fragment.addtocart.adapter.AddToCartAdapter
import com.example.drawerbottomnavapplication.fragment.addtocart.interfacedata.AddToCartInterface
import com.example.drawerbottomnavapplication.fragment.addtocart.model.AddToCartModel
import com.example.drawerbottomnavapplication.utils.Utils
import com.google.android.material.textview.MaterialTextView


class AddToCartFragment : Fragment() {
    private lateinit var binding: FragmentAddToCartBinding

    private lateinit var addToCartAdapter: AddToCartAdapter
    private val addToCartList: ArrayList<AddToCartModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_to_cart, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addToCartList.clear()

        (requireActivity() as MainActivity).supportActionBar?.show()
        (requireActivity() as MainActivity).supportActionBar?.title = "Add To Cart"
        Utils.menuHost(
            requireActivity() as MenuHost,
            galleryIsVisible = true,
            cameraIsVisible = true,
            imageIsVisible = true
        )
        timeSlotAdapter(addToCartList)

    }


    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).supportActionBar?.show()
        (requireActivity() as MainActivity).supportActionBar?.title = "Add To Cart"
        Utils.menuHost(
            requireActivity() as MenuHost,
            galleryIsVisible = true,
            cameraIsVisible = true,
            imageIsVisible = true
        )

        (activity as MainActivity).binding.tbToolBar.setNavigationIcon(R.drawable.ic_menu)
        (activity as MainActivity).binding.tbToolBar.setNavigationOnClickListener {
            (activity as MainActivity).binding.drawerLayout.open()
        }

    }


    @SuppressLint("NotifyDataSetChanged")
    private fun timeSlotAdapter(timeList: java.util.ArrayList<AddToCartModel>) {
        this.addToCartList.add(AddToCartModel("Alert Dailog", R.drawable.ic_dailog, true))
        this.addToCartList.add(
            AddToCartModel(
                "Scanner Image",
                R.drawable.ic_document_scanner,
                true
            )
        )
        this.addToCartList.add(AddToCartModel("Folder", R.drawable.ic_scoreboard, true))
        this.addToCartList.add(AddToCartModel("ScanCode", R.drawable.ic_document_scanner, true))
        this.addToCartList.add(AddToCartModel("Login", R.drawable.ic_login, true))

        addToCartAdapter =
            AddToCartAdapter(requireContext(), timeList, object : AddToCartInterface {
                override fun itemClick(position: Int) {
                    when (position) {
                        0 -> {

                            val builder = AlertDialog.Builder(requireContext())
                            val inflater = layoutInflater
                            val dialogView = inflater.inflate(R.layout.custom_alert_dialog, null)

                            builder.setView(dialogView)
                            val alertDialog = builder.create()

                            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                            val tvCancel = dialogView.findViewById<MaterialTextView>(R.id.tvCancel)
                            val tvCreate = dialogView.findViewById<MaterialTextView>(R.id.tvCreate)

                            tvCancel.setOnClickListener {
                                alertDialog.dismiss()
                            }

                            tvCreate.setOnClickListener {
                                alertDialog.dismiss()
                                Toast.makeText(
                                    requireContext(),
                                    "Create clicked",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            alertDialog.show()


                        }

                        1 -> {
                            val action =
                                AddToCartFragmentDirections.actionMnAddToCartToDocumentScannerFragment()
                            findNavController().navigate(action)

                        }

                        2 -> {
                            val action =
                                AddToCartFragmentDirections.actionMnAddToCartToHomeFragment()
                            findNavController().navigate(action)

                        }

                        3 -> {
                            val action =
                                AddToCartFragmentDirections.actionMnAddToCartToScanCodeFragment()
                            findNavController().navigate(action)

                        }
                    }

                }
            })

        addToCartAdapter.notifyDataSetChanged()
        binding.rvAddToCart.adapter = addToCartAdapter

    }

}