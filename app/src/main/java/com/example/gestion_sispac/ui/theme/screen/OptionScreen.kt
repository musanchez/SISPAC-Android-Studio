package com.example.pruebasgui.ui.theme.screen

import android.graphics.drawable.Icon
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pruebasgui.R
import com.example.pruebasgui.ui.theme.BlackPer


/*
@Composable
@Preview
fun showOptions() {

    Row {
        Icon(painter = painterResource(id = R.drawable.ic_catalog), contentDescription = null,
        modifier = Modifier.clickable {  },)

    }
}

 */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun ToolBar() {
    TopAppBar(title = {Text("Opciones")})

}


@Composable
//@Preview
fun IconTitle(title: String, icon: Painter) {
    Box (
        modifier = Modifier.clickable {  }
            ){
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Icon(painter = icon, contentDescription = null)
            Text(text = title, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}

@Composable
@Preview
fun OptionList() {
    Row (
        verticalAlignment = Alignment.CenterVertically
            ){
        IconTitle(title = "Catálogo", icon = painterResource(id = R.drawable.ic_catalog))
        Spacer(modifier = Modifier.size(16.dp))
        IconTitle(title = "Préstamos", icon = painterResource(id = R.drawable.ic_loans))
        Spacer(modifier = Modifier.size(16.dp))
        IconTitle(title = "Ajustes", icon = painterResource(id = R.drawable.ic_settings))
    }
}

@Composable
@Preview
fun OptionScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceEvenly){
        Text("BIENVENIDO A SISPAC MARCOS")
        Spacer(modifier = Modifier.height(24.dp))
        Text("¿Qué deseas hacer?")
        Spacer(modifier = Modifier.height(24.dp))
        OptionList()
    }
}