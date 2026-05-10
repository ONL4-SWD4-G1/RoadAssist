package com.example.app_mechanic

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//Colors

//Payment Method Enum
enum class PaymentMethod { ONLINE, CASH }

//Billing Item Data
data class BillingItem(val label: String, val amount: String)

//Main Screen
@Composable
fun JobSummaryScreen(onBack: () -> Unit = {}) {
    var selectedPayment by remember { mutableStateOf(PaymentMethod.ONLINE) }

    val billingItems = listOf(
        BillingItem("Base Service Fee",       "\$50.00"),
        BillingItem("12V Car Battery (Parts)", "\$85.00"),
        BillingItem("Labor Cost (45 mins)",    "\$40.00"),
        BillingItem("Tax (GST/HST)",           "\$15.50"),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            //Top Bar
            JobSummaryTopBar(onBack = onBack)

            Divider(color = DividerColor, thickness = 1.dp)

            //Scrollable Body
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Spacer(modifier = Modifier.height(4.dp))

                // Job Header Card
                JobHeaderCard()

                // Service Breakdown
                ServiceBreakdownSection(items = billingItems, total = "\$190.50")

                // Payment Method
                PaymentMethodSection(
                    selected  = selectedPayment,
                    onSelect  = { selectedPayment = it }
                )

                // Disclaimer
                DisclaimerBox()

                Spacer(modifier = Modifier.height(16.dp))
            }

            //Complete Job Button
            CompleteJobButton()
        }
    }
}

//Top Bar
@Composable
fun JobSummaryTopBar(onBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkBg)
            .padding(horizontal = 8.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBack, modifier = Modifier.size(36.dp)) {
            Icon(
                imageVector        = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint               = AccentBlue,
                modifier           = Modifier.size(22.dp)
            )
        }

        Text(
            text       = "Job Summary",
            color      = TextPrimary,
            fontSize   = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign  = TextAlign.Center,
            modifier   = Modifier.weight(1f)
        )

        // Spacer to balance the row
        Spacer(modifier = Modifier.size(36.dp))
    }
}

//Job Header Card
@Composable
fun JobHeaderCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape    = RoundedCornerShape(16.dp),
        colors   = CardDefaults.cardColors(containerColor = CardBg)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(AccentBlue.copy(alpha = 0.18f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector        = Icons.Default.Build,
                    contentDescription = null,
                    tint               = AccentBlue,
                    modifier           = Modifier.size(26.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column {
                Text(
                    text       = "Battery Replacement",
                    color      = TextPrimary,
                    fontSize   = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text     = "Job ID: #RA-88291",
                    color    = TextSecondary,
                    fontSize = 12.sp
                )
            }
        }
    }
}

//Service Breakdown Section
@Composable
fun ServiceBreakdownSection(items: List<BillingItem>, total: String) {
    Column(verticalArrangement = Arrangement.spacedBy(0.dp)) {
        Text(
            text       = "Service Breakdown",
            color      = TextPrimary,
            fontSize   = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(14.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape    = RoundedCornerShape(16.dp),
            colors   = CardDefaults.cardColors(containerColor = CardBg)
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                items.forEachIndexed { index, item ->
                    BillingRow(label = item.label, amount = item.amount)
                    if (index < items.lastIndex) {
                        Divider(
                            color     = DividerColor,
                            thickness = 1.dp,
                            modifier  = Modifier.padding(vertical = 0.dp)
                        )
                    }
                }

                Divider(color = DividerColor.copy(alpha = 1.5f), thickness = 1.5.dp)
                Spacer(modifier = Modifier.height(14.dp))

                // Total row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment     = Alignment.CenterVertically
                ) {
                    Text(
                        text       = "Total Amount",
                        color      = TextPrimary,
                        fontSize   = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text       = total,
                        color      = TotalBlue,
                        fontSize   = 20.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}

@Composable
fun BillingRow(label: String, amount: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        Text(
            text     = label,
            color    = TextSecondary,
            fontSize = 14.sp
        )
        Text(
            text       = amount,
            color      = TextPrimary,
            fontSize   = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

//Payment Method Section
@Composable
fun PaymentMethodSection(
    selected: PaymentMethod,
    onSelect: (PaymentMethod) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text       = "Payment Method",
            color      = TextPrimary,
            fontSize   = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier              = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PaymentOptionCard(
                label      = "Online",
                icon       = Icons.Default.CreditCard,
                isSelected = selected == PaymentMethod.ONLINE,
                onClick    = { onSelect(PaymentMethod.ONLINE) },
                modifier   = Modifier.weight(1f)
            )
            PaymentOptionCard(
                label      = "Cash",
                icon       = Icons.Default.Payments,
                isSelected = selected == PaymentMethod.CASH,
                onClick    = { onSelect(PaymentMethod.CASH) },
                modifier   = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun PaymentOptionCard(
    label: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(if (isSelected) CardBg else CardBgAlt)
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) AccentBlue else DividerColor,
                shape = RoundedCornerShape(14.dp)
            )
            .clickable { onClick() }
            .padding(vertical = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Check badge for selected
            Box(contentAlignment = Alignment.TopEnd) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            if (isSelected) AccentBlue.copy(alpha = 0.18f)
                            else Color.Transparent
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector        = icon,
                        contentDescription = label,
                        tint               = if (isSelected) AccentBlue else TextSecondary,
                        modifier           = Modifier.size(22.dp)
                    )
                }

                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .offset(x = 8.dp, y = (-8).dp)
                            .size(18.dp)
                            .clip(CircleShape)
                            .background(AccentBlue),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector        = Icons.Default.Check,
                            contentDescription = null,
                            tint               = Color.White,
                            modifier           = Modifier.size(11.dp)
                        )
                    }
                }
            }

            Text(
                text       = label,
                color      = if (isSelected) TextPrimary else TextSecondary,
                fontSize   = 14.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}

//Disclaimer Box
@Composable
fun DisclaimerBox() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(InfoBg)
            .border(1.dp, DividerColor, RoundedCornerShape(14.dp))
            .padding(14.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector        = Icons.Default.Info,
            contentDescription = null,
            tint               = TextMuted,
            modifier           = Modifier
                .size(18.dp)
                .padding(top = 1.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text       = "By completing this job, you confirm that the service has been performed to professional standards and the customer has been invoiced.",
            color      = TextSecondary,
            fontSize   = 12.sp,
            lineHeight = 19.sp
        )
    }
}

//Complete Job Button
@Composable
fun CompleteJobButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkBg)
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Button(
            onClick  = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape  = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AccentBlue,
                contentColor   = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
        ) {
            Row(
                verticalAlignment     = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text       = "Complete Job",
                    fontSize   = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.25f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector        = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint               = Color.White,
                        modifier           = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

//Preview
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun JobSummaryScreenPreview() {
    MaterialTheme {
        JobSummaryScreen()
    }
}