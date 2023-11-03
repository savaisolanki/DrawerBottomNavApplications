package com.example.drawerbottomnavapplication.fragment.gallery.adpter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.drawerbottomnavapplication.R
import com.example.drawerbottomnavapplication.databinding.UploadedImageBinding
import com.example.drawerbottomnavapplication.fragment.gallery.model.ImageModel


class ImageAdapter(
    private val context: Context,
    private val imageModelList: List<ImageModel>
) :
    RecyclerView.Adapter<ImageAdapter.ImageAdapterViewHolder>() {

    private lateinit var binding: UploadedImageBinding


    inner class ImageAdapterViewHolder(val binding: UploadedImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(position: Int) {
            binding.uploadImage = imageModelList[position]
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
    ): ImageAdapterViewHolder {
        binding =
            UploadedImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ImageAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageAdapterViewHolder, position: Int) {
        holder.bind(position)
        holder.binding.ivUploadImage.let {
            Glide.with(context)
                .load(imageModelList[position].imagePath)
                .placeholder(R.drawable.ic_error)
                .error(R.drawable.ic_error)
                .into(it)
        }


    }

    override fun getItemCount(): Int {
        return imageModelList.size
    }


}