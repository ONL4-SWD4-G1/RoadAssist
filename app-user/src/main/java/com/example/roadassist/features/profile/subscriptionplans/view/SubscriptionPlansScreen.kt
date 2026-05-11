package com.example.roadassist.features.profile.subscriptionplans.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roadassist.components.PrimaryButton
import com.example.roadassist.components.UserTopBar
import com.example.roadassist.fakedata.SubscriptionPlan
import com.example.roadassist.features.profile.subscriptionplans.viewmodel.SubscriptionPlansViewModel
import com.example.roadassist.theme.GreenSuccess
import com.example.roadassist.theme.OrangeButton
import com.example.roadassist.theme.TextPrimary
import com.example.roadassist.theme.TextSecondary
import com.example.roadassist.theme.White


@Composable
fun SubscriptionPlansScreen(
    onNavigateBack: () -> Unit, viewModel: SubscriptionPlansViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { UserTopBar("Subscription", onNavigateBack) },
        containerColor = White,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(8.dp))
            Text("Unlock exclusive perks!", fontWeight = FontWeight.ExtraBold, fontSize = 22.sp)
            Spacer(Modifier.height(8.dp))
            Text(
                "Choose the plan that suits your journey.",
                color = TextSecondary,
                fontSize = 14.sp
            )
            Spacer(Modifier.height(20.dp))

            uiState.plans.forEach { plan ->
                PlanCard(plan = plan)
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun PlanCard(plan: SubscriptionPlan) {
    Column {

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    plan.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 4.dp, bottom = 8.dp),
                )
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = TextSecondary,
                            fontSize = 14.sp
                        )
                    ) {
                        append("Annual Fee: ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.ExtraBold,
                            color = TextPrimary,
                            fontSize = 18.sp,
                        )
                    ) {
                        append("$${plan.annualFee}")
                    }
                }, lineHeight = 26.sp)

                Spacer(Modifier.height(12.dp))
                plan.features.forEach { feature ->
                    Row(
                        modifier = Modifier.padding(vertical = 4.dp),
                        verticalAlignment = Alignment.Top,
                    ) {
                        Icon(
                            imageVector = if (plan.id == "premium" && feature.startsWith("All")) Icons.Default.Star else Icons.Outlined.CheckCircle,
                            contentDescription = null,
                            tint = if (plan.id == "premium" && feature.startsWith("All")) OrangeButton else GreenSuccess,
                            modifier = Modifier.size(20.dp),
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(feature, fontSize = 14.sp, color = TextSecondary)
                    }
                }
                Spacer(Modifier.height(16.dp))
                PrimaryButton("Join", onClick = {})
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SubscriptionPlansScreenPreview() {
    SubscriptionPlansScreen(
        {}
    )
}