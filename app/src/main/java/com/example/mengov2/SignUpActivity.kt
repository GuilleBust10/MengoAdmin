package com.example.mengov2

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mengov2.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class SignUpActivity : AppCompatActivity() {
    private lateinit var userName : String
    private lateinit var nameOfRestaurant : String
    private lateinit var email : String
    private lateinit var password : String
    private lateinit var auth : FirebaseAuth
    private lateinit var database : DatabaseReference

    private val binding: ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = Firebase.auth
        database = Firebase.database.reference

        binding.createUserButton.setOnClickListener{
            //get text from edittext
            userName = binding.userName.text.toString().trim()
            nameOfRestaurant = binding.nameOfRestaurant.text.toString().trim()
            email = binding.emailOrPhone.text.toString().trim()
            password = binding.password.text.toString().trim()

            if(userName.isBlank() || nameOfRestaurant.isBlank() || email.isBlank() || password.isBlank()){
                Toast.makeText(this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show()
            }else{
                createAccount(email,password)
            }

            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        binding.AlreadyHaveAnAccount.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        val locationList: Array<String> = arrayOf("Nombre, Apellido, Correo")
        val adapter : ArrayAdapter<String> = ArrayAdapter(this, R.layout.simple_list_item_1)
        val autoCompleteTextView: AutoCompleteTextView = binding.listOfLocation
        autoCompleteTextView.setAdapter(adapter)
    }

    private fun createAccount(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
            if (task.isSuccessful){
                Toast.makeText( this, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else {
                Toast.makeText(this, "Creación fallida", Toast.LENGTH_SHORT).show()
                Log.d("Cuenta","Creación de cuenta: Fallida", task.exception)
            }
        }
    }
}