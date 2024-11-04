package com.example.mengov2

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mengov2.databinding.ActivityDashboardBinding
import com.example.mengov2.databinding.ActivityMainBinding

class Dashboard : AppCompatActivity() {
    private val binding: ActivityDashboardBinding by lazy {
        ActivityDashboardBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.addmenu.setOnClickListener {
            val intent = Intent(this,AnadirItem::class.java)
            startActivity(intent)
        }
    }
}