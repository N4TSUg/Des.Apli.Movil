package com.upn.movilapp3431

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.upn.movilapp3431.entities.Contact
import com.upn.movilapp3431.ui.theme.MovilApp3431Theme

class ListaJetPackActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val contacts = listOf(
            Contact("Juan Perez", "123456789"),
            Contact("Maria Gomez", "987654321"),
            Contact("Carlos Sanchez", "555555555"),
            Contact("Ana Torres", "444444444"),
            Contact("Luis Ramirez", "333333333"),
            Contact("Sofia Fernandez", "222222222"),
            Contact("Miguel Diaz", "111111111"),
            Contact("Laura Morales", "666666666"),
            Contact("Diego Castro", "777777777"),
            Contact("Elena Rojas", "888888888"),
            Contact("Pedro Vargas", "999999999"),
            Contact("Marta Silva", "000000000"),
            Contact("Jorge Herrera", "121212121"),
            Contact("Lucia Mendoza", "343434343"),
            Contact("Andres Fuentes", "565656565"),
            Contact("Carmen Ortiz", "787878787"),
            Contact("Rafael Aguilar", "909090909"),
        )

        setContent {
            MovilApp3431Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LazyColumn {
                        items(contacts.size) { index ->
                            val contact = contacts[index]
                            Column(
                                modifier = Modifier.clickable {
                                    // Define the action to perform when the Column is clicked
                                    println("Clicked on: ${contact.name}")
                                    // You can also navigate, show a Toast, update state, etc.
                                }

                            ) {
                                Text(
                                    text = contact.name,
                                    modifier = Modifier.padding(innerPadding)
                                )
                                Text(
                                    text = contact.phone,
                                    modifier = Modifier.padding(innerPadding)
                                )
                                HorizontalDivider(
                                    Modifier,
                                    DividerDefaults.Thickness,
                                    DividerDefaults.color
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovilApp3431Theme {
        Greeting("Android")
    }
}
 */