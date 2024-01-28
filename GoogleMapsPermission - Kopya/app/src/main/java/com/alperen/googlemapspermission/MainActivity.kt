package com.alperen.googlemapspermission

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.alperen.googlemapspermission.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task


/*
1. KULLANICIDAN KONUM İZNİNİ AL
2. KONUM İZNİNDEN SONRA MARKER İLE İŞARETLENMİŞ OLAN KONUMU YAZDIR
3. MARKER'I HAREKET ETTİRİP, FARKLI KONUMLARDAKİ ÜRÜNLERE ULAŞABİLMESİ SAĞLANMALI
 */
class MainActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mMap: GoogleMap
    private lateinit var lastLocation: Location
    private var izinKontrol = 0
    private lateinit var flpc: FusedLocationProviderClient
    private lateinit var locationTask: Task<Location>

    companion object {
        private const val LOCATION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        flpc = LocationServices.getFusedLocationProviderClient(this)

        // Tasarımımızdaki haritayı aktif etmek için kullanılan kod blogu
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        binding.buttonGetKonum.setOnClickListener {

            if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_REQUEST_CODE)
                return@setOnClickListener
            }
            mMap.isMyLocationEnabled = true
            flpc.lastLocation.addOnSuccessListener(this) { location ->
                if (location != null) {
                    lastLocation = location
                    val currentLatLong = LatLng(location.latitude, location.longitude)
                    placeMarkerOnMap(currentLatLong)
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 12f))
                }
            }
        }

    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)

        // MARKER'IN HAREKETLERİNİN SAĞLANDIĞI KISIM

        mMap.setOnMapClickListener { latLng ->
            // Tıklanan konumu kullanarak bir marker oluştur
            val markerOptions = MarkerOptions().position(latLng).title("Yeni Konum")

            // Haritaya marker'ı ekle
            val marker = mMap.addMarker(markerOptions)

            // Marker'ı sürükleyerek yerini değiştirmeye izin ver
            if (marker != null) {
                marker.isDraggable = true
            }
        }

        // Marker sürükleme olayını dinle

    }
    private fun placeMarkerOnMap(currentLatLong: LatLng) {
        val markerOptions = MarkerOptions().position(currentLatLong)
        markerOptions.title("$currentLatLong")
        val userLocationMarker = mMap.addMarker(markerOptions)
        mMap.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
            override fun onMarkerDragStart(marker: Marker) {
                // Marker sürüklemeye başlandığında yapılacak işlemler

            }

            override fun onMarkerDrag(marker: Marker) {
                // Marker sürüklendiğinde yapılacak işlemler
            }

            override fun onMarkerDragEnd(marker: Marker) {
                // Marker sürükleme bittiğinde yapılacak işlemler
                // position değişkeninde marker'ın yeni konumu bulunabilir
            }
        })
    }

    override fun onMarkerClick(p0: Marker) = false
}




