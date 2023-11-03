package com.example.drawerbottomnavapplication.fragment.account.model

data class AccountModel(
    val name: String,
    val url: Int? = null,
    val text: String,
    val isArrow: Boolean = true
)
