package com.alperen.kullanicietkilesimi

import android.app.ActivityManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alperen.kullanicietkilesimi.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonToast.setOnClickListener {
            Toast.makeText(this@MainActivity, "merhaba", Toast.LENGTH_SHORT).show()
        }

        binding.buttonSnackbar.setOnClickListener {
            Snackbar.make(it, "Silmek istiyor musunuz ?", Snackbar.LENGTH_SHORT)
                .setAction("EVET")
                {
                    Snackbar.make(it, " silindi", Snackbar.LENGTH_SHORT).show()

                }
                .setBackgroundTint(Color.WHITE)
                .setTextColor(Color.BLUE)
                .setActionTextColor(Color.BLUE)
                .show()

        }

        binding.buttonDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this@MainActivity)
                .setTitle("Başlık ")
                .setMessage("Mesaj")
                .setPositiveButton("Tamam")
                { d, i ->
                    Toast.makeText(this@MainActivity, "Tamam, seçildi", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("İptal")
                { d,i ->

                    Toast.makeText(this@MainActivity, "İptal, seçildi", Toast.LENGTH_SHORT).show()
                }.show()
        }
    }
}