package com.example.drawerbottomnavapplication.fragment.account.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.drawerbottomnavapplication.R
import com.example.drawerbottomnavapplication.databinding.FragmentAccountBinding
import com.example.drawerbottomnavapplication.fragment.account.adapter.AccountAdapter
import com.example.drawerbottomnavapplication.fragment.account.interfacedata.AccountInterface
import com.example.drawerbottomnavapplication.fragment.account.model.AccountModel


class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var accountAdapter: AccountAdapter
    private val accountList: ArrayList<AccountModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accountList.clear()
        accountAdapter(accountList)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun accountAdapter(accountList: java.util.ArrayList<AccountModel>) {
        this.accountList.add(AccountModel("Become a VIP", R.drawable.ic_dailog, "", true))
        this.accountList.add(AccountModel("Restore Purchase", R.drawable.ic_document_scanner, "", true))
        this.accountList.add(AccountModel("Scan Quality", R.drawable.ic_scoreboard, "Low", true))
        this.accountList.add(AccountModel("App Passcode", R.drawable.ic_tab, "", true))
        this.accountList.add(AccountModel("Get Support", R.drawable.ic_login, "", true))
        this.accountList.add(AccountModel("Request Feature", R.drawable.ic_login, "", true))
        this.accountList.add(AccountModel("Rate Us", R.drawable.ic_login, "", true))
        this.accountList.add(AccountModel("LogOut", R.drawable.ic_login, "", true))
        this.accountList.add(AccountModel("LogOut", R.drawable.ic_login, "", true))
        this.accountList.add(AccountModel("LogOut", R.drawable.ic_login, "", true))
        this.accountList.add(AccountModel("LogOut", R.drawable.ic_login, "", true))

        accountAdapter =
            AccountAdapter(requireContext(), accountList, object : AccountInterface {
                override fun itemClick(position: Int) {

                }
            })

        accountAdapter.notifyDataSetChanged()
        binding.rvAccount.adapter = accountAdapter

    }


}