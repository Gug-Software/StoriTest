package com.jkgug.example.storitest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.jkgug.example.storitest.ui.components.StoriTestNavHost
import com.jkgug.example.storitest.ui.theme.StoriTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StoriTestApp(name = "algo")
        }
    }
}

@Composable
fun StoriTestApp(
    name: String
) {
    StoriTestTheme {
        val navController = rememberNavController()
        Surface(tonalElevation = dimensionResource(id = R.dimen.tonal_elevation)) {
            Scaffold { innerPadding ->
                StoriTestNavHost(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}