package com.example.roadassist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roadassist.ui.theme.*

@Composable
fun LoginScreen(onLoginSuccess : () -> Unit, onSignupClick : () -> Unit, onForgotPasswordClick: () -> Unit) {

    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var selectedTab by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Login",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A1A2E)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = "Login Image",
            modifier = Modifier.size(140.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        LoginSignupTabs(
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it },
            onSignupClick = onSignupClick
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("Name", color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Password", color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = { onForgotPasswordClick() }) {
                Text(
                    text = "Forgot password?",
                    color = OrangeAccent,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onLoginSuccess() },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = OrangeAccent)
        ) {
            Text(
                text = "Login",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = {},
                modifier = Modifier.weight(1f).height(46.dp),
                shape = RoundedCornerShape(30.dp),
                border = BorderStroke(1.dp, Color.LightGray)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Google",
                    modifier = Modifier.size(18.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(6.dp))

                Text(text = "Google", color = Color.DarkGray, fontSize = 13.sp)
            }

            OutlinedButton(
                onClick = {},
                modifier = Modifier.weight(1f).height(46.dp),
                shape = RoundedCornerShape(30.dp),
                border = BorderStroke(1.dp, Color.LightGray)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "Facebook",
                    modifier = Modifier.size(18.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(6.dp))

                Text(text = "Facebook", color = Color.DarkGray, fontSize = 13.sp)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}
@Composable
private fun LoginSignupTabs(selectedTab  : Int, onTabSelected: (Int) -> Unit, onSignupClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .height(46.dp)
            .background(Color(0xFFF5F5F5), RoundedCornerShape(30.dp))
            .padding(4.dp)
    ) {
        Button(
            onClick = { onTabSelected(0); onSignupClick() },
            modifier = Modifier.weight(1f).fillMaxHeight(),
            shape = RoundedCornerShape(26.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedTab == 0) OrangeAccent else Color.Transparent,
                contentColor = if (selectedTab == 0) Color.White else Color.Gray
            ),
            elevation = ButtonDefaults.buttonElevation(0.dp)
        ) {
            Text(text = "Signup", fontWeight = FontWeight.SemiBold)
        }

        Button(
            onClick = { onTabSelected(1) },
            modifier = Modifier.weight(1f).fillMaxHeight(),
            shape = RoundedCornerShape(26.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedTab == 1) OrangeAccent else Color.Transparent,
                contentColor   = if (selectedTab == 1) Color.White else Color.Gray
            ),
            elevation = ButtonDefaults.buttonElevation(0.dp)
        ) {
            Text(text = "Login", fontWeight = FontWeight.SemiBold)
        }
    }
}