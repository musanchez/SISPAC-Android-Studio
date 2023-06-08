package com.example.gestion_sispac.ui.theme.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gestion_sispac.R
import com.example.gestion_sispac.ui.theme.navigation.Destinations





//@Preview
@Composable
fun IconTitle(title: String, goToOption : () -> Unit) {
    Button (onClick = {goToOption()
        Log.d(",MENSAJE", "CLICK EN OPCION")}) {
            //Icon(painter = icon, contentDescription = null, modifier = Modifier.clickable { goToOption
                //Log.d(",MENSAJE", "CLICK EN OPCION") })
            Text(text = title, style = MaterialTheme.typography.bodyLarge, fontSize = 20.sp)
            //Spacer(modifier = Modifier.size(10.dp))
    }
}

@Composable
fun OptionScreen(navController: NavHostController) {
    var isIconClicked by remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp)
    ) {
        Column() {
            Icon(   //LOGOUT
                painter = painterResource(id = R.drawable.ic_logout),
                contentDescription = null,
                modifier = Modifier.clickable {
                    isIconClicked = !isIconClicked
                    if (isIconClicked)
                        navController.navigate(Destinations.LoginScreen.route)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Icon(   //LOGOUT
                painter = painterResource(id = R.drawable.ic_settings),
                contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.width(15.dp))
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize().padding(vertical = 80.dp)
    ) {
        Text("BIENVENIDO A SISPAC", fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(24.dp))

        Text("¿Qué deseas hacer?")
        Spacer(modifier = Modifier.height(24.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Spacer(modifier = Modifier.size(20.dp))
            IconTitle(title = "Catálogo") {
                navController.navigate(Destinations.CatalogScreen.route)
            }

            Spacer(modifier = Modifier.size(20.dp))
            IconTitle(title = "Préstamos") {
                navController.navigate(Destinations.LoanScreen.route)

            }
            Spacer(modifier = Modifier.size(20.dp))
        }
    }
}
/*
icon = painterResource(id = R.drawable.ic_catalog)
icon = painterResource(id = R.drawable.ic_loans)
icon = painterResource(id = R.drawable.ic_settings)
 */

@Composable
@Preview(showBackground = true)
fun previewOptions() {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp)
    ) {
        Column() {
            Icon(   //LOGOUT
                painter = painterResource(id = R.drawable.ic_logout),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Icon(   //LOGOUT
                painter = painterResource(id = R.drawable.ic_settings),
                contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.width(15.dp))
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize().padding(vertical = 80.dp)
    ) {
        Text("BIENVENIDO A SISPAC",
            fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(24.dp))
        Text("¿Qué deseas hacer?")
        Spacer(modifier = Modifier.height(12.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Spacer(modifier = Modifier.size(20.dp))
            IconTitle(title = "Catálogo") {
                //navController.navigate(Destinations.CatalogScreen.route)
            }

            Spacer(modifier = Modifier.size(20.dp))
            IconTitle(title = "Préstamos") {

            }
            Spacer(modifier = Modifier.size(20.dp))
        }
    }
}
