package com.example.app_admin.technicians.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.R
import com.example.app_admin.theme.BackgroundGray
import com.example.app_admin.theme.BorderGray
import com.example.app_admin.theme.PrimaryOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuspensionReasonDropdown(selectedReason: String, onReasonChange: (String) -> Unit) {
    val reasons = listOf(
        stringResource(R.string.policy_violation),
        stringResource(R.string.repeated_order_cancellation),
        stringResource(R.string.misconduct_with_client),
        stringResource(R.string.other)
    )
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedReason.ifEmpty { stringResource(R.string.select_reason_from_list) },
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(MenuAnchorType.PrimaryNotEditable),
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = BackgroundGray,
                unfocusedBorderColor = BorderGray,
                focusedBorderColor = PrimaryOrange
            ),
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = if (selectedReason.isEmpty()) Color.Gray else Color.Black
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White)
        ) {
            reasons.forEach { reason ->
                DropdownMenuItem(
                    text = { Text(text = reason, fontSize = 14.sp) },
                    onClick = {
                        onReasonChange(reason)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}