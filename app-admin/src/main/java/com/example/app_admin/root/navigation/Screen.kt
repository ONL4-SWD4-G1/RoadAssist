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
    object Complaints : Screen()
    @Serializable
    object ComplaintDetails : Screen()

    @Serializable
    object UserWarning : Screen()

    @Serializable
    object UserSuspension : Screen()

    @Serializable
    object UserProfile : Screen()

    // For screens that need arguments, we use data classes
    @Serializable
    data class TechnicianDetail(val techId: Int) : Screen()
    @Serializable
    data class ComplaintDetail(val complaintId: Int) : Screen()
}