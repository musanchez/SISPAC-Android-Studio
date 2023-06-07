package com.example.gestion_sispac.ui.theme.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.gestion_sispac.R
import com.example.gestion_sispac.ui.theme.navigation.Destinations





//@Preview
@Composable
fun IconTitle(title: String, goToOption : () -> Unit) {
    Button (onClick = {goToOption()
        Log.d(",MENSAJE", "CLICK EN OPCION")}) {
            //Icon(painter = icon, contentDescription = null, modifier = Modifier.clickable { goToOption
                //Log.d(",MENSAJE", "CLICK EN OPCION") })
            Text(text = title, style = MaterialTheme.typography.bodyLarge, fontSize = 14.sp)
            //Spacer(modifier = Modifier.size(10.dp))
    }
}

@Composable
fun ProfileIconTitle(goToOption : () -> Unit) {
    Button (onClick = {goToOption() }) {
        Icon(painter = painterResource(R.drawable.ic_profile),
            contentDescription = null,
            //add clickable
        )
    }
}

@Composable
fun OptionScreen(navController: NavHostController) {
    /*Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        ProfileIconTitle() {

        }
    }*/
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceEvenly,
    modifier = Modifier.fillMaxSize()){
        Text("BIENVENIDO A SISPAC")
        Spacer(modifier = Modifier.height(24.dp))
        Text("¿Qué deseas hacer?")
        Spacer(modifier = Modifier.height(24.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Spacer(modifier = Modifier.size(10.dp))
            IconTitle(title = "Catálogo") {
                navController.navigate(Destinations.CatalogScreen.route)
            }
            Spacer(modifier = Modifier.size(10.dp))
            IconTitle(title = "Préstamos") {

            }
            Spacer(modifier = Modifier.size(10.dp))
            IconTitle(title = "Ajustes") {

            }
        }
    }
}
/*
icon = painterResource(id = R.drawable.ic_catalog)
icon = painterResource(id = R.drawable.ic_loans)
icon = painterResource(id = R.drawable.ic_settings)
 */
