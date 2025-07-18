package com.example.foodyapplication.ui.main.search

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodyapplication.R
import com.example.foodyapplication.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private val suggestions = listOf(
        Pair("Cơm chay", R.drawable.ic_voucher),
        Pair("Bún thịt nướng", R.drawable.ic_voucher),
        Pair("Bún", R.drawable.ic_voucher),
        Pair("Bún đậu mắm tôm", R.drawable.ic_voucher),
        Pair("Bánh tráng trộn", R.drawable.ic_voucher),
        Pair("Gà rán", R.drawable.ic_voucher)
    )

    private val history = mutableListOf("Gà rán")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupHistory()
        setupRecyclerView()

        binding.txtDeleteHistory.setOnClickListener {
            history.clear()
            binding.layoutHistory.removeAllViews()
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnSearch.setOnClickListener {
            val query = binding.edtSearch.text.toString()
            if (query.isNotBlank()) {
                if (!history.contains(query)) {
                    history.add(0, query)
                    addHistoryChip(query)
                }
            }
        }

    }

    private fun setupHistory() {
        binding.layoutHistory.removeAllViews()
        history.forEach {
            addHistoryChip(it)
        }
    }

    private fun addHistoryChip(text: String) {
        val chip = TextView(requireContext())
        chip.text = text
        chip.setPadding(16, 8, 16, 8)
        chip.setBackgroundResource(R.drawable.bg_chip)
        chip.setTextColor(Color.BLACK)
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        lp.setMargins(8, 4, 8, 4)
        chip.layoutParams = lp
        binding.layoutHistory.addView(chip)
    }

    private fun setupRecyclerView() {
        binding.recyclerSuggest.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerSuggest.adapter = SuggestAdapter(suggestions)
    }


}