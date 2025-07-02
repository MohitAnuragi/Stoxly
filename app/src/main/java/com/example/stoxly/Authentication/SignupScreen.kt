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
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
import java.util.Calendar


@Composable
fun SignupScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: AuthViewModel = viewModel()

    var fullName by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val calendar = Calendar.getInstance()
    val datePicker = remember {
        android.app.DatePickerDialog(
            context,
            { _, year, month, day ->
                dob = "${month + 1}/${day}/$year" // Format as MM/DD/YYYY
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        AppLogo()
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Create Account",
            color = AppColors.TextPrimary,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Full Name Field
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                "Full Name",
                color = AppColors.TextSecondary,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = AppColors.White,
                    unfocusedContainerColor = AppColors.Background,
                    focusedIndicatorColor = AppColors.AccentGreen,
                    unfocusedIndicatorColor = AppColors.TextSecondary.copy(alpha = 0.5f),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                ),
                shape = RoundedCornerShape(12.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Full name",
                        tint = AppColors.TextSecondary
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Date of Birth Field with Date Picker
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                "Date of Birth",
                color = AppColors.TextSecondary,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            OutlinedTextField(
                value = dob,
                onValueChange = { dob = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { datePicker.show() },
                enabled = false,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = AppColors.White,
                    disabledIndicatorColor = AppColors.TextSecondary.copy(alpha = 0.5f),
                    disabledTextColor = Color.Black,
                ),
                shape = RoundedCornerShape(12.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Cake,
                        contentDescription = "Date of birth",
                        tint = AppColors.TextSecondary
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = "Pick date",
                        tint = AppColors.TextSecondary,
                        modifier = Modifier.clickable { datePicker.show() }
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Email Field
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
                    focusedIndicatorColor = AppColors.AccentGreen,
                    unfocusedIndicatorColor = AppColors.TextSecondary.copy(alpha = 0.5f),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
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

        // Password Field
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
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = AppColors.White,
                    unfocusedContainerColor = AppColors.Background,
                    focusedIndicatorColor = AppColors.AccentGreen,
                    unfocusedIndicatorColor = AppColors.TextSecondary.copy(alpha = 0.5f),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                ),
                shape = RoundedCornerShape(12.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password",
                        tint = AppColors.TextSecondary
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Confirm Password Field
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                "Confirm Password",
                color = AppColors.TextSecondary,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = AppColors.White,
                    unfocusedContainerColor = AppColors.Background,
                    focusedIndicatorColor = AppColors.AccentGreen,
                    unfocusedIndicatorColor = AppColors.TextSecondary.copy(alpha = 0.5f),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                ),
                shape = RoundedCornerShape(12.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Confirm password",
                        tint = AppColors.TextSecondary
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Sign Up Button
        Button(
            onClick = {
                if (password == confirmPassword) {
                    viewModel.registerUser(email, password)
                } else {
                    viewModel.error.value = "Passwords do not match"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
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
            // Signup success
            else if(viewModel.isRegistered.value)
            {
                navController.navigate("top_movers")
            }
            else {
                Text(
                    "Sign Up",
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

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "Already have an account? Login",
            color = AppColors.TextSecondary,
            fontSize = 14.sp,
            modifier = Modifier.clickable {
                navController.navigate("login")
            }
        )
    }
}