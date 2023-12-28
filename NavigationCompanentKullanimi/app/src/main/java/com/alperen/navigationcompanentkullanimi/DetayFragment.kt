package com.alperen.navigationcompanentkullanimi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.arch.core.executor.DefaultTaskExecutor
import androidx.navigation.fragment.navArgs
import com.alperen.navigationcompanentkullanimi.databinding.FragmentDetayBinding
import com.google.android.material.snackbar.Snackbar

class DetayFragment : Fragment() {
    private lateinit var binding: FragmentDetayBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetayBinding.inflate(inflater, container, false)

        val bundle: DetayFragmentArgs by navArgs()
        val gelenBoy = bundle.boy
        val gelenbekar = bundle.bekar
        val urunId = bundle.urun.id
        val urunAd = bundle.urun.ad


        binding.textViewbLg.text = "$urunAd,$urunId, $gelenBoy, $gelenbekar"


        // true ise back tusunun görevi inaktif olur
        // false olursa back tusu normal görevini yerine getirir
        val backPress = object : OnBackPressedCallback(true) {
            // back tusuna basıldıgı zaman yapılmak istenen işlemler buraya yazılır
            override fun handleOnBackPressed() {
                // Callback true iken
                Snackbar.make(
                    binding.textViewbLg,
                    " geri dönmek istiyor musunuz? ", Snackbar.LENGTH_SHORT)
                    .setAction("EVET") {
                        isEnabled = false // geri dönüş aktif edildi.
                        requireActivity().onBackPressedDispatcher.onBackPressed()
                    }.show()
            }

        }
        // bu yapı yaşam döngüsü ile snkronize çalışmasını sağlıyor
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPress)
        return binding.root
    }
}