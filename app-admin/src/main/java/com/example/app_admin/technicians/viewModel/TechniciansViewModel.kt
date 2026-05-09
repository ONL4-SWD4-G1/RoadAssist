package com.example.app_admin.technicians.viewModel

import androidx.lifecycle.ViewModel
import com.example.app_admin.technicians.model.DetailFlow
import com.example.app_admin.technicians.model.Technician
import com.example.app_admin.technicians.model.TechnicianStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TechnicianViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TechnicianUiState())
    val uiState: StateFlow<TechnicianUiState> = _uiState.asStateFlow()

    fun selectTab(index: Int) {
        _uiState.update { it.copy(selectedTab = index) }
    }

    fun selectTechnician(technician: Technician) {
        _uiState.update { it.copy(selectedTechnician = technician) }
    }

    fun clearSelectedTechnician() {
        _uiState.update {
            it.copy(
                selectedTechnician = null,
                currentFlow = DetailFlow.VIEW,
                rejectionReason = ""
            )
        }
    }

    fun setDetailFlow(flow: DetailFlow) {
        _uiState.update { it.copy(currentFlow = flow) }
    }

    fun confirmRejection(reason: String) {
        _uiState.update {
            it.copy(
                rejectionReason = reason,
                currentFlow = DetailFlow.REJECT_SUCCESS
            )
        }
    }

    fun getTechniciansByStatus(status: TechnicianStatus): List<Technician> {
        return _uiState.value.allTechnicians.filter { it.status == status }
    }
}