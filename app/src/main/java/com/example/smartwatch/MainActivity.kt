package com.example.smartwatch

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private var db: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize o Firestore
        db = FirebaseFirestore.getInstance()

        val sendButton: Button = findViewById(R.id.btn_send)
        sendButton.setOnClickListener {
            sendDataToFirestore()
        }
    }

    private fun sendDataToFirestore() {
        // Obtenha a data e hora atual
        val currentDateTime = Calendar.getInstance().time
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(currentDateTime)
        val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(currentDateTime)
        val currentDocumentId = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(currentDateTime)

        // Crie os dados a serem enviados sem os campos Date e Time
        val data = hashMapOf(
            "CPF" to "080.199.323.12",
            "Name" to "Samsung Smartwatch"
        )

        // Adicione um novo documento com a hora atual como ID
        db?.collection(currentDate)
            ?.document(currentDocumentId)
            ?.set(data)
            ?.addOnSuccessListener {
                // Se o envio for bem-sucedido, mostre uma mensagem de sucesso
                showMessage("Mensagem enviada com sucesso!")
            }
            ?.addOnFailureListener { e ->
                // Se ocorrer uma falha, mostre uma mensagem de erro
                showMessage("Falha ao enviar mensagem: ${e.message}")
            }
    }

    private fun showMessage(message: String) {
        // Mostra uma mensagem usando o Toast
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
