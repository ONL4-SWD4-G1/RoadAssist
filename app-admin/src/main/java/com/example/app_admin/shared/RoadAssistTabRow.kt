package com.example.app_admin.shared

import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun RoadAssistTabRow(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    isScrollable: Boolean = false,
    containerColor: Color = Color.White,
    selectedContentColor: Color = Color(0xFFF5A623),
    unselectedContentColor: Color = Color(0xff64748B)
) {
    val tabContent = @Composable {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                unselectedContentColor = unselectedContentColor,
                selectedContentColor = selectedContentColor,
                text = {
                    Text(
                        text = title,
                        fontSize = 13.sp,
                        fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Medium,
                        maxLines = 1,
                        softWrap = false,
                        overflow = TextOverflow.Visible
                    )
                }
            )
        }
    }

    if (isScrollable) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 16.dp,
            containerColor = containerColor,
            contentColor = selectedContentColor,
            modifier = modifier,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(
                        tabPositions[selectedTabIndex]
                    ),
                    color = selectedContentColor
                )
            },
            tabs = tabContent
        )
    } else {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = containerColor,
            contentColor = selectedContentColor,
            modifier = modifier,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = selectedContentColor
                )
            },
            tabs = tabContent
        )
    }
}

@Preview(showBackground = true, name = "Fixed Tabs - Arabic", locale = "ar")
@Composable
fun PreviewRoadAssistTabRowFixed() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("المعاملات", "طلبات الصرف", "التقارير")

    RoadAssistTabRow(
        tabs = tabs,
        selectedTabIndex = selectedTab,
        onTabSelected = { selectedTab = it },
        isScrollable = false
    )
}

@Preview(showBackground = true, name = "Scrollable Tabs - Arabic", locale = "ar")
@Composable
fun PreviewRoadAssistTabRowScrollable() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("الكل", "نشط", "متوقف", "قيد المراجعة", "مكتمل", "ملغى")

    RoadAssistTabRow(
        tabs = tabs,
        selectedTabIndex = selectedTab,
        onTabSelected = { selectedTab = it },
        isScrollable = true
    )
}