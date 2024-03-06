package com.example.havagas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var buttonAdicionarCelular: Button
    private lateinit var textViewTelefoneCelular: TextView
    private lateinit var editTextTelefoneCelular: EditText

    private lateinit var spinnerFormacao: Spinner
    private lateinit var textViewAnoConclusao: TextView
    private lateinit var editTextAnoConclusao: EditText
    private lateinit var textViewInstituicao: TextView
    private lateinit var editTextInstituicao: EditText
    private lateinit var textViewTituloMonografia: TextView
    private lateinit var editTextTituloMonografia: EditText
    private lateinit var textViewOrientador: TextView
    private lateinit var editTextOrientador: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupAdicionarCelular()

        initializeViews()

        configureFormacao()

    }

    private fun setupAdicionarCelular() {
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

    private fun initializeViews() {
        spinnerFormacao = findViewById(R.id.spinnerFormacao)
        textViewAnoConclusao = findViewById(R.id.textViewAnoConclusao)
        editTextAnoConclusao = findViewById(R.id.editTextAnoConclusao)
        textViewInstituicao = findViewById(R.id.textViewInstituicao)
        editTextInstituicao = findViewById(R.id.editTextInstituicao)
        textViewTituloMonografia = findViewById(R.id.textViewTituloMonografia)
        editTextTituloMonografia = findViewById(R.id.editTextTituloMonografia)
        textViewOrientador = findViewById(R.id.textViewOrientador)
        editTextOrientador = findViewById(R.id.editTextOrientador)
    }

    private fun configureFormacao() {
        val formacoes = resources.getStringArray(R.array.formacoes)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, formacoes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFormacao.adapter = adapter

        spinnerFormacao.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val formacaoSelecionada = formacoes[position]

                when (formacaoSelecionada) {
                    "Fundamental", "Médio" -> showFields(true, false, false)
                    "Graduação", "Especialização" -> showFields(true, true, false)
                    "Mestrado", "Doutorado" -> showFields(true, true, true)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { }
        }
    }

    private fun showFields(showAnoConclusao: Boolean, showInstituicao: Boolean, showTituloMonografia: Boolean) {
        textViewAnoConclusao.visibility = if (showAnoConclusao) View.VISIBLE else View.GONE
        editTextAnoConclusao.visibility = if (showAnoConclusao) View.VISIBLE else View.GONE
        textViewInstituicao.visibility = if (showInstituicao) View.VISIBLE else View.GONE
        editTextInstituicao.visibility = if (showInstituicao) View.VISIBLE else View.GONE
        textViewTituloMonografia.visibility = if (showTituloMonografia) View.VISIBLE else View.GONE
        editTextTituloMonografia.visibility = if (showTituloMonografia) View.VISIBLE else View.GONE
        textViewOrientador.visibility = if (showTituloMonografia) View.VISIBLE else View.GONE
        editTextOrientador.visibility = if (showTituloMonografia) View.VISIBLE else View.GONE
    }
}