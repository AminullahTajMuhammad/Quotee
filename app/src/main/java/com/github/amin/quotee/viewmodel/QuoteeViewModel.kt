package com.github.amin.quotee.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.amin.quotee.data.remote.Resource
import com.github.amin.quotee.data.remote.Status
import com.github.amin.quotee.data.remote.responses.QuotesResponse
import com.github.amin.quotee.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteeViewModel @Inject constructor(
    private val appRepository: AppRepository
): ViewModel() {

    private val _allQuotes = MutableStateFlow<Resource<ArrayList<QuotesResponse>>>(Resource.loading(null))
    val allQuotes get() = _allQuotes

    fun getAllQuotes() {
        viewModelScope.launch {
            appRepository.getAllQuotes().collect {
                when(it.status) {
                    Status.EMPTY -> {
                        _allQuotes.value = Resource.empty()
                    }
                    Status.SUCCESS -> {
                        _allQuotes.value = Resource.success(it.data as ArrayList)
                    }
                    Status.ERROR -> {
                        _allQuotes.value = Resource.error(it.message.toString(), null)
                    }
                }
            }
        }
    }

    private val _quoteDetail = MutableStateFlow<Resource<QuotesResponse>>(Resource.loading(null))
    val quoteDetail get() = _quoteDetail

    fun getQuoteDetails(id: String) {
        viewModelScope.launch {
            appRepository.getQuoteDetails(id).collect {
                when(it.status) {
                    Status.SUCCESS -> {
                        _quoteDetail.value = Resource.success(it.data)
                    }
                    Status.ERROR -> {
                        _quoteDetail.value = Resource.error(it.message.toString(), null)
                    }
                }
            }
        }
    }


    private val _randomQuote = MutableStateFlow<Resource<QuotesResponse>>(Resource.loading(null))
    val randomQuote get() = _randomQuote

    fun getRandomQuote() {
        viewModelScope.launch {
            appRepository.getRandomQuote().collect {
                when(it.status) {
                    Status.SUCCESS -> {
                        _randomQuote.value = Resource.success(it.data)
                    }
                    Status.ERROR -> {
                        _randomQuote.value = Resource.error(it.message.toString(), null)
                    }
                }
            }
        }
    }
}