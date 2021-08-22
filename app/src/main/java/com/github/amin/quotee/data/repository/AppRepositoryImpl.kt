package com.github.amin.quotee.data.repository

import com.github.amin.quotee.data.remote.ApiService
import com.github.amin.quotee.data.remote.RetrofitCallback
import com.github.amin.quotee.data.remote.responses.QuotesResponse
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): AppRepository {

    override suspend fun getAllQuotes(
        success: (data: ArrayList<QuotesResponse>) -> Unit,
        failure: (message: String) -> Unit
    ) {
        apiService.getAllQuotes().enqueue(RetrofitCallback {
            onSuccess { call, response ->
                if(response != null && response.isSuccessful && response.code() == 200) {
                    val result = response.body()
                    success.invoke(result as ArrayList<QuotesResponse>)
                } else {
                    failure.invoke("Something went wrong")
                }
            }

            onFailure { call, t ->
                failure.invoke("Failed to get data...")
            }
        })
    }

    override suspend fun getRandomQuote(
        success: (data: QuotesResponse) -> Unit,
        failure: (message: String) -> Unit
    ) {
        apiService.getRandomQuote().enqueue(RetrofitCallback {
            onSuccess { call, response ->
                if(response != null && response.isSuccessful && response.code() == 200) {
                    val result = response.body()
                    result?.let {
                        success.invoke(it)
                    }
                } else {
                    failure.invoke("Something went wrong")
                }
            }

            onFailure { call, t ->
                failure.invoke("Failed to get data...")
            }
        })
    }

    override suspend fun getQuoteDetails(
        id: String,
        success: (data: QuotesResponse) -> Unit,
        failure: (message: String) -> Unit
    ) {
        apiService.getQuoteDetail(id).enqueue(RetrofitCallback {
            onSuccess { call, response ->
                if(response != null && response.isSuccessful && response.code() == 200) {
                    val result = response.body()
                    result?.let {
                        success.invoke(it)
                    }
                } else {
                    failure.invoke("Something went wrong")
                }
            }

            onFailure { call, t ->
                failure.invoke("Failed to get data...")
            }
        })
    }

}