package com.example.app_admin.finance.model

data class TransactionData(
    val title: String,
    val amount: String,
    val date: String,
    val status: String,
    val isPositive: Boolean
)