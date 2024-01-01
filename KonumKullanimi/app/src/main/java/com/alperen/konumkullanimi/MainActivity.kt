package com.alperen.konumkullanimi

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.alperen.konumkullanimi.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var izinKontrol = 0
    private lateinit var flpc: FusedLocationProviderClient
    private lateinit var locationTask: Task<Location>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        flpc = LocationServices.getFusedLocationProviderClient(this)

        binding.buttonKonumAl.setOnClickListener {
            // Manifestteki FINE izninin kontrol edilen kod satırı
            izinKontrol =
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)

            if (izinKontrol == PackageManager.PERMISSION_GRANTED) {// izin onaylanmışsa
                locationTask= flpc.lastLocation
                konumBilgisiAl()

            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100
                )
            }

        }
    }

    fun konumBilgisiAl()
    {
        locationTask.addOnSuccessListener {
            if(it != null)
            {
                binding.textViewEnlem.text= "Enlem : ${it.longitude}"
                binding.textViewBoylam.text= "Boylam : ${it.latitude}"
            }else{
                binding.textViewBoylam.text= "Enlem : Bulunamadı "
                binding.textViewEnlem.text= "Boylam : Bulunamadı"
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(applicationContext, "izin onaylandı gardaş", Toast.LENGTH_SHORT)
                    .show()
        } else {

            Toast.makeText(applicationContext, "izin onaylanmadı gardaş", Toast.LENGTH_SHORT).show()
        }
    }
}