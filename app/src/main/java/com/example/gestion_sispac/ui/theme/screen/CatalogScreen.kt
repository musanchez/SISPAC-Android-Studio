package com.example.gestion_sispac.ui.theme.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import com.example.gestion_sispac.R

import com.example.gestion_sispac.LightBluePer

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.gestion_sispac.ui.theme.model.Author
import com.example.gestion_sispac.ui.theme.model.GenBookItem
import com.example.gestion_sispac.ui.theme.navigation.Destinations
import com.example.gestion_sispac.ui.theme.viewmodel.BookViewModel
import com.example.gestion_sispac.ui.theme.viewmodel.LoginModel


/*val book1 = Book(title = "El principito", isbn = "1",
    classification = "Fantasía", publisher = "Springer", status = true)
val book2 = Book(title = "Las mil y una noches", isbn = "1",
    classification = "Ciencia Ficción", publisher = "Hispana", status = true)
val book3 = Book(title = "El coronel no tiene quien le escriba", isbn = "1",
    classification = "Narrativa", publisher = "Pearson", status = false)
val book4 = Book(title = "Margarita", isbn = "1",
    classification = "Novela", publisher = "Big House", status = true)

val books : List<Book> = listOf(book1, book2, book3, book4)*/


@Composable
fun ItemBook(book : GenBookItem) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(color = LightBluePer, shape = RoundedCornerShape(6.dp))
            ){
        Column {
            //title stuff
            Text(" Título: " + book.title,
                style = MaterialTheme.typography.bodyLarge,
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier
                .height(8.dp)
                .fillMaxWidth())

            //ISBN Stuff
            Text(" ISBN: " + book.isbn,
                style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier
                .height(8.dp)
                .fillMaxWidth())

            //publisher info
            Text(" Editorial: " + book.publisher.name,
                style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier
                .height(8.dp)
                .fillMaxWidth())

            //Classification
            Text(" Categoría: " + book.classification.name,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier
                .height(8.dp)
                .fillMaxWidth())

            //Authors stuff
            val authors : List<Author> = book.authors
            var author_names = ""

            for(aut : Author in authors) {
                author_names += aut.name

                if (authors.last() != aut)
                    author_names += ", "
            }

            Text(" Autores: " + author_names,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier
                .height(2.dp)
                .fillMaxWidth())

            Button(onClick = {},
                modifier = Modifier.padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray/*,
                    contentColor = Color.Red*/)
            ) {
                Text("Agregar al Carrito", style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier
                .height(2.dp)
                .fillMaxWidth())
        }

    }

}

@Composable
fun ShowBooks(/*list : List<Book>*/ state : BookViewModel.UIState, navController: NavHostController) {
    var isIconClicked by remember { mutableStateOf(false) }

    if(!state.listLibro.isEmpty()) {
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(vertical = 32.dp, horizontal = 12.dp)
        ) {
            item {
                Text(
                    text = "CATÁLOGO DE LIBROS",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier
                    .height(4.dp)
                    .fillMaxWidth())
            }
            item {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_return_arrow),
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterVertically)
                            .clickable {
                                isIconClicked = !isIconClicked
                                if (isIconClicked)
                                    navController.navigate(Destinations.OptionScreen.route)
                            }
                    )

                    Spacer(modifier = Modifier
                        .weight(0.5f)
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_books_bigger),
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )

                    Spacer(modifier = Modifier
                        .weight(0.5f)
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_go_arrow),
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                Spacer(modifier = Modifier
                    .height(4.dp)
                    .fillMaxWidth())
            }
            items(/*list.size*/state.listLibro.size) { index ->
                ItemBook(state.listLibro.get(index))
            }
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
//@Preview
fun CatalogScreen(navController : NavHostController) {
    val bookViewModel : BookViewModel = viewModel()
    val state by bookViewModel.bookState.collectAsState()

    Column {
        SearchBar()
        FilterRadio()
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(24.dp))
        ShowBooks(state, navController)
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
fun FilterRadio() {
    var option by remember {mutableStateOf("")}
    Row (
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
            ){

        RadioButton(selected = option == "Autor", onClick = { option = "Autor"
        /*DEJAR ESPACIO PARA FUNCION COMPOSABLE CON LA LOGICA*/})
        Text(text = "Autor", style = MaterialTheme.typography.labelSmall)

        RadioButton(selected = option == "Editorial", onClick = { option = "Editorial"
            /*DEJAR ESPACIO PARA FUNCION COMPOSABLE CON LA LOGICA*/})
        Text(text = "Editorial", style = MaterialTheme.typography.labelSmall)

        RadioButton(selected = option == "Categoría", onClick = { option = "Categoría"
            /*DEJAR ESPACIO PARA FUNCION COMPOSABLE CON LA LOGICA*/})
        Text(text = "Categoría", style = MaterialTheme.typography.labelSmall)

        RadioButton(selected = option == "Titulo", onClick = { option = "Titulo"
            /*DEJAR ESPACIO PARA FUNCION COMPOSABLE CON LA LOGICA*/})
        Text(text = "Titulo", style = MaterialTheme.typography.labelSmall)
    }
}
