package com.github.amin.quotee.data.remote.responses

import com.google.gson.annotations.SerializedName

data class QuotesResponse(
    @SerializedName("_id")
    var _id: String,
    var content: String,
    var author: String
)