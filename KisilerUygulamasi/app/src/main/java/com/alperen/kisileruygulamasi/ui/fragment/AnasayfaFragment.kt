package com.alperen.kisileruygulamasi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.alperen.kisileruygulamasi.R
import com.alperen.kisileruygulamasi.data.entity.Kisiler
import com.alperen.kisileruygulamasi.databinding.FragmentAnasayfaBinding
import com.alperen.kisileruygulamasi.ui.adapter.KisilerAdapter

class AnasayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnasayfaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnasayfaBinding.inflate(inflater,container,false)
        binding.r1.layoutManager= LinearLayoutManager(requireContext())
        val kisislerListesi = ArrayList<Kisiler>()
        val k1 = Kisiler(1,"ahmeaknsd","123123")
        val k2 = Kisiler(2,"asdzzxvc","193800123")
        val k3 = Kisiler(3,"aşdkasd","1312312324")

        kisislerListesi.add(k1)
        kisislerListesi.add(k2)
        kisislerListesi.add(k3)

        val _kisilerAdapter= KisilerAdapter(requireContext(),kisislerListesi)
        binding.r1.adapter=_kisilerAdapter

        // fragmenatlar arasında geçiş kodu
        binding.fab.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.kisiKayitFragment)
        }
        binding.toolbarAnasayfa.title="Kişiler "
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener{

            override fun onQueryTextChange(newText: String): Boolean {
                ara(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                ara(query)
                return true
            }



        })
        return binding.root
    }

    fun ara(aramakelimesi: String)
    {
            Log.e("Kişi ara", aramakelimesi)
    }


}