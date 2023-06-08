package com.example.gestion_sispac.ui.theme.screen

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gestion_sispac.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoanCreate() {
    var beginLoan: String by rememberSaveable{ mutableStateOf("") }
    var endLoan: String by rememberSaveable { mutableStateOf("") }
    val yearDl: Int
    val monthDl: Int
    val dayDl: Int
    val mCalendar: Calendar = Calendar.getInstance()
    yearDl = mCalendar.get(Calendar.YEAR)
    monthDl = mCalendar.get(Calendar.MONTH)
    dayDl = mCalendar.get(Calendar.DAY_OF_MONTH)

    val bDatePickerDialog = DatePickerDialog(
        LocalContext.current,
        { datePicker: DatePicker, year: Int, month: Int, day: Int ->
            val formattedMonth = (month + 1).toString().padStart(2, '0') // Add leading zero if necessary
            val formattedDay = day.toString().padStart(2, '0') // Add leading zero if necessary
            beginLoan = "$year-$formattedMonth-$formattedDay"
        },
        yearDl,
        monthDl,
        dayDl,
    )
    val eDatePickerDialog = DatePickerDialog(
        LocalContext.current,
        { datePicker: DatePicker, year: Int, month: Int, day: Int ->
            val formattedMonth = (month + 1).toString().padStart(2, '0') // Add leading zero if necessary
            val formattedDay = day.toString().padStart(2, '0') // Add leading zero if necessary
            endLoan = "$year-$formattedMonth-$formattedDay"
        },
        yearDl,
        monthDl,
        dayDl,
    )
    Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("Prestar Libros")
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
        Row {
            OutlinedTextField(value = beginLoan,
                onValueChange = { beginLoan = it },
                readOnly = true,
                label = { Text("Fecha de Inicio") })
            IconButton(onClick = { bDatePickerDialog.show() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = null
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
        Row {
            OutlinedTextField(value = endLoan, onValueChange = { endLoan = it }, readOnly = true,
                label = { Text("Fecha de Fin") })
            IconButton(onClick = { eDatePickerDialog.show() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = null
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(vertical = 32.dp, horizontal = 12.dp)
        ) {
            items(cart.size) { index ->
                ItemBook(cart[index])
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
        Button(onClick = { }) {
            Text("Prestar")
        }
    }
}





/*


 */