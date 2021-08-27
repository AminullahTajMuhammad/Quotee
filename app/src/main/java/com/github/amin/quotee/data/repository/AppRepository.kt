package com.github.amin.quotee.data.repository

import com.github.amin.quotee.data.remote.Resource
import com.github.amin.quotee.data.remote.responses.QuotesResponse
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun getAllQuotes(): Flow<Resource<List<QuotesResponse>>>
    suspend fun getRandomQuote(): Flow<Resource<QuotesResponse>>
    suspend fun getQuoteDetails(id: String): Flow<Resource<QuotesResponse>>
}