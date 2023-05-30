package com.example.gestion_sispac.ui.theme.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import com.example.gestion_sispac.R

import com.example.gestion_sispac.LightBluePer

import androidx.compose.ui.text.style.TextAlign
import com.example.gestion_sispac.ui.theme.model.Book



val book1 = Book(title = "El principito", isbn = "1",
    classification = "Fantasía", publisher = "Springer", status = true)
val book2 = Book(title = "Las mil y una noches", isbn = "1",
    classification = "Ciencia Ficción", publisher = "Hispana", status = true)
val book3 = Book(title = "El coronel no tiene quien le escriba", isbn = "1",
    classification = "Narrativa", publisher = "Pearson", status = false)
val book4 = Book(title = "Margarita", isbn = "1",
    classification = "Novela", publisher = "Big House", status = true)

val books : List<Book> = listOf(book1, book2, book3, book4)


@Composable
fun ItemBook(book: Book) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(color = LightBluePer, shape = RoundedCornerShape(6.dp))

            ){
        Column {
            Text("Titulo: " + book.title,
                style = MaterialTheme.typography.bodyLarge)
            Text(text = if (book.status) {
                "DISPONIBLE"
            } else {
                "NO DISPONIBLE"
            },
                style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier
                .height(16.dp)
                .fillMaxWidth())
        }

    }

}

@Composable
fun ShowBooks(list : List<Book>) {

    LazyColumn (
        verticalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(vertical = 32.dp, horizontal = 12.dp)
            ){
        item {
            Text(text = "CATÁLOGO DE LIBROS",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())
        }
        item {
            Icon(painter = painterResource(id = R.drawable.ic_books_bigger),
                contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            )
        }
        items(list.size) {
            index -> ItemBook(book = list.get(index))
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun SearchBar() {
    var search by remember { mutableStateOf(value = "") }
    OutlinedTextField(value = search,
        onValueChange = {search = it},
        trailingIcon = { Icon(painter = painterResource(id = R.drawable.ic_search),
            contentDescription = null)},
        label = {Text(text = "Buscar",
        style = MaterialTheme.typography.bodyLarge)},
        placeholder = { Text(text = "Barra de búsqueda")},
        modifier = Modifier.
        fillMaxWidth()
    )

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun CatalogScreen() {
    Column {
        SearchBar()
        FilterBooks()
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(24.dp))
        ShowBooks(list = books)
    }
}
/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun OptionsBar() {
    TopAppBar(title = {Text(text = "Opciones",
        style = MaterialTheme.typography.bodyLarge) })
}
 */

@Composable
fun FilterRadio(valor : String) {
    var option by remember {mutableStateOf(value = false)}
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        RadioButton(selected = option, onClick = { option = !option
        /*DEJAR ESPACIO PARA FUNCION COMPOSABLE CON LA LOGICA*/})
        Text(text = valor, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
@Preview
fun FilterBooks(){
    Row (
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
            ){
        FilterRadio(valor = "Autor")
        FilterRadio(valor = "Categoría")
        FilterRadio(valor = "Editorial")
    }
}