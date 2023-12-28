package com.alperen.calismayapisi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alperen.calismayapisi.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{
            //Snackbar.make(binding.button,"merhaba",Snackbar.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, DetayActivity::class.java)
            intent.putExtra("ad","ahmet")
            intent.putExtra("yaş",22)
            intent.putExtra("boy",1.78)
            intent.putExtra("bekar",true)
            startActivity(intent)


        }
    }
}