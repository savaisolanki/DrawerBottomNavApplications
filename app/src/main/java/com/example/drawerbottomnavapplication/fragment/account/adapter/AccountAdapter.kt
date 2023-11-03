package com.example.drawerbottomnavapplication.fragment.account.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.drawerbottomnavapplication.R
import com.example.drawerbottomnavapplication.databinding.ItemsAccountBinding
import com.example.drawerbottomnavapplication.fragment.account.interfacedata.AccountInterface
import com.example.drawerbottomnavapplication.fragment.account.model.AccountModel


class AccountAdapter(
    private val context: Context,
    private val accountList: List<AccountModel>,
    private val accountInterface: AccountInterface
) :
    RecyclerView.Adapter<AccountAdapter.AddToCartAdapterViewHolder>() {

    private lateinit var binding: ItemsAccountBinding


    inner class AddToCartAdapterViewHolder(val binding: ItemsAccountBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(position: Int) {
            binding.accountModel = accountList[position]
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
            ItemsAccountBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return AddToCartAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddToCartAdapterViewHolder, position: Int) {
        holder.bind(position)
        holder.itemView.setOnClickListener {
            accountInterface.itemClick(position)
        }
        holder.binding.ivAccount.let {
            Glide.with(context)
                .load(accountList[position].url)
                .placeholder(R.drawable.ic_error)
                .centerCrop()
                .into(it)
        }
    }

    override fun getItemCount(): Int {
        return accountList.size
    }


}