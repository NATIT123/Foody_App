package com.example.foodyapplication.ui.main.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodyapplication.R

class ComingOrderFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SuggestionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_coming, container, false)
        recyclerView = view.findViewById(R.id.recyclerSuggestion)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = SuggestionAdapter(generateFakeSuggestions())
        recyclerView.adapter = adapter
        return view
    }

    private fun generateFakeSuggestions(): List<String> {
        return listOf("Trà sữa A", "Bánh xèo B", "Phở C", "Bún đậu D")
    }
}