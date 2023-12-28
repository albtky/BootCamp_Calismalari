package com.alperen.widgetskullanimi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TimeFormatException
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.alperen.widgetskullanimi.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var kontrol = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonoku.setOnClickListener {
            val alınanVeri = binding.editTextGirdi.text.toString()
            binding.textViewSonuc.text = alınanVeri
        }

        binding.buttonResim1.setOnClickListener {
            binding.imageView.setImageResource(R.drawable.funny_resim)
        }
        binding.buttonResim1.setOnClickListener {
            // resimin ismiyle beraber çağırma işlemi

            binding.imageView.setImageResource(
                resources.getIdentifier(
                    "funny_resim",
                    "drawable",
                    packageName
                )
            )
        }

        // expose dropdown menü işlemleri

        val ulkeler = ArrayList<String>()
        ulkeler.add("türkiye")
        ulkeler.add("japonya")
        ulkeler.add("amerika")

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ulkeler)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)


        binding.switch1.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked)
                Log.e("Widgets", "Switch : ON ")
            else
                Log.e("Widgets", "Switch : OF ")
        }

        binding.buttonGoster.setOnClickListener {
            Log.e("Sonuc", "Switch Durumu : ${binding.switch1.isChecked}")
            if (kontrol) {
                val secilenButton = findViewById<Button>(binding.toggleButton.checkedButtonId)
                val buttonYazi = secilenButton.text.toString()
                Log.e("Sonuc : ", "Toggle Durum : $buttonYazi")
            }
            val ulke = binding.autoCompleteTextView.text.toString()
            Log.e("Sonuc : ", "Ülke : $ulke")
            binding.slider.progress.toString()
            Log.e("Sonuc : ", "Slider durum : ${binding.slider.progress}")

        }


        binding.toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
            kontrol = isChecked
            if (kontrol) {
                val secilenButton = findViewById<Button>(binding.toggleButton.checkedButtonId)
                val buttonYazi = secilenButton.text.toString()
                Log.e("Sonuc : ", buttonYazi)
            }
        }

        binding.buttonBasla.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
        }
        binding.buttonDur.setOnClickListener {
            binding.progressBar.visibility = View.INVISIBLE
        }

        binding.textViewSlider.text = binding.slider.progress.toString()
        binding.slider.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.textViewSlider.text = p1.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(sp1eekBar: SeekBar?) {
            }

        })


        binding.buttonSaat.setOnClickListener {
            val tp = MaterialTimePicker.Builder()
                .setTitleText("Saat seç")
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()
            tp.show(supportFragmentManager, "saat")
            tp.addOnPositiveButtonClickListener {
                binding.editTextSaat.setText("${tp.hour} : ${tp.minute}")
            }
        }
        binding.buttonTarih.setOnClickListener {
            val dp = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Tarih seç")
                .build()

            dp.show(supportFragmentManager, "Tarih")
            dp.addOnPositiveButtonClickListener {
            val df= SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val tarih = df.format(it)
                binding.editTextTarih.setText(tarih)
            }
        }
    }
}