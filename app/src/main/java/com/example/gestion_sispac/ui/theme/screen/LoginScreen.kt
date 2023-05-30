package com.example.pruebasgui.ui.theme.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Colors
import com.example.pruebasgui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TxtField(label: String, placeholder: String) {
    var txt by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = txt,
        label = {Text(text = label)},
        onValueChange = {txt = it},
        placeholder = {Text(text = placeholder)}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun PassField() {
    var passwordVisibility by remember { mutableStateOf(false) }
    val icon = if (passwordVisibility) {
        painterResource(id = R.drawable.ic_password_view)
    } else {
        painterResource(id = R.drawable.ic_password_not_view)
    }
    var txt by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = txt,
        onValueChange = {txt = it},
        label = {Text(text = "Contraseña")},
        placeholder = {Text(text = "Contraseña")},
        keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = icon, contentDescription = null)
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        maxLines = 1,
        singleLine = true
    )
}
@Composable
@Preview
fun LogButton() {
    Button(onClick = { /*TODO*/ },
        modifier = Modifier.padding(16.dp)) {
       Text("Ingresar")
    }
}

@Composable
@Preview
fun FormLogin() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            style = MaterialTheme.typography.titleLarge,
            text = "SISPAC"
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            style = MaterialTheme.typography.titleLarge,
            text = "Inicio de Sesión"
        )
        TxtField("Usuario", "Usuario")
        Spacer(modifier = Modifier.size(12.dp))
        PassField()
        Spacer(modifier = Modifier.size(12.dp))
        LogButton()
    }

}
