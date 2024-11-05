package com.example.mengov2

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mengov2.adapter.AllitemAdapter
import com.example.mengov2.databinding.ActivityAllitemBinding
import java.util.ArrayList

class Allitem : AppCompatActivity() {
    private val binding : ActivityAllitemBinding by lazy {
        ActivityAllitemBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        val menuFoodName = listOf("Hambruguesa", "Sandwich", "momo", "item", "sandwich", "momo")
        val menuItemPrice = listOf("Q5", "Q6", "Q8", "Q9", "Q10", "Q10")
        val menuImage = listOf(
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu3,
            R.drawable.menu4,
            R.drawable.menu5,
            R.drawable.menu6
        )
        val adapter = AllitemAdapter(ArrayList(menuFoodName), ArrayList(menuItemPrice), ArrayList(menuImage))
        binding.MenuRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.MenuRecyclerView.adapter = adapter

        binding.returnButton.setOnClickListener {
            val intent = Intent(this,Dashboard::class.java)
            startActivity(intent)
        }
    }
}