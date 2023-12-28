package com.alperen.calismayapisi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alperen.calismayapisi.databinding.ActivityDetayBinding

class DetayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetayBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val gelenAd= intent.getStringExtra("ad")
        val gelenYas= intent.getIntExtra("yaş",0)
        val gelenBoy= intent.getDoubleExtra("boy",0.0)
        val gelenBekar = intent.getBooleanExtra("bekar",false)

        binding.textView.text= "${gelenAd}, ${gelenBoy}, ${gelenYas}, ${gelenBekar}"



    }
}