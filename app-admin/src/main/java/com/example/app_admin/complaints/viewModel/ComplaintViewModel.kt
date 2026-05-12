package com.example.app_admin.complaints.viewModel

import androidx.lifecycle.ViewModel
import com.example.app_admin.complaints.model.Complaint
import com.example.app_admin.complaints.model.ComplaintStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ComplaintViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ComplaintUiState())
    val uiState: StateFlow<ComplaintUiState> = _uiState.asStateFlow()

    fun selectComplaint(complaint: Complaint) {
        _uiState.update { it.copy(selectedComplaint = complaint) }
    }

    fun clearSelectedComplaint() {
        _uiState.update { it.copy(selectedComplaint = null) }
    }

    fun showWarning() {
        _uiState.update { it.copy(showWarningScreen = true) }
    }

    fun hideWarning() {
        _uiState.update { it.copy(showWarningScreen = false) }
    }

    fun showUserProfile() {
        _uiState.update { it.copy(showUserProfile = true) }
    }

    fun hideUserProfile() {
        _uiState.update { it.copy(showUserProfile = false) }
    }

    fun getComplaintsByStatus(status: ComplaintStatus): List<Complaint> {
        return _uiState.value.complaints.filter { it.status == status }
    }
}