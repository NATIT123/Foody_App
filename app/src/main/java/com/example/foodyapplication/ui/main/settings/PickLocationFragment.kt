package com.example.foodyapplication.ui.main.settings

import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodyapplication.databinding.FragmentPickLocationBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.events.MapListener
import org.osmdroid.events.ScrollEvent
import org.osmdroid.events.ZoomEvent
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.MapEventsOverlay
import java.io.IOException
import java.net.URLEncoder
import java.util.*
import java.util.concurrent.TimeUnit

class PickLocationFragment : Fragment() {

    private lateinit var binding: FragmentPickLocationBinding
    private lateinit var suggestionAdapter: AddressSuggestionAdapter
    private lateinit var geocoder: Geocoder
    private var reverseJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        geocoder = Geocoder(requireContext(), Locale.getDefault())
        Configuration.getInstance()
            .load(requireContext(), requireContext().getSharedPreferences("osmdroid", 0))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPickLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMap()
        setupRecyclerView()

        //Back
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.etSearch.addTextChangedListener {
            lifecycleScope.launch {
                delay(1000)
                searchByKeyword(it.toString())

            }
        }
    }

    private fun setupMap() {
        binding.mapView.setTileSource(TileSourceFactory.MAPNIK)
        binding.mapView.setMultiTouchControls(true)
        binding.mapView.controller.setZoom(18.0)

        // Default center (Tân Bình)
        val startPoint = GeoPoint(10.8167, 106.6525)
        binding.mapView.controller.setCenter(startPoint)

        // Listen for map dragging
        val mapEventsOverlay = MapEventsOverlay(object : MapEventsReceiver {
            override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean = true
            override fun longPressHelper(p: GeoPoint?): Boolean = false
        })
        binding.mapView.overlays.add(mapEventsOverlay)

        binding.mapView.addMapListener(object : MapListener {
            override fun onScroll(event: ScrollEvent?): Boolean {
                reverseJob?.cancel()
                reverseJob = lifecycleScope.launch {
                    delay(800)
                    val center = binding.mapView.mapCenter as GeoPoint
                    center.let {
                        val address = reverseGeocodeNominatim(center.latitude, center.longitude)
                        if (address != null) {
                            suggestionAdapter.updateData(listOf(address))
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Không thể lấy địa chỉ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }

                return true
            }

            override fun onZoom(event: ZoomEvent?): Boolean {
                return false
            }
        })
    }

    private fun setupRecyclerView() {
        suggestionAdapter = AddressSuggestionAdapter(emptyList()) { selectedAddress ->
            Toast.makeText(requireContext(), "Chọn: $selectedAddress", Toast.LENGTH_SHORT).show()
        }
        binding.recyclerSuggestions.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = suggestionAdapter
        }
    }

    suspend fun reverseGeocodeNominatim(lat: Double, lon: Double): String? =
        withContext(Dispatchers.IO) {
            val url = "https://nominatim.openstreetmap.org/reverse?format=json&lat=$lat&lon=$lon"

            val request = Request.Builder()
                .url(url)
                .header("User-Agent", "FoodyApp/1.0 (contact@foodyapp.com)")
                .build()

            val client = OkHttpClient.Builder()
                .callTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()

            try {
                val response = client.newCall(request).execute()
                val bodyStr = response.body?.string()



                if (!response.isSuccessful || bodyStr.isNullOrBlank() || bodyStr.startsWith("<")) {
                    Log.e("Nominatim", "Invalid response or blocked (HTML)")
                    return@withContext null
                }

                val json = JSONObject(bodyStr)
                return@withContext json.optString("display_name", null)
            } catch (e: IOException) {
                Log.e("Nominatim", "Timeout or network error: ${e.message}")
                return@withContext null
            }
        }

    suspend fun searchByKeyword(keyword: String): List<String> =
        withContext(Dispatchers.IO) {
            if (keyword.isBlank()) return@withContext emptyList()

            val encodedKeyword = URLEncoder.encode(keyword, "UTF-8")
            val url =
                "https://nominatim.openstreetmap.org/search?q=$encodedKeyword&format=json&limit=10&addressdetails=1&accept-language=vi"

            Log.d("Nominatim", "Search URL: $url")

            val request = Request.Builder()
                .url(url)
                .header("User-Agent", "FoodyApp/1.0 (contact@foodyapp.com)") // BẮT BUỘC
                .build()

            val client = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()

            try {
                val response = client.newCall(request).execute()
                val responseBody = response.body?.string()

                if (!response.isSuccessful || responseBody.isNullOrBlank() || responseBody.startsWith(
                        "<"
                    )
                ) {
                    Log.e("Nominatim", "Invalid or HTML response: $responseBody")
                    return@withContext emptyList()
                }

                val jsonArray = JSONArray(responseBody)
                return@withContext List(jsonArray.length()) { i ->
                    jsonArray.getJSONObject(i).optString("display_name", "")
                }.filter { it.isNotBlank() }

            } catch (e: IOException) {
                Log.e("Nominatim", "Network error: ${e.message}")
                return@withContext emptyList()
            }
        }


}
