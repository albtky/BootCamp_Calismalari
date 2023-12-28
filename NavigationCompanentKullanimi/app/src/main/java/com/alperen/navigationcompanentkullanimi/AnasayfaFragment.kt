package com.alperen.navigationcompanentkullanimi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.alperen.navigationcompanentkullanimi.databinding.FragmentAnasayfaBinding
import com.google.android.material.snackbar.Snackbar


class AnasayfaFragment : Fragment() {
private lateinit var binding: FragmentAnasayfaBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAnasayfaBinding.inflate(inflater,container,false )

        binding.button.setOnClickListener{
//            Snackbar.make(it,"merhaba",Snackbar.LENGTH_SHORT).show()
//            binding.textView.text="naber"


            val urun = Urunler(100,"TV")
                val gecis = AnasayfaFragmentDirections.detayGecis(urun)
                Navigation.findNavController(it).navigate(gecis)

        }


        binding.buttonGoster.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.bottomSheetFragment)
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("yaşam döngüsü","onCreate")
    }

    override fun onPause() {
        super.onPause()
        Log.e("yaşam döngüsü","onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.e("yaşam döngüsü","onResume")
    }
}