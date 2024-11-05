package com.example.mengov2

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mengov2.databinding.ActivityLoginBinding
import com.example.mengov2.databinding.ActivitySingUpBinding
import com.example.mengov2.model.UserModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var email :String
    private lateinit var password :String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference


    private val binding: ActivityLoginBinding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = Firebase.auth

        database = Firebase.database.reference

        binding.loginButton.setOnClickListener{
            email = binding.email.text.toString().trim()
            password = binding.Password.text.toString().trim()

            if(email.isBlank() || password.isBlank()){
                Toast.makeText(this,"Por favor ingrese todos los datos", Toast.LENGTH_SHORT).show()

            }else{
                createUserAccount(email,password)
                Toast.makeText(this,"Ingresado Feliz", Toast.LENGTH_SHORT).show()

            }

            val intent = Intent(this,SingUpActivity::class.java)
            startActivity(intent)
        }
        binding.dontHaveAccount.setOnClickListener {
            val intent = Intent(this,SingUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createUserAccount(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                val user = auth.currentUser
                updateUi(user)
            }else{
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        val user = auth.currentUser
                        saveUserData()
                        updateUi(user)
                    }else
                    {
                        Toast.makeText(this,"ENTRADA FALLIDA",Toast.LENGTH_SHORT).show()
                        Log.d("Cuenta","CREACION DE USUARIO", task.exception)
                    }
                }

            }
        }
    }

    private fun saveUserData() {
        email = binding.email.text.toString().trim()
        password = binding.Password.text.toString().trim()

        val user= UserModel(email,password)
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        userId.let {
            if(it != null){
                database.child("usuario").child(it).setValue(user)
            }
        }
    }

    private fun updateUi(user: FirebaseUser?) {
        startActivity(Intent(this,Dashboard::class.java))
    }

}