package com.example.drawerbottomnavapplication.fragment.addtocart.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.drawerbottomnavapplication.R
import com.example.drawerbottomnavapplication.databinding.ItemsAddToCartBinding
import com.example.drawerbottomnavapplication.databinding.ItemsTimeSlotBinding
import com.example.drawerbottomnavapplication.fragment.addtocart.model.AddToCartModel
import com.example.drawerbottomnavapplication.fragment.addtocart.interfacedata.AddToCartInterface


class AddToCartAdapter(
    private val context: Context,
    private val addToCartList: List<AddToCartModel>,
    private val addToCartInterface: AddToCartInterface
) :
    RecyclerView.Adapter<AddToCartAdapter.AddToCartAdapterViewHolder>() {

    private lateinit var binding: ItemsAddToCartBinding


    inner class AddToCartAdapterViewHolder(val binding: ItemsAddToCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(position: Int) {
            binding.addToCartModel= addToCartList[position]
        }

    }

    override fun getItemId(position: Int): Long {
        return getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddToCartAdapterViewHolder {
        binding =
            ItemsAddToCartBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return AddToCartAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddToCartAdapterViewHolder, position: Int) {
        holder.bind(position)
        holder.itemView.setOnClickListener {
            addToCartInterface.itemClick(position)
        }
        holder.binding.ivTimeSlotItems.let {
            Glide.with(context)
                .load(addToCartList[position].url)
                .placeholder(R.drawable.ic_error)
                .centerCrop()
                .into(it)
        }
    }

    override fun getItemCount(): Int {
        return addToCartList.size
    }


}