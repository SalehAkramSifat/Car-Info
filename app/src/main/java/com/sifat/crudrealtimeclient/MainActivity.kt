package com.sifat.crudrealtimeclient

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sifat.crudrealtimeclient.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.search.setOnClickListener {
            val searchVehicleNumber: String = binding.edSearch.text.toString()
            if (searchVehicleNumber.isNotEmpty()) {
                readData(searchVehicleNumber)
            } else {
                Toast.makeText(this, "Please Enter the Vehicle number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(vehicleNumber: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
        databaseReference.child(vehicleNumber).get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val ownerName = snapshot.child("vehicleOwner").value
                val vehicleBrand = snapshot.child("vehicleBrand").value
                val vehicleRTO = snapshot.child("vehicleRto").value

                // Set the values to the corresponding TextViews
                binding.TVownerName.text = ownerName?.toString() ?: "Owner name not found"
                binding.TVvehicleBrand.text = vehicleBrand?.toString() ?: "Brand not found"
                binding.TVvehicleRto.text = vehicleRTO?.toString() ?: "RTO not found"

                Toast.makeText(this, "Result Found", Toast.LENGTH_SHORT).show()
                binding.edSearch.text.clear()
            } else {
                Toast.makeText(this, "Vehicle Number does not Exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Something went Wrong", Toast.LENGTH_SHORT).show()
        }
    }
}