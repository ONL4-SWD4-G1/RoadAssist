package com.example.app_admin.ui.customes

import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RoadAssistTabRow(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    isScrollable: Boolean = false, // Add this parameter
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
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
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