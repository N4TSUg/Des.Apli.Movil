package com.upn.movilapp3431.composables

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.upn.movilapp3431.viewmodels.ContactListViewModel
import com.upn.movilapp3431.viewmodels.SerieListViewModel

@Composable
fun Contador() {
    Column {
        var counter by remember {  mutableIntStateOf(0) }

        Text(text = "$counter")
        Button(
            onClick = {
                counter++
                Log.i("MAIN_APP", "Counter: $counter")
            }
        ) {
            Text(text = "Click Me!")
        }
    }
}

@Composable
fun ListaElementos(viewModel : ContactListViewModel) {
    if (viewModel.hasError) {
        Text(text = "Error al cargar los datos")
    } else {
        if (viewModel.isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(viewModel.contacts) { contact ->
                    Text(text = "${contact.name} - ${contact.phone}")
                }
            }
        }
    }
}

@Composable
fun ListaSeries(viewModel : SerieListViewModel) {
    if (viewModel.hasError) {
        Text(text = "Error al cargar los datos")
    } else {
        if (viewModel.isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(viewModel.series) { serie ->
                    Text(text = "${serie.name} - ${serie.numChapters}")
                }
            }
        }
    }
}