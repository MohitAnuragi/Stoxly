package com.example.stoxly.Authentication

import AuthViewModel
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.stoxly.components.AppColors


@Composable
fun LoginScreen(navController: NavController) {
    val viewModel: AuthViewModel = viewModel()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Header with logo
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 30.dp)
        ) {
            AppLogo()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Welcome Back",
                color = AppColors.TextPrimary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Login to continue",
                color = AppColors.TextSecondary,
                fontSize = 20.sp
            )
        }

        // Email field
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                "Email",
                color = AppColors.TextSecondary,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = AppColors.White,
                    unfocusedContainerColor = AppColors.Background,
                    disabledContainerColor = AppColors.Background,
                    focusedIndicatorColor = AppColors.AccentGreen,
                    unfocusedIndicatorColor = AppColors.TextSecondary.copy(alpha = 0.5f),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedLabelColor = AppColors.AccentGreen,
                    cursorColor = AppColors.AccentGreen,
                ),
                shape = RoundedCornerShape(12.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email",
                        tint = AppColors.TextSecondary
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Password field
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                "Password",
                color = AppColors.TextSecondary,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = AppColors.White,
                    unfocusedContainerColor = AppColors.Background,
                    disabledContainerColor = AppColors.CardBackground,
                    focusedIndicatorColor = AppColors.AccentGreen,
                    unfocusedIndicatorColor = AppColors.TextSecondary.copy(alpha = 0.5f),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedLabelColor = AppColors.AccentGreen,
                    cursorColor = AppColors.AccentGreen,
                ),
                shape = RoundedCornerShape(12.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password",
                        tint = AppColors.TextSecondary
                    )
                },
                trailingIcon = {
                    if (password.isNotEmpty()) {
                        IconButton(onClick = { password = "" }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear password",
                                tint = AppColors.TextSecondary
                            )
                        }
                    }
                }
            )
        }

        // Login button
        Button(
            onClick = { viewModel.loginUser(email, password) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppColors.AccentGreen,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp
            )
        ) {
            if (viewModel.isLoading.value) {
                CircularProgressIndicator(
                    color = Color.White,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(20.dp)
                )
            }
          // success
           else if(viewModel.isLoggedIn.value) {
                navController.navigate("top_movers")
            }

            else {
                Text(
                    "Login",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Error message
        viewModel.error.value?.let { error ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                error,
                color = AppColors.AccentRed,
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // Sign up link
        Row(
            modifier = Modifier.padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Don't have an account? ",
                color = AppColors.TextSecondary,
                fontSize = 14.sp
            )
            Text(
                "Sign up",
                color = AppColors.AccentGreen,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    navController.navigate("signup")
                }
            )
        }

    }
}



