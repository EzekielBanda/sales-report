package com.unitech.learning.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unitech.learning.screen.login.LoginUi

@Composable
fun LoginNavigation() {
    val navController = rememberNavController()

    /*NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginUi(navController)
        }

        composable("main") {

        }
    }

     */
}