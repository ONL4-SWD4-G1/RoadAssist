package com.example.app_admin.shared


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.theme.DarkNavy
import com.example.app_admin.theme.LightText

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
            TopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.Start) {
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
                windowInsets = WindowInsets(0, 0, 0, 0),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    scrolledContainerColor = Color.White
                )
            )
            bottomContent?.invoke()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "Basic Top Bar", locale = "ar")
@Composable
fun PreviewTopBarBasic() {
    MaterialTheme {
        RoadAssistTopAppBar(
            title = "الشاشة الرئيسية",
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Default.Menu,
                        contentDescription = null,
                        tint = DarkNavy
                    )
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "With Subtitle & Actions", locale = "ar")
@Composable
fun PreviewTopBarFull() {
    MaterialTheme {
        RoadAssistTopAppBar(
            title = "المحفظة والمالية",
            subtitle = "الرصيد: ٥٠٠ ر.س",
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Default.ArrowForward,
                        contentDescription = null,
                        tint = DarkNavy
                    )
                }
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Default.NotificationsNone,
                        contentDescription = null,
                        tint = DarkNavy
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Default.MoreVert,
                        contentDescription = null,
                        tint = DarkNavy
                    )
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "With Tabs (Bottom Content)", locale = "ar")
@Composable
fun PreviewTopBarWithTabs() {
    MaterialTheme {
        RoadAssistTopAppBar(
            title = "إدارة الفنيين",
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Default.Menu,
                        contentDescription = null,
                        tint = DarkNavy
                    )
                }
            },
            bottomContent = {
                RoadAssistTabRow(
                    tabs = listOf("الكل", "نشط", "متوقف"),
                    selectedTabIndex = 0,
                    onTabSelected = {},
                    containerColor = Color.Transparent
                )
            }
        )
    }
}