package com.jkgug.example.storitest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.compose.rememberNavController
import com.jkgug.example.storitest.ui.screen.navhost.StoriTestNavHost
import com.jkgug.example.storitest.ui.theme.StoriTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { StoriTestApp() }
    }
}

@Composable
fun StoriTestApp() {

    StoriTestTheme {
        val navController = rememberNavController()
        val snackBarHostState = remember { SnackbarHostState() }
        Surface(
            tonalElevation = dimensionResource(id = R.dimen.tonal_elevation),
        ) {
            Scaffold(
                snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
            ) { innerPadding ->
                StoriTestNavHost(
                    navController = navController,
                    snackBarHostState = snackBarHostState,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}