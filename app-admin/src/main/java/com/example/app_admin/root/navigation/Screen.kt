package com.example.app_admin.root.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    object Overview : Screen()
    @Serializable
    object Orders : Screen()
    @Serializable
    object Technicians : Screen()
    @Serializable
    object Finance : Screen()
    @Serializable
    object More : Screen()
    @Serializable
    object UserProfile : Screen()

    @Serializable
    data class TechnicianDetails(val techId: Int) : Screen()
    @Serializable
    data class ComplaintDetails(val complaintId: Int) : Screen()
    @Serializable
    data class ComplainantTechnician(val techId: Int) : Screen()
    @Serializable
    data class UserWarning(val userId: Int) : Screen()
    @Serializable
    data class UserSuspension(val userId: Int) : Screen()
    @Serializable
    data class TechnicianSuspension(val techId: Int) : Screen()

    @Serializable
    data class TechnicianDeduction(val techId: Int) : Screen()

    @Serializable
    data class TechnicianRefund(val orderId: String, val techId: Int) : Screen()
}