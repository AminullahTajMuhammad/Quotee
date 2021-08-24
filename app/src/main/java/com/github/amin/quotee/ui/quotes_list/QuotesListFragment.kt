package com.github.amin.quotee.ui.quotes_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.github.amin.quotee.R
import com.github.amin.quotee.adapter.AllQuotesAdapter
import com.github.amin.quotee.data.remote.Status
import com.github.amin.quotee.databinding.FragmentQuotesListBinding
import com.github.amin.quotee.extensions.gone
import com.github.amin.quotee.extensions.isInternetConnected
import com.github.amin.quotee.extensions.snackBar
import com.github.amin.quotee.viewmodel.QuoteeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuotesListFragment : Fragment() {

    private lateinit var binding: FragmentQuotesListBinding
    private lateinit var mAdapter: AllQuotesAdapter

    private val viewModel: QuoteeViewModel by viewModels()

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

        if(context?.isInternetConnected == true) {
            viewModel.getAllQuotes()
        } else {
            binding.shimmer.gone()
            snackBar("No Internet. Please check internet connection...")
        }

        setupUI()
        initObservers()
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

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.allQuotes.collect {
                when(it.status) {
                    Status.LOADING -> {
                        binding.shimmer.startShimmer()
                    }
                    Status.SUCCESS -> {
                        binding.shimmer.stopShimmer()
                        binding.shimmer.gone()
                        it.data?.let { it1 -> mAdapter.updateData(it1) }
                    }
                    Status.ERROR -> {
                        binding.shimmer.stopShimmer()
                        binding.shimmer.gone()
                        Log.d("ERROR", it.message.toString())
                        snackBar(it.message.toString())
                    }
                }
            }
        }
    }
}