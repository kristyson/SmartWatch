package com.example.smartwatch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private var db: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialize o Firestore
        db = FirebaseFirestore.getInstance()

        // Adicione um novo documento com um ID gerado automaticamente.
        val data = hashMapOf(
            "name" to "John Doe",
            "email" to "johndoe@example.com"
        )

        db?.collection("users")
            ?.add(data)
            ?.addOnSuccessListener(OnSuccessListener<DocumentReference> { documentReference ->

                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.id)
            })
            ?.addOnFailureListener(OnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            })
    }
}
