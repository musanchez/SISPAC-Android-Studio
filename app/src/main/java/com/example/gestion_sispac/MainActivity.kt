package com.example.gestion_sispac

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gestion_sispac.ui.model.LoginModel
import com.example.gestion_sispac.ui.theme.GestionSISPACTheme

class MainActivity : ComponentActivity() {

    private val loginModel : LoginModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GestionSISPACTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(loginModel)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
//@Preview
fun LoginScreen(loginModel: LoginModel) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login"/*,
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontSize = 36.sp
            )*/
        )
        Spacer(modifier = Modifier.height(40.dp))

        val context = LocalContext.current

        TextField(value = loginModel.name,
            onValueChange = {loginModel.name = it},
            label = {Text("usuario")}
        )
        Spacer(modifier = Modifier.height(10.dp))

        var passwordVisibility: Boolean by rememberSaveable{ mutableStateOf(false) }
        val icon = if (passwordVisibility) {
            painterResource(id = R.drawable.ic_design_visibility)
        } else {
            painterResource(id = R.drawable.ic_design_visibility_off)
        }
        TextField(value = loginModel.password,
            onValueChange = {loginModel.password = it},
            label = { Text("contraseña")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            //maxlines = 1,
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(painter = icon, contentDescription = null)
                }
            },
            visualTransformation = if (passwordVisibility) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {val loginResponse = loginModel.onSubmit(context)},
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Login")
            }
        }
    }



}
