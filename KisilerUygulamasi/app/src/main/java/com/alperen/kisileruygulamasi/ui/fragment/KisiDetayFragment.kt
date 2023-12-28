package com.alperen.kisileruygulamasi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.alperen.kisileruygulamasi.R
import com.alperen.kisileruygulamasi.databinding.FragmentKisiDetayBinding


class KisiDetayFragment : Fragment() {
private lateinit var binding: FragmentKisiDetayBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKisiDetayBinding.inflate(inflater,container, false)

        binding.toolbarKisiDetay.title="Kişi Detay"

        val bundle: KisiDetayFragmentArgs by navArgs()
        val gelenKisi=bundle.kisi

        binding.editTextKisiad.setText((gelenKisi.kisi_ad))
        binding.editTextKisiTel.setText ((gelenKisi.kisi_tel))

        binding.buttonGNcelle2.setOnClickListener {
            val kisi_ad = binding.editTextKisiad.text.toString()
            val kisi_tel = binding.editTextKisiTel.text.toString()

            güncelle(gelenKisi.kisi_id,kisi_ad,kisi_tel)


        }


        return binding.root
    }

    fun güncelle(kisi_id:Int, kisi_ad:String, kisi_tel :String)
    {
        Log.e("Kişi Kaydet","$kisi_ad - $kisi_id - $kisi_tel")
    }

}