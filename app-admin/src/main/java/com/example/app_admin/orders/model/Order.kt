package com.example.app_admin.orders.model

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val id: String,
    val orderNumber: String,
    val amount: String,
    val orderStatus: OrderStatus,
    val address: String,
    val timeElapsed: String,
    val clientName: String,
    val clientImageRes: Int? = null
)