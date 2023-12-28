package com.alperen.navigationcompanentkullanimi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.alperen.navigationcompanentkullanimi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nacHostFragment1= supportFragmentManager.findFragmentById(R.id.navHostFragment1) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavigationView,nacHostFragment1.navController)

        // binding.bottomNavigationView ile anasayfanın altındaki bottom navigation viewine ulaştık
        // geri kalan kısım ise birleştirme kısmı

    }
}