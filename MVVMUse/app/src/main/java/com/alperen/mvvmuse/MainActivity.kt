package com.alperen.mvvmuse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.alperen.mvvmuse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivityNesne = this


        viewModel.sonuc.observe(this){
            binding.hesaplamaSonucu= it
        }

     }

    fun buttonToplamTikla(alinanSayi1: String, alinanSayi2: String) {
        viewModel.toplamaYap(alinanSayi1, alinanSayi2)
     }

    fun buttonCarpmaTikla(alinanSayi1: String, alinanSayi2: String) {
        viewModel.carpmaYap(alinanSayi1, alinanSayi2)
     }
}