package com.github.amin.quotee.data.repository

import android.util.Log
import com.github.amin.quotee.data.remote.ApiService
import com.github.amin.quotee.data.remote.Resource
import com.github.amin.quotee.data.remote.responses.QuotesResponse
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

@DelicateCoroutinesApi
class AppRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): AppRepository {

    override suspend fun getAllQuotes(): Flow<Resource<List<QuotesResponse>>> {
        return flow {
            try {
                val quotes = apiService.getAllQuotes()
                if(quotes.isEmpty()) {
                    emit(Resource.empty<List<QuotesResponse>>())
                } else {
                    emit(Resource.success(quotes))
                }
            } catch (e: Exception) {
                if(e is IOException) {
                    emit(Resource.error<List<QuotesResponse>>("No Internet Connection", null))
                } else {
                    emit(Resource.error<List<QuotesResponse>>("Something went wrong..", null))
                }
            }
        }
    }

    override suspend fun getRandomQuote(): Flow<Resource<QuotesResponse>> {
        return flow {
            try {
                emit(Resource.success(apiService.getRandomQuote()))
            } catch (e: Exception) {
                if(e is IOException) {
                    emit(Resource.error<QuotesResponse>("No Internet Connection", null))
                } else {
                    emit(Resource.error<QuotesResponse>("Something went wrong..", null))
                }
            }
        }
    }

    override suspend fun getQuoteDetails(id: String): Flow<Resource<QuotesResponse>> {
        return flow {
            try {
                emit(Resource.success(apiService.getQuoteDetail(id)))
            } catch (e: Exception) {
                if(e is IOException) {
                    emit(Resource.error<QuotesResponse>("No Internet Connection", null))
                } else {
                    Log.d("ERROR", e.localizedMessage.toString())
                    emit(Resource.error<QuotesResponse>("Something went wrong..", null))
                }
            }
        }
    }


}