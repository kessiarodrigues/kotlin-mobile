package com.example.conversor

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var selectedItem:String?=null
    private fun convertToCelsius(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5 / 9
    }

    private fun convertToFahrenheit(celsius: Double): Double {
        return celsius * 9 / 5 + 32
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        val items = arrayOf("ºC para ºF", "ºF para ºC")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val button = findViewById<Button>(R.id.button)
        val textInput = findViewById<EditText>(R.id.editTemperature)
        val selectedType = findViewById<TextView>(R.id.textView_tipo)
        val convertedText = findViewById<TextView>(R.id.textView_converted)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long){
                if(position === 0){
                    selectedType.text = "ºC"
                }else{
                    selectedType.text = "ºF"
                }
                selectedItem = items[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        button.setOnClickListener{
            val inputValue = textInput.text.toString().toDouble()

            if(selectedItem === items[0]){
                val converted = convertToFahrenheit(inputValue)
                convertedText.text = "Temperatura Convertida: " +  converted + "ºF"
            }
            if(selectedItem === items[1]){
                val converted = convertToCelsius(inputValue)
                convertedText.text = "Temperatura Convertida: "+ converted + "ºC"
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}