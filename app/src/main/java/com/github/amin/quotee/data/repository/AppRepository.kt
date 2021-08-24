package com.github.amin.quotee.data.repository

import com.github.amin.quotee.data.remote.Resource
import com.github.amin.quotee.data.remote.responses.QuotesResponse
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun getAllQuotes(): Flow<List<QuotesResponse>>
    suspend fun getRandomQuote(
        success: (data: QuotesResponse) -> Unit,
        failure: (message: String) -> Unit
    )
    suspend fun getQuoteDetails(
        id: String,
        success: (data: QuotesResponse) -> Unit,
        failure: (message: String) -> Unit
    )
}