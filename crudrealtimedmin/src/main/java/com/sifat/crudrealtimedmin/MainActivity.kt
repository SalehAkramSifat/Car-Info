package com.sifat.crudrealtimedmin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sifat.crudrealtimedmin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.upload.setOnClickListener {
            startActivity(Intent(this, UploadActivity::class.java))
            finish()
        }
        binding.update.setOnClickListener {
            startActivity(Intent(this, UpdateActivity::class.java))
            finish()
        }
        binding.delete.setOnClickListener {
            startActivity(Intent(this, DeleteActivity::class.java))
            finish()
        }



    }
}