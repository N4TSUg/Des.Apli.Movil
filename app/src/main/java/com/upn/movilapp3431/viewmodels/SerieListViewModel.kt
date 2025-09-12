package com.upn.movilapp3431.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.upn.movilapp3431.entities.Serie


class SerieListViewModel: ViewModel() {
    val series = mutableStateListOf<Serie>()
    var isLoading by mutableStateOf(false)
    var hasError by mutableStateOf(false)

    private val database = Firebase.database
    private val seriesRef = database.getReference("series")

    init {
        loadCSeries()
    }

    private fun loadCSeries() {
        isLoading = true

        seriesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val tempSeries = mutableListOf<Serie>()
                for (item in dataSnapshot.children) {
                    Log.d("MAIN_APP", "Value is: $item")
                    val serie = item.getValue(Serie::class.java)
                    tempSeries.add(serie!!)
                }

                series.addAll(tempSeries)
                isLoading = false

                Log.d("MAIN_APP", "Value is: ${series.size}")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MAIN_APP", "Failed to read value.", error.toException())
                isLoading = false
                hasError = true
            }
        })
    }
}