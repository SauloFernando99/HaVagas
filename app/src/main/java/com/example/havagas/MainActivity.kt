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

    private lateinit var etFullName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var checkBoxReceberEmail: CheckBox
    private lateinit var editTextTelefone: EditText
    private lateinit var radioButtonResidencial: RadioButton
    private lateinit var radioButtonComercial: RadioButton
    private lateinit var rgSex: RadioGroup
    private lateinit var rbMale: RadioButton
    private lateinit var rbFemale: RadioButton
    private lateinit var editTextDataNascimento: EditText
    private lateinit var editTextVagasInteresse: EditText
    private lateinit var btnClear: Button
    private lateinit var btnSave: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupAdicionarCelular()

        initializeViews()

        configureFormacao()

        setupLimpar()

        btnClear = findViewById(R.id.btnClear)
        btnClear.setOnClickListener { setupLimpar() }

        btnSave = findViewById(R.id.btnSave)
        btnSave.setOnClickListener { salvarFormulario() }

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
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnClear.setOnClickListener {
            etFullName.text.clear()
            editTextEmail.text.clear()
            checkBoxReceberEmail.isChecked = false
            editTextTelefone.text.clear()
            radioButtonResidencial.isChecked = false
            radioButtonComercial.isChecked = false
            textViewTelefoneCelular.visibility = View.GONE
            editTextTelefoneCelular.visibility = View.GONE
            editTextTelefoneCelular.text.clear()
            rgSex.clearCheck()
            rbMale.isChecked = false
            rbFemale.isChecked = false
            editTextDataNascimento.text.clear()
            spinnerFormacao.setSelection(0)
            editTextAnoConclusao.text.clear()
            editTextInstituicao.text.clear()
            editTextTituloMonografia.text.clear()
            editTextOrientador.text.clear()
            editTextVagasInteresse.text.clear()
        }
    }

    private fun salvarFormulario() {
        val nomeCompleto = etFullName.text.toString()
        val email = editTextEmail.text.toString()
        val receberEmail = checkBoxReceberEmail.isChecked
        val telefone = editTextTelefone.text.toString()
        val tipoTelefone = if (radioButtonResidencial.isChecked) "Residencial" else "Comercial"
        val telefoneCelular = editTextTelefoneCelular.text.toString()
        val sexo = if (rbMale.isChecked) "Masculino" else "Feminino"
        val dataNascimento = editTextDataNascimento.text.toString()
        val formacao = spinnerFormacao.selectedItem.toString()
        val anoConclusao = editTextAnoConclusao.text.toString()
        val instituicao = editTextInstituicao.text.toString()
        val tituloMonografia = editTextTituloMonografia.text.toString()
        val orientador = editTextOrientador.text.toString()
        val vagasInteresse = editTextVagasInteresse.text.toString()

        val formulario = Formulario(
            nomeCompleto = nomeCompleto,
            email = email,
            receberEmail = receberEmail,
            telefone = telefone,
            tipoTelefone = tipoTelefone,
            telefoneCelular = telefoneCelular,
            sexo = sexo,
            dataNascimento = dataNascimento,
            formacao = formacao,
            anoConclusao = anoConclusao,
            instituicao = instituicao,
            tituloMonografia = tituloMonografia,
            orientador = orientador,
            vagasInteresse = vagasInteresse
        )

        exibirToast(formulario.toString())
    }

    private fun exibirToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

    }
}