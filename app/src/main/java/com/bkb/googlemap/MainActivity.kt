package com.bkb.googlemap

import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import java.util.*

class MainActivity : AppCompatActivity() {
    var autoCompleteTextView: AutoCompleteTextView? = null
    var adapter: AutoCompleteAdapter? = null
    var responseView: TextView? = null
    var placesClient: PlacesClient? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        responseView = findViewById(R.id.response)

        val apiKey = getString(R.string.api_key)
        if (apiKey.isEmpty()) {
            responseView!!.setText(getString(R.string.error))
            return
        }

        // Setup Places Client

        // Setup Places Client
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, apiKey)
        }

        placesClient = Places.createClient(this)
        initAutoCompleteTextView()
    }


    private fun initAutoCompleteTextView() {
        autoCompleteTextView = findViewById(R.id.auto)
        autoCompleteTextView!!.setThreshold(1)
        autoCompleteTextView!!.setOnItemClickListener(autocompleteClickListener)
        adapter = AutoCompleteAdapter(this, placesClient)
        autoCompleteTextView!!.setAdapter(adapter)
    }

    private val autocompleteClickListener =
        OnItemClickListener { adapterView, view, i, l ->
            try {
                val item = adapter!!.getItem(i)
                var placeID: String? = null
                if (item != null) {
                    placeID = item.placeId
                }

                //                To specify which data types to return, pass an array of Place.Fields in your FetchPlaceRequest
                //                Use only those fields which are required.
                val placeFields = Arrays.asList(
                    Place.Field.ID,
                    Place.Field.NAME,
                    Place.Field.ADDRESS,
                    Place.Field.LAT_LNG
                )
                var request: FetchPlaceRequest? = null
                if (placeID != null) {
                    request = FetchPlaceRequest.builder(placeID, placeFields)
                        .build()
                }
                if (request != null) {
                    placesClient!!.fetchPlace(request).addOnSuccessListener { task ->
                        responseView!!.text = """
                            ${task.place.name}
                            ${task.place.address}
                            """.trimIndent()
                    }.addOnFailureListener { e ->
                        e.printStackTrace()
                        responseView!!.text = e.message
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }




}