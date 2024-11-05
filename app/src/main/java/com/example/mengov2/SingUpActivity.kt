package com.example.mengov2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mengov2.databinding.ActivitySingUpBinding
import com.example.mengov2.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SingUpActivity : AppCompatActivity() {
    private lateinit var userName : String
    private lateinit var nameOfRestaurant : String
    private lateinit var email : String
    private lateinit var password : String
    private lateinit var auth : FirebaseAuth
    private lateinit var database : DatabaseReference

    private val binding: ActivitySingUpBinding by lazy{
        ActivitySingUpBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        auth = Firebase.auth
        database = Firebase.database.reference

        binding.createButton.setOnClickListener{
            userName = binding.name.text.toString().trim()
            nameOfRestaurant = binding.restorentName.text.toString().trim()
            email = binding.emailOrPhone.text.toString().trim()
            password = binding.password.text.toString().trim()

            if(userName.isBlank() || nameOfRestaurant.isBlank() || email.isBlank() || password.isBlank()){
                Toast.makeText(this,"LLenar campos", Toast.LENGTH_SHORT).show()
            }else{
                createAccount(email,password)
            }



        }


    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
            if (task.isSuccessful){
                Toast.makeText( this, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show()
                saveUserData()
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

    private fun saveUserData() {
        userName = binding.name.text.toString().trim()
        nameOfRestaurant = binding.restorentName.text.toString().trim()
        email = binding.emailOrPhone.text.toString().trim()
        password = binding.password.text.toString().trim()

        val user = UserModel(userName,nameOfRestaurant,email,password)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        // Guarda la base de datos
        database.child("user").child(userId).setValue(user)
    }


}