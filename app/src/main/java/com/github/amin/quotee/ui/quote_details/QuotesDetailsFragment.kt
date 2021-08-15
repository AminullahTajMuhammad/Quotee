package com.github.amin.quotee.ui.quote_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.amin.quotee.databinding.FragmentQuotesDetailsBinding

class QuotesDetailsFragment : Fragment() {

    private lateinit var binding: FragmentQuotesDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentQuotesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
}