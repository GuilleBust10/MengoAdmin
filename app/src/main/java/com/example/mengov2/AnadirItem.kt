package com.example.mengov2

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mengov2.databinding.ActivityAnadirItemBinding


class AnadirItem : AppCompatActivity() {
    private val binding: ActivityAnadirItemBinding by lazy {
        ActivityAnadirItemBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_anadir_item)
        binding.selectimage.setOnClickListener{
            pickimage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        binding.backButton.setOnClickListener {
            val intent = Intent(this,Dashboard::class.java)
            startActivity(intent)
        }

    }
    val pickimage =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()){ uri ->
        if (uri != null)
        {
            binding.selectedimage.setImageURI(uri)
        }
    }
}