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

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val preferences = getSharedPreferences("com.upn.movilapp3431", MODE_PRIVATE)
        val estaLogueado = preferences.getBoolean("ESTA_LOGUEADO", false)

//        auth.createUserWithEmailAndPassword("miguel@gmail.com", "1234567")
//            .addOnSuccessListener { task ->
//                Log.i("MAIN_APP", task.user!!.uid)
//            }
//            .addOnFailureListener { error ->
//                Log.e("MAIN_APP", "Error al crear el usuario", error)
//            }


//        val db = Firebase.firestore
//        /* Buscar Contacts */
//        db.collection("contacts")
//            .whereEqualTo("name", "Luis")
//            .get()
//            .addOnSuccessListener { result ->
//                for(item in result) {
//                    val contact = item.toObject(Contact::class.java)
//                    Log.i("MAIN_APP", contact.toString())
//                }
//            }.addOnFailureListener { error ->
//                Log.e("MAIN_APP", "Error al obtener los contactos", error)
//            }



        /* Crear un usuario en FireStore */
//        val contact = Contact("2", "Carlos", "999999", "")
//        Log.i("MAIN_APP", "Se va a crear el contacto")
//        db.collection("contacts")
//            .add(contact)
//            .addOnSuccessListener{ document ->
//                Log.i("MAIN_APP", "Se creo el contacto corrctamente")
//            }.addOnFailureListener { error ->
//                Log.e("MAIN_APP", "Error al crear el contacto", error)
//            }




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
                        Text("Inicio de Sesión", style = MaterialTheme.typography.headlineMedium) // 14sp

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
                                auth.signInWithEmailAndPassword(username, password)
                                    .addOnSuccessListener { task ->
                                        Log.i("MAIN_APP", "Usuario logueado ${task.user?.uid}")
                                        // guardar en shared prefrences

                                    }
                                    .addOnFailureListener { error ->
                                        Log.e("MAIN_APP", "Error al loguear el usuario", error)

                                    }

//                                val database = Firebase.database
//                                val usersRef = database.getReference("users")
//
//                                usersRef.addValueEventListener(object : ValueEventListener {
//                                    var loggedUser: User? = null
//                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
//                                        for (item in dataSnapshot.children) {
//                                            val user = item.getValue(User::class.java)!!
//                                             if (user.password == password && user.username == username) {
//                                                 loggedUser = user
//                                             }
//                                        }
//
//                                        if (loggedUser == null) {
//                                            Toast.makeText(context, "Usuario y contraseña incorrectos", Toast.LENGTH_SHORT).show()
//                                        } else {
//                                            Toast.makeText(context, "Usuario correcto", Toast.LENGTH_SHORT).show()
//                                        }
//
//                                    }
//
//                                    override fun onCancelled(error: DatabaseError) {
//
//                                    }
//                                })



//                                val userRef = usersRef.child(username)
//                                userRef.get().addOnSuccessListener { dataSnapshot ->
//                                    if (dataSnapshot.exists()) {
//
//                                        Log.i("MAIN_APP", dataSnapshot.value.toString())
//
//                                        val user = dataSnapshot.getValue(User::class.java)!!
//
//                                        if (user.password == password) {
//                                            preferences.edit {
//                                                putBoolean("ESTA_LOGUEADO", true)
//                                                putString("USERNAME", username)
//                                                putString("ROLE", user.role)
//                                            }
//
//                                            val intent = Intent(
//                                                context,
//                                                FirebaseRealtimeDatabaseActivity::class.java
//                                            )
//                                            context.startActivity(intent)
//                                            finish()
//                                        } else {
//                                            Toast.makeText(context, "Usuario y contraseña incorrectos", Toast.LENGTH_SHORT).show()
//                                        }
//
//                                    } else {
//                                        Log.i("MAIN_APP", "No existe $username")
//                                        Toast.makeText(context, "Usuario y contraseña incorrectos", Toast.LENGTH_SHORT).show()
//                                    }
//                                }


                                // validar contra firebase


                            },
                            modifier = Modifier.padding(top = 16.dp)
                        ) {
                            Text("Ingresar")
                        }
                    }
                }
            }
        }
    }
}