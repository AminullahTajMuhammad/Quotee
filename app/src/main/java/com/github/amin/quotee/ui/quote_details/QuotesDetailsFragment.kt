package com.github.amin.quotee.ui.quote_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.amin.quotee.data.remote.Status
import com.github.amin.quotee.data.remote.responses.QuotesResponse
import com.github.amin.quotee.databinding.FragmentQuotesDetailsBinding
import com.github.amin.quotee.extensions.gone
import com.github.amin.quotee.extensions.invisible
import com.github.amin.quotee.extensions.snackBar
import com.github.amin.quotee.extensions.visible
import com.github.amin.quotee.utils.AppConstants
import com.github.amin.quotee.viewmodel.QuoteeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuotesDetailsFragment : Fragment() {

    private lateinit var binding: FragmentQuotesDetailsBinding

    private val viewModel: QuoteeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentQuotesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(AppConstants.QUOTE_ID)?.let {
            viewModel.getQuoteDetails(it)
        }

        initObserver()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.quoteDetail.collect {
                when(it.status) {
                    Status.LOADING -> {
                        binding.linAuthor.invisible()
                        binding.tvQuote.invisible()
                        binding.progressBar.visible()
                    }
                    Status.SUCCESS -> {
                        binding.linAuthor.visible()
                        binding.tvQuote.visible()
                        binding.progressBar.gone()

                        setupViews(it.data)
                    }
                    Status.ERROR -> {
                        binding.linAuthor.gone()
                        binding.tvQuote.gone()
                        binding.progressBar.gone()
                        snackBar(it.message.toString())
                    }
                }
            }
        }
    }

    private fun setupViews(data: QuotesResponse?) {
        binding.tvQuote.text = data?.content
        binding.tvQuoteBy.text = data?.author

        binding.toolbarQuotesDetails.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}