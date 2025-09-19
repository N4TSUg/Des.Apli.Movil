package com.upn.movilapp3431

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.upn.movilapp3431.ui.theme.MovilApp3431Theme
import androidx.core.content.edit
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.firestore
import com.upn.movilapp3431.entities.Contact
import com.upn.movilapp3431.entities.User

class AddNewUserActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val preferences = getSharedPreferences("com.upn.movilapp3431", MODE_PRIVATE)
        val estaLogueado = preferences.getBoolean("ESTA_LOGUEADO", false)
        setContent {
            MovilApp3431Theme {

                val context = LocalContext.current

                if (estaLogueado) {
                    val intent = Intent(context, FirebaseRealtimeDatabaseActivity::class.java)
                    context.startActivity(intent)
                    finish()
                }


                var username by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    LaunchedEffect(Unit) {
                        val usernamePref = preferences.getString("USERNAME", null)
                        if (usernamePref != null) {
                            username = usernamePref
                        }
                    }

                    Column(
                        modifier = Modifier.padding(innerPadding)
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center

                    ) {
                        Text("Crear nuevo usuario", style = MaterialTheme.typography.headlineMedium) // 14sp

                        OutlinedTextField(
                            value = username,
                            onValueChange = {it -> username = it},
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Email") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                        )
                        OutlinedTextField(
                            value = password,
                            onValueChange = {o -> password = o},
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            visualTransformation = PasswordVisualTransformation(),
                            label = { Text("Contraseña") },
                        )
                        Button(
                            onClick = {

                                val auth = Firebase.auth
                                        auth.createUserWithEmailAndPassword(username, password)
                                    .addOnSuccessListener { task ->
                                        Log.i("MAIN_APP", task.user!!.uid)
                                    }
                                    .addOnFailureListener { error ->
                                        Log.e("MAIN_APP", "Error al crear el usuario", error)
                                    }                          },
                            modifier = Modifier.padding(top = 16.dp)
                        ) {
                            Text("Agregar usuario")
                        }
                    }
                }
            }
        }
    }
}