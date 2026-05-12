package com.example.roadassist.features.home.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roadassist.fakedata.ServiceItem
import com.example.roadassist.theme.OrangeButton


@Composable
fun ServicesGrid(
    services: List<ServiceItem>,
    onServiceSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        services.chunked(4).forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                row.forEach { service ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clickable { onServiceSelected(service.route) }
                            .width(72.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(Color.White),
                            contentAlignment = Alignment.Center,
                        ) {
                            ServiceIconView(
                                icon = serviceIcon(service.id),
                                contentDescription = service.label,
                                tint = OrangeButton,
                                modifier = Modifier.size(28.dp),
                            )
                        }
                        Spacer(Modifier.height(6.dp))
                        Text(
                            service.label, fontSize = 11.sp, textAlign = TextAlign.Center,
                            color = Color(0xFF1A1A2E),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ServiceIconView(
    modifier: Modifier = Modifier,
    icon: ServiceIcon,
    contentDescription: String?,
    tint: Color = OrangeButton,
) {
    when (icon) {
        is ServiceIcon.Vector -> Icon(
            imageVector = icon.icon,
            contentDescription = contentDescription,
            tint = tint,
            modifier = modifier,
        )

        is ServiceIcon.Drawable -> Icon(
            painter = painterResource(id = icon.resId),
            contentDescription = contentDescription,
            tint = tint,
            modifier = modifier,
        )
    }
}