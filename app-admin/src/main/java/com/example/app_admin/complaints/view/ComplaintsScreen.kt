package com.example.app_admin.complaints.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.app_admin.complaints.model.Complaint
import com.example.app_admin.complaints.view.component.ComplaintCard
import com.example.app_admin.sampleComplaints

@Composable
fun ComplaintsScreen(onHandleComplaint: (Complaint) -> Unit) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(sampleComplaints) { complaint ->
            ComplaintCard(
                complaint = complaint,
                onHandleClick = { onHandleComplaint(complaint) }
            )
        }
    }
}