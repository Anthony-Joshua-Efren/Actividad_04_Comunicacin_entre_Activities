package com.example.actividad_04_comunicacin_entre_activities

import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencias a los elementos de la interfaz de usuario

        val editTextNombre = findViewById<EditText>(R.id.editTextNombre)
        val editTextEdad = findViewById<EditText>(R.id.editTextEdad)
        val buttonEnviar = findViewById<Button>(R.id.buttonEnviar)
        val formularioLayout = findViewById<LinearLayout>(R.id.formularioLayout)
        val pantallaPrincipalLayout = findViewById<LinearLayout>(R.id.pantallaPrincipalLayout)
        val textViewNombre = findViewById<TextView>(R.id.textViewNombre)
        val textViewEdad = findViewById<TextView>(R.id.textViewEdad)
        val buttonVolver = findViewById<Button>(R.id.buttonVolver)

        // Acción al presionar el botón "Enviar"

        buttonEnviar.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            val edad = editTextEdad.text.toString()

            // Válidar que los campos no estén vacíos

            if (nombre.isEmpty() || edad.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa tu nombre y edad.", Toast.LENGTH_SHORT).show()
            } else {

                // Mostrar los datos ingresados en la pantalla principal

                textViewNombre.text = "Nombre: $nombre"
                textViewEdad.text = "Edad: $edad"

                // Aplicar animación de transición

                val fadeOut = AlphaAnimation(1.0f, 0.0f)
                fadeOut.duration = 500
                formularioLayout.startAnimation(fadeOut)

                // Hacer visible la pantalla principal y ocultar el formulario

                formularioLayout.visibility = View.GONE
                pantallaPrincipalLayout.visibility = View.VISIBLE

                val fadeIn = AlphaAnimation(0.0f, 1.0f)
                fadeIn.duration = 500
                pantallaPrincipalLayout.startAnimation(fadeIn)
            }
        }

        // Acción al presionar el botón "Volver" para regresar al formulario

        buttonVolver.setOnClickListener {

            // Aplicar animación de transición

            val fadeOut = AlphaAnimation(1.0f, 0.0f)
            fadeOut.duration = 500
            pantallaPrincipalLayout.startAnimation(fadeOut)

            // Hacer visible el formulario y ocultar la pantalla principal

            pantallaPrincipalLayout.visibility = View.GONE
            formularioLayout.visibility = View.VISIBLE

            val fadeIn = AlphaAnimation(0.0f, 1.0f)
            fadeIn.duration = 500
            formularioLayout.startAnimation(fadeIn)
        }

    }
}