package com.example.mengov2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mengov2.databinding.ActivityAnadirItemBinding
import com.example.mengov2.databinding.ActivityMainBinding

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

    }
    val pickimage =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()){ uri ->
        if (uri != null)
        {
            binding.selectedimage.setImageURI(uri)
        }
    }
}