package com.example.gestion_sispac.ui.theme.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.gestion_sispac.LightBluePer
import com.example.gestion_sispac.ui.theme.config.DataStoreManager
import com.example.gestion_sispac.ui.theme.model.LoanItem
import com.example.gestion_sispac.ui.theme.navigation.Destinations
import com.example.gestion_sispac.ui.theme.viewmodel.LoanViewModel
import kotlinx.coroutines.flow.first

@Composable
fun Loans(state: LoanViewModel.UIState) {
    if (!state.loans.isEmpty()) {
        LazyColumn (contentPadding = PaddingValues(vertical = 32.dp, horizontal = 12.dp),
        verticalArrangement = Arrangement.Center){

            item {
                Text(text = "MIS PRÉSTAMOS", style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth())
            }
            items(state.loans.size) {
                LoanIt(loan = state.loans[it])
            }
        }
    }
}

@Composable
fun LoanIt(loan : LoanItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(color = LightBluePer, shape = RoundedCornerShape(6.dp))
    ) {
            Column() {
                Text("Fecha de Emisión: ${loan.dateIssued}")
                Text("Fecha de Devolución: ${loan.returnDate}")
                Text("Libros prestados:" )
                for (i in 0 until loan.copies.size) {
                    Text(loan.copies[i].book.title)
                }
                Text("ESTADO: ${loan.loanStatus}")
            }
    }
}

@Composable
fun backButton(goBack : () -> Unit) {
    Button(
        onClick = goBack
    ) {
        Text("Regresar")
    }
}

@Composable
fun LoanScreen(navController: NavHostController) {
    val loanViewModel : LoanViewModel = viewModel()
    val context = LocalContext.current
    val cif = remember { mutableStateOf("") }
    val dataStore = DataStoreManager(context)
    val state by loanViewModel._loanState.collectAsState()

    LaunchedEffect(Unit) {
        val value = dataStore.getValue.first()
        Log.d("VALOR", "$value")
        if (value != null) {
            cif.value = value
            loanViewModel.loansUser(cif.value)
        }
    }
    Box(modifier = Modifier.
    fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Loans(state)
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart
            ) {
                backButton {
                    navController.navigate(Destinations.OptionScreen.route)
                }
            }
        }
    }
}