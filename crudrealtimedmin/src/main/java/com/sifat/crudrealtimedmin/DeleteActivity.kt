package com.sifat.crudrealtimedmin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sifat.crudrealtimedmin.databinding.ActivityDeleteBinding

class DeleteActivity : AppCompatActivity() {
    lateinit var binding: ActivityDeleteBinding
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.search.setOnClickListener {
            val vehicaleNumber = binding.deleteVehicleNumber.text.toString()
            if (vehicaleNumber.isNotEmpty()){
                deleteData(vehicaleNumber)
            }else{
                Toast.makeText(this,"Please Enter vehicale number ", Toast.LENGTH_SHORT).show()
            }

        }

    }
    private fun deleteData(vehicaleNumber: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
        databaseReference.child(vehicaleNumber).removeValue().addOnSuccessListener {
            binding.deleteVehicleNumber.text.clear()
            Toast.makeText(this,"Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this,"Unable to deleted", Toast.LENGTH_SHORT).show()
        }

    }
}