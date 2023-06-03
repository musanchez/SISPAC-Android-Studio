package com.example.gestion_sispac.ui.theme.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.gestion_sispac.R
import com.example.gestion_sispac.ui.theme.navigation.Destinations
import com.example.gestion_sispac.ui.theme.viewmodel.LoginModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TxtField(label: String, placeholder: String, loginModel: LoginModel) {
    OutlinedTextField(
        value = loginModel.cif,
        label = {Text(text = label)},
        onValueChange = {loginModel.cif = it},
        placeholder = {Text(text = placeholder)}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassField(loginModel: LoginModel) {
    var passwordVisibility by remember { mutableStateOf(false) }
    val icon = if (passwordVisibility) {
        painterResource(id = R.drawable.ic_password_view)
    } else {
        painterResource(id = R.drawable.ic_password_not_view)
    }
    OutlinedTextField(
        value = loginModel.password,
        onValueChange = {loginModel.password = it},
        label = {Text(text = "Contraseña")},
        placeholder = {Text(text = "Contraseña")},
        keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = ! passwordVisibility }) {
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
fun LogButton(loginModel : LoginModel) {
    Button(onClick = loginModel::onSubmit,
        modifier = Modifier.padding(16.dp)) {
       Text("Ingresar")
    }
}

@Composable
fun FormLogin(navController: NavHostController) {
    val loginModel : LoginModel = viewModel()
    val state by loginModel._state.collectAsState()
    val isLoading = remember { mutableStateOf(false) }
    val isSuccess = remember{ mutableStateOf(false) }
    var show by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(state) {
        isLoading.value = state._loading
        Log.d("LOADING",isLoading.toString())
        isSuccess.value = state.loginResponse.success
        Log.d("SUCCESS",isSuccess.toString())
    }

    if (isLoading.value) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }
    if (isSuccess.value) {
        //Log.d("BIENVENIDO","Bienvenido $loginModel.cif")
        //show = true
        navController.navigate(route = Destinations.OptionScreen.route)
    }
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
        TxtField("Usuario", "Usuario", loginModel)
        Spacer(modifier = Modifier.size(12.dp))
        PassField(loginModel)
        Spacer(modifier = Modifier.size(12.dp))
        LogButton(loginModel)
    }

}
