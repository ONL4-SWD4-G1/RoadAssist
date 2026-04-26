package com.example.roadassist

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roadassist.ui.theme.*
import kotlinx.coroutines.launch

data class OnboardingPage(
    val image : Int,
    val title : String,
    val subtitle : String,
    val buttonText: String
)

val onboardingPages = listOf(
    OnboardingPage(
        image = R.drawable.onboarding1,
        title = "Feeling Stuck?",
        subtitle = "No Problem. We'll Fix It.\nJust Request service.",
        buttonText = "Continue"
    ),
    OnboardingPage(
        image = R.drawable.onboarding2,
        title = "Track assistant",
        subtitle = "Real-time tracking of your service provider",
        buttonText = "Continue"
    ),
    OnboardingPage(
        image = R.drawable.onboarding3,
        title = "Quality Service",
        subtitle = "Rest assured, your vehicle is in expert's hand",
        buttonText = "Get Started"
    )
)

@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onFinish: () -> Unit) {

    val pagerState = rememberPagerState(pageCount = { onboardingPages.size })
    val scope = rememberCoroutineScope()


    Box(
        modifier = Modifier.fillMaxSize().background(White)
    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { pageIndex ->
            SingleOnboardingPage(page = onboardingPages[pageIndex])
        }

        TextButton(
            onClick = { onFinish() },
            modifier = Modifier.align(Alignment.TopEnd).padding(16.dp)
        ) {
            Text(
                text = "Skip",
                color = OrangeAccent,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            DotsIndicator(
                totalDots = onboardingPages.size,
                activeDot = pagerState.currentPage
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (pagerState.currentPage < onboardingPages.lastIndex) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        onFinish()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OrangeAccent)
            ) {
                Text(
                    text = onboardingPages[pagerState.currentPage].buttonText,
                    color = White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}
@Composable
fun SingleOnboardingPage(page: OnboardingPage) {

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
