package com.example.roadassist.features.booking.technicians.availabletechs.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.fakedata.technicians
import com.example.roadassist.features.booking.technicians.availabletechs.model.AvailableTechniciansUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AvailableTechniciansViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        AvailableTechniciansUiState(
            allTechnicians = technicians,
            filteredTechnicians = technicians,
        )
    )
    val uiState: StateFlow<AvailableTechniciansUiState> = _uiState.asStateFlow()

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        applyFilters()
    }

    fun onFilterSelected(filter: String) {
        _uiState.update { it.copy(selectedFilter = filter) }
        applyFilters()
    }

    private fun applyFilters() {
        val state = _uiState.value
        val filtered = state.allTechnicians.filter { tech ->
            val matchesSearch = state.searchQuery.isEmpty() ||
                    tech.name.contains(state.searchQuery, ignoreCase = true) ||
                    tech.role.contains(state.searchQuery, ignoreCase = true)
            val matchesFilter = state.selectedFilter == "All" ||
                    tech.expertise.any { e -> e.contains(state.selectedFilter, ignoreCase = true) }
            matchesSearch && matchesFilter
        }
        _uiState.update { it.copy(filteredTechnicians = filtered) }
    }
}
