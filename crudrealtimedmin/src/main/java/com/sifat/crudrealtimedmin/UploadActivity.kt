package com.sifat.crudrealtimedmin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sifat.crudrealtimedmin.databinding.ActivityMainBinding
import com.sifat.crudrealtimedmin.databinding.ActivityUploadBinding

class UploadActivity : AppCompatActivity() {
    lateinit var binding: ActivityUploadBinding
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.upload.setOnClickListener {
            val vehicleOwner = binding.vehicleOwner.text.toString()
            val vehicleBrand = binding.vehicleBrand.text.toString()
            val vehicleRTO = binding.vehicleRto.text.toString()
            val vehicleNumber = binding.vehicleNumber.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")

            val VehicaleData = VehicaleData(vehicleOwner, vehicleBrand, vehicleRTO, vehicleNumber)
            databaseReference.child(vehicleNumber).setValue(VehicaleData).addOnSuccessListener {
                binding.vehicleOwner.text.clear()
                binding.vehicleBrand.text.clear()
                binding.vehicleRto.text.clear()
                binding.vehicleNumber.text.clear()

                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }

        }

    }
}