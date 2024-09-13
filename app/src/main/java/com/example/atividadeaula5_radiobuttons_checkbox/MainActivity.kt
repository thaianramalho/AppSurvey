package com.example.atividadeaula5_radiobuttons_checkbox

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNome: EditText
    private lateinit var dpDataNascimento: DatePicker
    private lateinit var etEmail: EditText
    private lateinit var etTelefone: EditText
    private lateinit var rbSatisfacao: RatingBar
    private lateinit var rbRecomendacao: RatingBar
    private lateinit var rbUtilidade: RatingBar
    private lateinit var cbRobustez: CheckBox
    private lateinit var cbAgilidade: CheckBox
    private lateinit var cbInovacao: CheckBox
    private lateinit var cbLentidao: CheckBox
    private lateinit var cbIneficiencia: CheckBox
    private lateinit var cbProdutividade: CheckBox
    private lateinit var cbBurocracia: CheckBox
    private lateinit var btnEnviar: Button
    private lateinit var layoutFormulario: LinearLayout
    private lateinit var layoutConfirmacao: LinearLayout
    private lateinit var tvRespostas: TextView
    private lateinit var btnConfirmar: Button
    private lateinit var btnVoltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializarViews()
        configurarBotaoEnviar()
        configurarBotaoConfirmar()
        configurarBotaoVoltar()
    }

    private fun inicializarViews() {
        etNome = findViewById(R.id.etNome)
        dpDataNascimento = findViewById(R.id.dpDataNascimento)
        etEmail = findViewById(R.id.etEmail)
        etTelefone = findViewById(R.id.etTelefone)
        rbSatisfacao = findViewById(R.id.rbSatisfacao)
        rbRecomendacao = findViewById(R.id.rbRecomendacao)
        rbUtilidade = findViewById(R.id.rbUtilidade)
        cbRobustez = findViewById(R.id.cbRobustez)
        cbAgilidade = findViewById(R.id.cbAgilidade)
        cbInovacao = findViewById(R.id.cbInovacao)
        cbLentidao = findViewById(R.id.cbLentidao)
        cbIneficiencia = findViewById(R.id.cbIneficiencia)
        cbProdutividade = findViewById(R.id.cbProdutividade)
        cbBurocracia = findViewById(R.id.cbBurocracia)
        btnEnviar = findViewById(R.id.btnEnviar)
        layoutFormulario = findViewById(R.id.layoutFormulario)
        layoutConfirmacao = findViewById(R.id.layoutConfirmacao)
        tvRespostas = findViewById(R.id.tvRespostas)
        btnConfirmar = findViewById(R.id.btnConfirmar)
        btnVoltar = findViewById(R.id.btnVoltar)
    }

    private fun configurarBotaoEnviar() {
        btnEnviar.setOnClickListener {
            if (validarEntradas()) {
                mostrarConfirmacao()
            }
        }
    }

    private fun configurarBotaoConfirmar() {
        btnConfirmar.setOnClickListener {
            Toast.makeText(this, "Pesquisa enviada com sucesso!", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun configurarBotaoVoltar() {
        btnVoltar.setOnClickListener {
            layoutFormulario.visibility = View.VISIBLE
            layoutConfirmacao.visibility = View.GONE
        }
    }

    private fun validarEntradas(): Boolean {
        if (etNome.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "Por favor, insira seu nome", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches()) {
            Toast.makeText(this, "Por favor, insira um e-mail válido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (etTelefone.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "Por favor, insira seu telefone", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun mostrarConfirmacao() {
        val respostas = obterRespostas()
        tvRespostas.text = respostas
        layoutFormulario.visibility = View.GONE
        layoutConfirmacao.visibility = View.VISIBLE
    }

    private fun obterRespostas(): String {
        val destaquesSeleccionados = mutableListOf<String>()
        if (cbRobustez.isChecked) destaquesSeleccionados.add("Robustez")
        if (cbAgilidade.isChecked) destaquesSeleccionados.add("Agilidade")
        if (cbInovacao.isChecked) destaquesSeleccionados.add("Inovação")
        if (cbLentidao.isChecked) destaquesSeleccionados.add("Lentidão")
        if (cbIneficiencia.isChecked) destaquesSeleccionados.add("Ineficiência")
        if (cbProdutividade.isChecked) destaquesSeleccionados.add("Produtividade")
        if (cbBurocracia.isChecked) destaquesSeleccionados.add("Burocracia")

        return """
            Nome: ${etNome.text}
            Data de Nascimento: ${dpDataNascimento.dayOfMonth}/${dpDataNascimento.month + 1}/${dpDataNascimento.year}
            E-mail: ${etEmail.text}
            Telefone: ${etTelefone.text}
            Grau de Satisfação: ${rbSatisfacao.rating.toInt()}
            Indicação do Produto: ${rbRecomendacao.rating.toInt()}
            Grau de Utilidade: ${rbUtilidade.rating.toInt()}
            Destaques do Produto: ${destaquesSeleccionados.joinToString(", ")}
        """.trimIndent()
    }
}