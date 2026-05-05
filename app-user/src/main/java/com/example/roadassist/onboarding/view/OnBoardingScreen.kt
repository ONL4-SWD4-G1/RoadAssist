package com.example.roadassist.onboarding.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roadassist.ui.theme.*
import kotlinx.coroutines.launch
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.roadassist.onboarding.model.OnboardingUiState
import com.example.roadassist.onboarding.vm.OnboardingViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    onFinish: () -> Unit,
    viewModel: OnboardingViewModel = viewModel()
) {
    val pagerState = rememberPagerState(pageCount = { viewModel.totalPages })
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize().background(White)) {

        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { pageIndex ->
            SingleOnboardingPage(page = viewModel.pages[pageIndex])
        }

        TextButton(
            onClick = onFinish,
            modifier = Modifier.align(Alignment.TopEnd).padding(16.dp)
        ) {
            Text("Skip", color = OrangeAccent, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DotsIndicator(
                totalDots = viewModel.totalPages,
                activeDot = pagerState.currentPage
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val nextPage = viewModel.onNextPage(pagerState.currentPage)
                    if (nextPage != null) {
                        scope.launch { pagerState.animateScrollToPage(nextPage) }
                    } else {
                        onFinish()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OrangeAccent)
            ) {
                Text(
                    text = viewModel.getCurrentButtonText(pagerState.currentPage),
                    color = White, fontWeight = FontWeight.Bold, fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun SingleOnboardingPage(page: OnboardingUiState) {

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(60.dp))

        Image(
            painter = painterResource(id = page.image),
            contentDescription = page.title,
            modifier = Modifier.fillMaxWidth().height(260.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = page.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = page.subtitle,
            fontSize = 14.sp,
            color = TextGray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun DotsIndicator(totalDots: Int, activeDot: Int) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(totalDots) { index ->
            Box(
                modifier = Modifier.size(if (index == activeDot) 24.dp else 8.dp, 8.dp)
                    .clip(CircleShape)
                    .background(if (index == activeDot) OrangeAccent else DotInactive)
            )
        }
    }
}