package com.example.roadassist.features.profile.edit.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.fakedata.User
import com.example.roadassist.features.profile.edit.model.EditProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EditProfileViewModel : ViewModel() {
    private val _uiState =
        MutableStateFlow(EditProfileUiState(username = User.NAME, phone = User.PHONE))
    val uiState: StateFlow<EditProfileUiState> = _uiState.asStateFlow()

    fun onUsernameChanged(username: String) {
        _uiState.update { it.copy(username = username) }
    }

    fun onPhoneChanged(phone: String) {
        _uiState.update { it.copy(phone = phone) }
    }
}
