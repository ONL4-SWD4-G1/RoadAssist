package com.example.roadassist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roadassist.ui.theme.Background
import com.example.roadassist.ui.theme.DarkBlue
import com.example.roadassist.ui.theme.OrangeAccent
import com.example.roadassist.ui.theme.TextDark
import com.example.roadassist.ui.theme.TextGray
import com.example.roadassist.ui.theme.White

@Composable
fun OtherServiceFormScreen(onBackClick: () -> Unit = {}) {

    var vehicleType by remember { mutableStateOf("") }
    var manufacturer by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("") }
    var registrationNum by remember { mutableStateOf("") }
    var problemDescription by remember { mutableStateOf("") }

    Scaffold(containerColor = Background) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            TopBarWithBack(title = "Other Service", onBackClick = onBackClick)

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Vehicle details",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = TextDark
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    FormField(
                        value = vehicleType,
                        onValueChange = { vehicleType = it },
                        placeholder = "Vehicle Type"
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    FormField(
                        value = manufacturer,
                        onValueChange = { manufacturer = it },
                        placeholder = "Manufacturer"
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    FormField(
                        value = model,
                        onValueChange = { model = it },
                        placeholder = "Model"
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    FormField(
                        value = registrationNum,
                        onValueChange = { registrationNum = it },
                        placeholder = "Enter Registration number"
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Please describe the problem of vehicle in detail",
                        fontSize = 14.sp,
                        color = TextDark
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = problemDescription,
                        onValueChange = { problemDescription = it },
                        placeholder = {
                            Text(
                                "Describe issue here...",
                                color = TextGray,
                                fontSize = 13.sp
                            )
                        },
                        modifier = Modifier.fillMaxWidth().height(120.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = DarkBlue,
                            unfocusedBorderColor = Color(0xFFE0E0E0)
                        ),
                        maxLines = 5
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OrangeAccent)
            ) {
                Text(
                    "Book Service",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun FormField(value: String, onValueChange: (String) -> Unit, placeholder: String) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(placeholder,
                color = TextGray,
                fontSize = 13.sp
            )
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = DarkBlue,
            unfocusedBorderColor = Color(0xFFE0E0E0),
            focusedContainerColor = White,
            unfocusedContainerColor = White
        ),
        singleLine = true
    )
}
