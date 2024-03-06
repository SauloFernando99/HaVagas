package com.example.havagas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var buttonAdicionarCelular: Button
    private lateinit var textViewTelefoneCelular: TextView
    private lateinit var editTextTelefoneCelular: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonAdicionarCelular = findViewById(R.id.buttonAdicionarCelular)
        textViewTelefoneCelular = findViewById(R.id.textViewTelefoneCelular)
        editTextTelefoneCelular = findViewById(R.id.editTextTelefoneCelular)

        buttonAdicionarCelular.setOnClickListener {
            if (textViewTelefoneCelular.visibility == View.GONE) {
                textViewTelefoneCelular.visibility = View.VISIBLE
                editTextTelefoneCelular.visibility = View.VISIBLE
            } else {
                textViewTelefoneCelular.visibility = View.GONE
                editTextTelefoneCelular.visibility = View.GONE
            }
        }
    }
}