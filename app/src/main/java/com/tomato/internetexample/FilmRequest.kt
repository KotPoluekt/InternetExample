package com.tomato.internetexample

import com.google.gson.annotations.SerializedName

data class FilmRequest (
    @SerializedName("real_name")
    val name: String,
    val id: Int
)
