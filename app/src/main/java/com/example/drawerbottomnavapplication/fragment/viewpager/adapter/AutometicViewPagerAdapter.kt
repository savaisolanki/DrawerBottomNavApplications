package com.example.drawerbottomnavapplication.fragment.viewpager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.drawerbottomnavapplication.R
import com.example.drawerbottomnavapplication.databinding.ItemImageListBinding
import com.example.drawerbottomnavapplication.fragment.viewpager.`interface`.AutometicViewPagerInterface
import com.example.drawerbottomnavapplication.fragment.viewpager.model.AutoMeticViewPagerModel

class AutometicViewPagerAdapter(
    private val context: Context,
    private val imageList: ArrayList<AutoMeticViewPagerModel>,
    private val autometicViewPagerInterface: AutometicViewPagerInterface
) : RecyclerView.Adapter<AutometicViewPagerAdapter.AutometicViewPagerAdapterViewHolder>() {


    private lateinit var binding: ItemImageListBinding

    inner class AutometicViewPagerAdapterViewHolder(val binding: ItemImageListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): AutometicViewPagerAdapterViewHolder {
        binding = ItemImageListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AutometicViewPagerAdapterViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: AutometicViewPagerAdapterViewHolder, position: Int) {
        holder.bind(position)

        holder.itemView.setOnClickListener {
            autometicViewPagerInterface.itemClick(position)
        }
        holder.binding.ivAutoMeticItems.let {
            Glide.with(context).load(imageList[position].url).placeholder(R.drawable.ic_error)
                .fitCenter().into(it)
        }

    }
}