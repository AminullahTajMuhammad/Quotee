package com.github.amin.quotee.data.remote

import com.github.amin.quotee.data.remote.responses.QuotesResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("quotes")
    suspend fun getAllQuotes(): List<QuotesResponse>

    @GET("quotes/random")
    fun getRandomQuote(): Call<QuotesResponse>

    @GET("quotes/get/{id}")
    fun getQuoteDetail(@Path("id") id: String): Call<QuotesResponse>
}