package com.example.roadassist

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roadassist.ui.theme.*
import kotlinx.coroutines.launch

data class OnboardingPage(
    val imageRes : Int,
    val title : String,
    val subtitle : String,
    val buttonText : String
)

private val pages = listOf(
    OnboardingPage(
        imageRes = R.drawable.onboarding1,
        title = "Feeling Stuck?",
        subtitle = "No Problem. We'll Fix It.\nJust Request service.",
        buttonText = "Continue"
    ),
    OnboardingPage(
        imageRes = R.drawable.onboarding2,
        title = "Track assistant",
        subtitle = "Real-time tracking of your service provider",
        buttonText = "Continue"
    ),
    OnboardingPage(
        imageRes = R.drawable.onboarding3,
        title = "Quality Service",
        subtitle = "Rest assured, your vehicle is in expert's hand",
        buttonText = "Get Started"
    )
)


@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onFinish: () -> Unit) {

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize().background(White))
    {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { pageIndex ->
            OnboardingPageContent(page = pages[pageIndex])
        }

        TextButton(
            onClick = onFinish,
            modifier = Modifier.align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 16.dp)
        ) {
            Text(
                text = "Skip",
                color = OrangeAccent,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            PageIndicator(
                pageCount = pages.size,
                currentPage = pagerState.currentPage
            )

            Button(
                onClick = {
                    val current = pagerState.currentPage
                    if (current < pages.lastIndex) {
                        scope.launch {
                            pagerState.animateScrollToPage(current + 1)
                        }
                    } else {
                        onFinish()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(54.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OrangeButton)
            ) {
                Text(
                    text = pages[pagerState.currentPage].buttonText,
                    color = White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
private fun OnboardingPageContent(page: OnboardingPage) {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(80.dp))

        Box(
            modifier = Modifier.fillMaxWidth()
                .height(260.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFE8F4F8)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "🚗",
                fontSize = 80.sp
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = page.title,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A1A2E),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = page.subtitle,
            fontSize = 14.sp,
            color = TextGray,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun PageIndicator(pageCount: Int, currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            val isActive = index == currentPage

            val width by animateDpAsState(
                targetValue = if (isActive) 24.dp else 8.dp,
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
                label = "dot_width"
            )

            Box(
                modifier = Modifier.height(8.dp)
                    .width(width)
                    .clip(CircleShape)
                    .background(if (isActive) OrangeAccent else DotInactive)
            )
        }
    }
}