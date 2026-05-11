package com.example.roadassist.features.profile.contact.model

import com.example.roadassist.fakedata.Contact

data class ContactUiState(
    val phone: String = Contact.PHONE,
    val email: String = Contact.EMAIL,
    val address: String = Contact.ADDRESS,
)