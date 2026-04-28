package com.example.app_admin.ui.customes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.ui.theme.DarkNavy
import com.example.app_admin.ui.theme.LightText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoadAssistTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null,
    bottomContent: @Composable (() -> Unit)? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    Surface(
        color = Color.White,
        tonalElevation = 0.dp,
        shadowElevation = if ((scrollBehavior?.state?.overlappedFraction ?: 0f) > 0.01f) 2.dp else 0.dp
    ) {
        Column(modifier = modifier) {
            CenterAlignedTopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = DarkNavy,
                                fontSize = 18.sp
                            )
                        )
                        if (subtitle != null) {
                            Text(
                                text = subtitle,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = LightText,
                                    fontSize = 12.sp
                                )
                            )
                        }
                    }
                },
                navigationIcon = { navigationIcon?.invoke() },
                actions = { actions?.invoke(this) },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    scrolledContainerColor = Color.White
                )
            )
            bottomContent?.invoke()
        }
    }
}