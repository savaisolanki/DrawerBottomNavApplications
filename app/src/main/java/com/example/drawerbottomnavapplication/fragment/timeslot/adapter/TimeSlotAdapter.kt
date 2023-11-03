package com.example.drawerbottomnavapplication.fragment.timeslot.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.drawerbottomnavapplication.R
import com.example.drawerbottomnavapplication.databinding.ItemsTimeSlotBinding
import com.example.drawerbottomnavapplication.fragment.timeslot.interfacedata.TimeSlotInterface
import com.example.drawerbottomnavapplication.fragment.timeslot.model.TimeSlotModel


class TimeSlotAdapter(
    private val context: Context,
    private val timeList: List<TimeSlotModel>,
    private val timeSlotInterface: TimeSlotInterface
) :
    RecyclerView.Adapter<TimeSlotAdapter.TimeSlotAdapterViewHolder>() {

    private lateinit var binding: ItemsTimeSlotBinding


    inner class TimeSlotAdapterViewHolder(val binding: ItemsTimeSlotBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(position: Int) {
            binding.timeSlotModel = timeList[position]
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
    ): TimeSlotAdapterViewHolder {
        binding =
            ItemsTimeSlotBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return TimeSlotAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimeSlotAdapterViewHolder, position: Int) {
        holder.bind(position)
        holder.itemView.setOnClickListener {
            timeSlotInterface.itemClick(position)
        }
        holder.binding.ivTimeSlotItems.let {
            Glide.with(context)
                .load(timeList[position].url)
                .placeholder(R.drawable.ic_error)
                .centerCrop()
                .into(it)
        }
    }

    override fun getItemCount(): Int {
        return timeList.size
    }


}