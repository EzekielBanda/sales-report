package com.unitech.learning.screen.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.unitech.learning.MainActivity
import com.unitech.learning.navigation.LoginNavigation
import com.unitech.learning.screen.login.ui.theme.LearningTheme
import com.unitech.learning.screen.performRegistration
import com.unitech.learning.ui.theme.DeepBlue
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.security.auth.callback.Callback

class LoginScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearningTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //LoginNavigation()
                    LoginUi {
                        navigateToHome()
                    }
                }
            }
        }
    }
    private fun navigateToHome() {
        val intent = Intent(
            this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}


@Composable
fun LoginUi(onLoginSuccess: () -> Unit) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()


    var isLogging by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Login To Learn",
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
            label = { Text(text = "Enter your email here") },
            shape = MaterialTheme.shapes.medium,
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "email")
            },
            //Turn keyboard on for email input
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            value = email,
            onValueChange = { email = it }
        )
        Spacer(modifier = Modifier.size(8.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            label = { Text(text = "Enter your Password here") },
            shape = MaterialTheme.shapes.medium,
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "password")
            },
            //Turn keyboard on for Password
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            value = password,
            onValueChange = { password = it }
        )

        Spacer(modifier = Modifier.size(8.dp))

        OutlinedButton(
            onClick = {

                if (email.isNotEmpty() && password.isNotEmpty()) {
                    isLogging = true
                    performLogin(email, password) { success ->
                        isLogging = false // Set back to false after registration completes
                        if (success) {
                            // Registration successful, handle accordingly
                            onLoginSuccess()
                        } else {
                            // Registration failed, handle accordingly
                            isError = true
                            errorMessage = "LogIn failed. Please try again."
                        }
                    }
                } else {
                    isError = true
                    errorMessage = "Email and Password cannot be Empty."
                }
                /*coroutineScope.launch {
                    val loginSuccessful = performLogin(email, password)
                    if (loginSuccessful) {
                        //Navigate to Main Activity
                        Log.d("Login", "Login successful, navigating to main")
                        onLoginSuccess()
                    } else {
                        isError = true
                        errorMessage = "Wrong Email or Password"
                    }
                }
                 */
            },
            enabled = !isLogging,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {

            if (isLogging) {
                CircularProgressIndicator()
            } else {

                Text(
                    text = "Login",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

        }
    }
}

fun performLogin(email: String,
                 password: String,
                 callback: (Boolean) -> Unit) {

    val auth = FirebaseAuth.getInstance()

    try {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true)
                } else {
                    val exception = task.exception
                    callback(false)
                }
            }

    } catch (_: Exception) {

    }
}

/*@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LearningTheme {
        LoginUi()
    }
}
 */