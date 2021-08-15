package com.github.amin.quotee.ui.quotes_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.github.amin.quotee.R
import com.github.amin.quotee.adapter.AllQuotesAdapter
import com.github.amin.quotee.databinding.FragmentQuotesListBinding

class QuotesListFragment : Fragment() {

    private lateinit var binding: FragmentQuotesListBinding
    private lateinit var mAdapter: AllQuotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mAdapter = AllQuotesAdapter(requireContext())
        binding =  FragmentQuotesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        binding.btnDarkMode.setOnClickListener {
            val mode = AppCompatDelegate.getDefaultNightMode()
            if(mode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
        binding.recyclerQuotes.apply {
            adapter = mAdapter
        }
    }
}