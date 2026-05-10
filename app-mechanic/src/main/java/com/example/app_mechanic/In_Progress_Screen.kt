package com.example.app_mechanic

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

//Colors


//Main Screen
@Composable
fun ActiveJobScreen(onBack: () -> Unit = {}) {
    var sparePart   by remember { mutableStateOf("") }
    var extraCost   by remember { mutableStateOf("") }
    var notes       by remember { mutableStateOf("") }

    // Live timer state starting at 01:24:58
    var hours   by remember { mutableStateOf(1) }
    var minutes by remember { mutableStateOf(24) }
    var seconds by remember { mutableStateOf(58) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L)
            seconds++
            if (seconds >= 60) { seconds = 0; minutes++ }
            if (minutes >= 60) { minutes = 0; hours++ }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            //Top Bar
            ActiveJobTopBar(onBack = onBack)

            //Scrollable Body
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Spacer(modifier = Modifier.height(4.dp))

                // Work Duration
                WorkDurationSection(hours = hours, minutes = minutes, seconds = seconds)

                // Job Information
                JobInformationSection()

                // Billing & Parts
                BillingPartsSection(
                    sparePart  = sparePart,
                    onSparePartChange = { sparePart = it },
                    extraCost  = extraCost,
                    onExtraCostChange = { extraCost = it },
                    notes      = notes,
                    onNotesChange = { notes = it }
                )

                Spacer(modifier = Modifier.height(80.dp))
            }

            //Finish Job Button
            FinishJobButton()
        }

        //Chat FAB
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 80.dp)
                .size(52.dp)
                .clip(CircleShape)
                .background(AccentBlue),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Message,
                contentDescription = "Chat",
                tint = Color.White,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}

//Top Bar
@Composable
fun ActiveJobTopBar(onBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkBg)
            .padding(horizontal = 8.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBack, modifier = Modifier.size(36.dp)) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = TextPrimary,
                modifier = Modifier.size(22.dp)
            )
        }

        Text(
            text = "Active Job",
            color = TextPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )

        IconButton(onClick = {}, modifier = Modifier.size(36.dp)) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More",
                tint = TextPrimary,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}

//Work Duration Section
@Composable
fun WorkDurationSection(hours: Int, minutes: Int, seconds: Int) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SectionLabel(text = "WORK DURATION")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TimerUnit(
                value  = hours.toString().padStart(2, '0'),
                label  = "Hours",
                modifier = Modifier.weight(1f)
            )
            TimerUnit(
                value  = minutes.toString().padStart(2, '0'),
                label  = "Minutes",
                modifier = Modifier.weight(1f)
            )
            TimerUnit(
                value  = seconds.toString().padStart(2, '0'),
                label  = "Seconds",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun TimerUnit(value: String, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(TimerCardBg)
                .padding(vertical = 18.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                color = AccentBlue,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }
        Text(
            text = label,
            color = TextSecondary,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

//Job Information Section
@Composable
fun JobInformationSection() {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        SectionLabel(text = "JOB INFORMATION")

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = CardBg)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icon box
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(AccentBlue.copy(alpha = 0.18f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Build,
                        contentDescription = null,
                        tint = AccentBlue,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    Text(
                        text = "Tire Replacement",
                        color = TextPrimary,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "Emergency Roadside Assistance",
                        color = TextSecondary,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

//Billing & Parts Section
@Composable
fun BillingPartsSection(
    sparePart: String,
    onSparePartChange: (String) -> Unit,
    extraCost: String,
    onExtraCostChange: (String) -> Unit,
    notes: String,
    onNotesChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        SectionLabel(text = "BILLING & PARTS")

        // Spare Parts field
        FieldLabel(text = "Add Spare Parts")
        StyledInputField(
            value          = sparePart,
            onValueChange  = onSparePartChange,
            placeholder    = "e.g. 215/55 R17 Tire",
            leadingIcon    = Icons.Default.Inventory2
        )

        // Additional Cost field
        FieldLabel(text = "Additional Cost (\$)")
        StyledInputField(
            value          = extraCost,
            onValueChange  = onExtraCostChange,
            placeholder    = "0.00",
            leadingIcon    = Icons.Default.AttachMoney,
            keyboardType   = KeyboardType.Decimal
        )

        // Add Internal Notes (dashed border)
        DashedNotesField(
            value         = notes,
            onValueChange = onNotesChange
        )
    }
}

//Styled Input Field
@Composable
fun StyledInputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(InputBg)
            .padding(horizontal = 14.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = leadingIcon,
            contentDescription = null,
            tint = TextSecondary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Box(modifier = Modifier.weight(1f)) {
            if (value.isEmpty()) {
                Text(text = placeholder, color = TextHint, fontSize = 14.sp)
            }
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(color = TextPrimary, fontSize = 14.sp),
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                cursorBrush = SolidColor(AccentBlue),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

//Dashed Notes Field
@Composable
fun DashedNotesField(value: String, onValueChange: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(InputBg)
            .border(
                width = 1.dp,
                color = DashedBorder,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 14.dp, vertical = 16.dp)
    ) {
        if (value.isEmpty()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.NoteAdd,
                    contentDescription = null,
                    tint = TextSecondary,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Add Internal Notes",
                    color = TextSecondary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(color = TextPrimary, fontSize = 14.sp),
            cursorBrush = SolidColor(AccentBlue),
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 24.dp)
        )
    }
}

//Finish Job Button
@Composable
fun FinishJobButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkBg)
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AccentBlue,
                contentColor   = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
        ) {
            Text(
                text       = "Finish Job",
                fontSize   = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.3.sp
            )
        }
    }
}

//Helpers
//fun SectionLabel2(text: String) {
//    Text(
//        text          = text,
//        color         = LabelColor,
//        fontSize      = 11.sp,
//        fontWeight    = FontWeight.SemiBold,
//        letterSpacing = 1.2.sp
//    )
//}

@Composable
private fun FieldLabel(text: String) {
    Text(
        text       = text,
        color      = TextPrimary,
        fontSize   = 13.sp,
        fontWeight = FontWeight.Medium
    )
}

//Preview
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ActiveJobScreenPreview() {
    MaterialTheme {
        ActiveJobScreen()
    }
}