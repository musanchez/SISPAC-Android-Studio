package com.example.gestion_sispac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.gestion_sispac.ui.theme.navigation.NavigationHost
import com.example.gestion_sispac.ui.theme.screen.CatalogScreen
import com.example.gestion_sispac.ui.theme.screen.DateSelection
import com.example.gestion_sispac.ui.theme.viewmodel.LoginModel

class MainActivity : ComponentActivity() {

    private val loginModel by viewModels<LoginModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SispacTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   // NavigationHost()
                    //SelectDatesScreen(cart = emptyList())
                    DateSelection()
                }
            }
        }
    }
}


