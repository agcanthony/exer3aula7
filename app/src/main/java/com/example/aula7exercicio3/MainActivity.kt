package com.example.aula7exercicio3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val telefone = findViewById<ExtendedFloatingActionButton>(R.id.telefone)
        val navegador = findViewById<ExtendedFloatingActionButton>(R.id.navegador)
        val mensagem = findViewById<ExtendedFloatingActionButton>(R.id.mensagem)
        val email = findViewById<ExtendedFloatingActionButton>(R.id.email)

        telefone.setOnClickListener {
            showRedirectingMessageAndRedirect("tel:" + "9999999999")
        }

        navegador.setOnClickListener {
            showRedirectingMessageAndRedirect("http://google.com.br")
        }

        mensagem.setOnClickListener {
            showRedirectingMessageAndRedirect("sms:" + "9999999999")
        }

        email.setOnClickListener {
            showRedirectingMessageAndRedirect("mailto:")
        }
    }

    private fun showRedirectingMessageAndRedirect(action: String) {
        showToast("Redirecionando...")

        handler.postDelayed({
            performAction(action)
        }, 3000)
    }

    private fun performAction(action: String) {
        val intent = when {
            action.startsWith("tel:") -> Intent(Intent.ACTION_DIAL, Uri.parse(action))
            action.startsWith("http") -> Intent(Intent.ACTION_VIEW, Uri.parse(action))
            action.startsWith("sms:") -> Intent(Intent.ACTION_VIEW, Uri.parse(action)).apply {
                putExtra("sms_body", "Pós-Graduação!")
            }
            action.startsWith("mailto:") -> Intent(Intent.ACTION_SENDTO, Uri.parse(action))
            else -> null
        }

        intent?.let {
            startActivity(it)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
