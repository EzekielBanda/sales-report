package com.unitech.learning.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.unitech.learning.ui.theme.BlueStart
import com.unitech.learning.ui.theme.DeepBlue
import com.unitech.learning.ui.theme.GreenStart
import com.unitech.learning.ui.theme.LightGreen1
import com.unitech.learning.ui.theme.TextWhite

@Composable
fun SignUpScreen(onRegistrationSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var errorPasswordMessage by remember { mutableStateOf("") }

    var isRegistering by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Register To Learn",
            color = Color.White,
            fontFamily = FontFamily.SansSerif,
            fontSize = 20.sp,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Spacer(modifier = Modifier.size(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            label = { Text(text = "Enter Email here", color = TextWhite) },
            shape = MaterialTheme.shapes.medium,
            leadingIcon = {
                Icon(Icons.Default.Email,
                    contentDescription = "email",
                    tint = BlueStart)
            },
            //Turn keyboard on for email input
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            value = email,
            onValueChange = { email = it },
            isError = isError
        )
        // Add error message below email field
        if (isError && errorMessage.isNotEmpty()) {
            Text(
                errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.size(8.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            label = { Text(text = "Enter Password here",
                color = TextWhite) },
            shape = MaterialTheme.shapes.medium,
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "password",
                    tint = BlueStart)
            },
            //Turn keyboard on for Password
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            value = password,
            onValueChange = { password = it },
        )

        Spacer(modifier = Modifier.size(8.dp))

        if (isError) {
            Text(
                errorMessage, color = Color.Red
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        OutlinedButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = BlueStart,
                contentColor = TextWhite
            ),
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    // Check password length
                    if (password.length > 5) {
                        isRegistering = true
                        performRegistration(email, password) { success ->
                            isRegistering = false // Set back to false after registration completes
                            if (success) {
                                // Registration successful, handle accordingly
                                onRegistrationSuccess()
                            } else {
                                // Registration failed, handle accordingly
                                isError = true
                                errorMessage = "Registration failed. Please try again.\n" +
                                "Check your internet Connection"
                            }
                        }
                    } else {
                        isError = true
                        password = ""
                        errorMessage = "Password must be at least 6 Characters"
                    }

                } else {
                    isError = true
                    email = ""
                    password = ""
                    errorMessage = "Email and Password cannot be Empty."
                }
            },
            enabled = !isRegistering,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .height(50.dp)
        ) {

            if (isRegistering) {
                CircularProgressIndicator()
            } else {

                Text(
                    text = "Register",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

        }
    }
}


fun performRegistration(email: String,
                        password: String,
                        callback: (Boolean) -> Unit) {

    val auth = FirebaseAuth.getInstance()

    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Registration successful
                callback(true)
            } else {
                // Registration failed
                val exception = task.exception
                var errorMessage = ""

                // Check for FirebaseAuthUserCollisionException (email already exists)
                if (exception is FirebaseAuthUserCollisionException) {
                    errorMessage = "This email address is already registered. Please use a different email."
                } else {
                    // Handle other exceptions
                    errorMessage = "Registration failed. Please try again."
                }

                // Set error state and trigger UI update
                isError = true

                // Callback with false
                callback(false)
            }
        }
}
