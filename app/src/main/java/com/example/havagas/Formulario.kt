package com.example.havagas

class Formulario(
    var nomeCompleto: String = "",
    var email: String = "",
    var receberEmail: Boolean = false,
    var telefone: String = "",
    var tipoTelefone: String = "",
    var telefoneCelular: String = "",
    var sexo: String = "",
    var dataNascimento: String = "",
    var formacao: String = "",
    var anoConclusao: String = "",
    var instituicao: String = "",
    var tituloMonografia: String = "",
    var orientador: String = "",
    var vagasInteresse: String = ""
) {
    override fun toString(): String {
        return """
            Nome Completo: $nomeCompleto
            E-mail: $email
            Receber E-mails: $receberEmail
            Telefone: $telefone ($tipoTelefone)
            Telefone Celular: $telefoneCelular
            Sexo: $sexo
            Data de Nascimento: $dataNascimento
            Formação: $formacao
            Ano de Conclusão: $anoConclusao
            Instituição: $instituicao
            Título de Monografia: $tituloMonografia
            Orientador: $orientador
            Vagas de Interesse: $vagasInteresse
        """.trimIndent()
    }
}