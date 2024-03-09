package com.unitech.learning

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.unitech.learning.screen.SignUpScreen
import com.unitech.learning.screen.login.LoginScreen
import com.unitech.learning.ui.theme.LearningTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearningTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SignUpScreen { navigateToLogin() }
                }
            }
        }
    }
    private fun navigateToLogin() {
        val intent = Intent(
            this, LoginScreen::class.java)
        startActivity(intent)
        finish()
    }
}