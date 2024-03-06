package com.example.havagas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

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

        setupLimpar()

        setupSalvar()

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

    private fun setupLimpar() {
        val btnClear = findViewById<Button>(R.id.btnClear)

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            findViewById<EditText>(R.id.etFullName).text.clear()
            findViewById<EditText>(R.id.editTextEmail).text.clear()
            findViewById<CheckBox>(R.id.checkBoxReceberEmail).isChecked = false
            findViewById<EditText>(R.id.editTextTelefone).text.clear()
            findViewById<RadioGroup>(R.id.radioGroupTipoTelefone).clearCheck()
            findViewById<EditText>(R.id.editTextTelefoneCelular).text.clear()
            findViewById<TextView>(R.id.textViewTelefoneCelular).visibility = View.GONE
            findViewById<EditText>(R.id.editTextTelefoneCelular).visibility = View.GONE
            findViewById<RadioGroup>(R.id.rgSex).clearCheck()
            findViewById<EditText>(R.id.editTextDataNascimento).text.clear()
            findViewById<Spinner>(R.id.spinnerFormacao).setSelection(0)
            findViewById<EditText>(R.id.editTextAnoConclusao).text.clear()
            findViewById<EditText>(R.id.editTextInstituicao).text.clear()
            findViewById<EditText>(R.id.editTextTituloMonografia).text.clear()
            findViewById<EditText>(R.id.editTextOrientador).text.clear()
            findViewById<EditText>(R.id.editTextVagasInteresse).text.clear()
        }
    }

    private fun setupSalvar() {
        val btnSave = findViewById<Button>(R.id.btnSave)

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            val nomeCompleto = findViewById<EditText>(R.id.etFullName).text.toString()
            val email = findViewById<EditText>(R.id.editTextEmail).text.toString()
            val receberEmail = findViewById<CheckBox>(R.id.checkBoxReceberEmail).isChecked
            val telefone = findViewById<EditText>(R.id.editTextTelefone).text.toString()
            val tipoTelefone = if (findViewById<RadioButton>(R.id.radioButtonResidencial).isChecked) "Residencial" else "Comercial"
            val telefoneCelular = findViewById<EditText>(R.id.editTextTelefoneCelular).text.toString()
            val sexo = if (findViewById<RadioButton>(R.id.rbMale).isChecked) "Masculino" else "Feminino"
            val dataNascimento = findViewById<EditText>(R.id.editTextDataNascimento).text.toString()
            val formacao = findViewById<Spinner>(R.id.spinnerFormacao).selectedItem.toString()
            val anoConclusao = findViewById<EditText>(R.id.editTextAnoConclusao).text.toString()
            val instituicao = findViewById<EditText>(R.id.editTextInstituicao).text.toString()
            val tituloMonografia = findViewById<EditText>(R.id.editTextTituloMonografia).text.toString()
            val orientador = findViewById<EditText>(R.id.editTextOrientador).text.toString()
            val vagasInteresse = findViewById<EditText>(R.id.editTextVagasInteresse).text.toString()

            val formulario = Formulario(
                nomeCompleto,
                email,
                receberEmail,
                telefone,
                tipoTelefone,
                telefoneCelular,
                sexo,
                dataNascimento,
                formacao,
                anoConclusao,
                instituicao,
                tituloMonografia,
                orientador,
                vagasInteresse
            )

            exibirToast(formulario.toString())
        }
    }

    private fun exibirToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

    }
}