package com.upn.movilapp3431

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.upn.movilapp3431.entities.Transaction
import com.upn.movilapp3431.ui.theme.MovilApp3431Theme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

class TransactionListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val transaction = listOf(
            Transaction("Pago recibo de Luz", "23/08/2025",156.10,"Gasto"),
            Transaction("Pago recibo de Agua", "23/08/2025",23.00,"Gasto"),
            Transaction("Sueldo mensual", "20/08/2025",1971.20,"Ingreso"),
            Transaction("Compra de mando para pc", "20/07/2025",130.00,"Gasto"),
            Transaction("Sueldo por trabajo menor", "05/06/2025",250.00,"Ingreso"),
            Transaction("Sueldo por corte de stikers","07/07/2025",25.00,"Ingreso")
        )
        setContent {
            MovilApp3431Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LazyColumn(modifier = Modifier.padding(innerPadding)) {
                        items(transaction.size){index ->
                            val transaction = transaction[index]
                            Card (
                                modifier = Modifier.fillMaxWidth().padding(8.dp)
                                    .clickable{println("Clicked on ${transaction.description}")},
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFFF5F5F5)),
                                elevation = CardDefaults.cardElevation(4.dp)
                                ){
                                Row(modifier = Modifier.fillMaxWidth().padding(8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text(
                                        text = transaction.description,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                    val colorMount = if (transaction.type=="Ingreso")Color.Blue else Color.Red
                                    Text(
                                        text = "S/${transaction.amount}",
                                        color = colorMount,
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Text(
                                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp),
                                    text = transaction.date,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}