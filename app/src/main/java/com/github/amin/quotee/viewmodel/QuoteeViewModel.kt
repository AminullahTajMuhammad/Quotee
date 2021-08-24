package com.github.amin.quotee.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.amin.quotee.data.remote.Resource
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
            appRepository.getAllQuotes()
                .catch {
                    _allQuotes.value = (Resource.error(it.message.toString(), null))
                }
                .collect {
                    _allQuotes.value = Resource.success(it as ArrayList<QuotesResponse>)
                }
        }
    }
}