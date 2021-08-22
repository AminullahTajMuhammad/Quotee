package com.github.amin.quotee.data.remote

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCallback<T>(initMethod: RetrofitCallback<T>.() -> Unit): Callback<T> {

    private var _failureCallback: (call: Call<T>?, throwable: Throwable?) -> Unit = { _, _ -> }
    private var _successCallback: (call: Call<T>?, response: Response<T>?) -> Unit = { _, _ -> }

    override fun onFailure(call: Call<T>, t: Throwable) {
        _failureCallback(call, t)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        _successCallback(call, response)
    }


    fun onSuccess(callback: (call: Call<T>?, response: Response<T>?) -> Unit) {
        _successCallback = callback
    }

    fun onFailure(callback: (call: Call<T>?, t: Throwable?) -> Unit) {
        _failureCallback = callback
    }

    init {
        initMethod()
    }
}