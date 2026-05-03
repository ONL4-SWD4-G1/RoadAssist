package com.example.app_admin.ui.finance.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.ui.technicians.model.TransactionData
import com.example.app_admin.ui.theme.DangerBg
import com.example.app_admin.ui.theme.DangerRed
import com.example.app_admin.ui.theme.DarkNavy
import com.example.app_admin.ui.theme.DeepBlue
import com.example.app_admin.ui.theme.LightText
import com.example.app_admin.ui.theme.SuccessBg
import com.example.app_admin.ui.theme.SuccessGreen

@Composable
fun TransactionItem(transaction: TransactionData) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xffF6F6F8)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    "${if(transaction.isPositive) "+" else ""}${transaction.amount} ر.س",
                    color =
                        if(transaction.isPositive) SuccessGreen
                        else DangerRed,
                    fontWeight = FontWeight.Bold
                )
                Text(transaction.status, color = LightText, fontSize = 11.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.End) {
                Text(transaction.title, fontWeight = FontWeight.Bold, color = DarkNavy, fontSize = 14.sp)
                Text(transaction.date, color = LightText, fontSize = 11.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(if (transaction.isPositive) SuccessBg else if (transaction.title.contains("تحويل")) Color(0xFFF1F5F9) else DangerBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector =
                        if(transaction.title.contains("تحويل"))
                            Icons.Default.AccountBalance
                        else if(transaction.isPositive) Icons.Default.Add
                        else Icons.Default.RemoveCircleOutline,
                    contentDescription = null,
                    tint =
                        if(transaction.isPositive) SuccessGreen
                        else if (transaction.title.contains("تحويل")) DeepBlue
                        else DangerRed
                )
            }
        }
    }
}
