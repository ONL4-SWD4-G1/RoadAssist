package com.example.roadassist.features.services.otherservices.model

data class OtherServiceForm(
    val vehicleType: String = "",
    val manufacturer: String = "",
    val model: String = "",
    val registrationNum: String = "",
    val problemDescription: String = ""
)