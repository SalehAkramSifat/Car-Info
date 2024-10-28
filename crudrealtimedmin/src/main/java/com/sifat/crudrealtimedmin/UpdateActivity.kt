package com.sifat.crudrealtimedmin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sifat.crudrealtimedmin.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateBinding
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.update.setOnClickListener {
            val NumberUpdate = binding.vehicleNumberUpdate.text.toString()
            val ownerUpdate = binding.vehicleownerUpdate.text.toString()
            val brandUpdate = binding.vehicleBrandUpdate.text.toString()
            val rtoUpdate = binding.vehicleRtoUpdate.text.toString()
            
            updateData(NumberUpdate, ownerUpdate, brandUpdate, rtoUpdate)
        }
    }

    private fun updateData(vehicleNumber: String, ownerName: String, vehicleBrand: String, vehicleRto: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
        val vehicaleData = VehicaleData(ownerName, vehicleBrand, vehicleRto, vehicleNumber)
        databaseReference.child(vehicleNumber).setValue(vehicaleData)  // Using .setValue() to replace existing data
            .addOnSuccessListener {
                binding.vehicleNumberUpdate.text.clear()
                binding.vehicleownerUpdate.text.clear()
                binding.vehicleBrandUpdate.text.clear()
                binding.vehicleRtoUpdate.text.clear()
                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
            Toast.makeText(this, "Unable to update", Toast.LENGTH_SHORT).show()
        }
    }
}