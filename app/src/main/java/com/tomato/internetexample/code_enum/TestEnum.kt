package com.tomato.internetexample.code_enum

import android.graphics.Color
import com.google.gson.*
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

private const val JSON = """[
    
    {
        "country_name": "Russia",
        "color": [1, 2, 3],
        "id": 1
    }  
    ]
"""

fun main() {
    val gson = getGson()

    val items = gson.fromJson<List<Flag>>(JSON, object: TypeToken<List<Flag>>(){}.type)

    println(items.toString())
    println("----------")

    println(gson.toJson(items))
}

private fun  getGson() = GsonBuilder()
    .create()

data class Flag(
    @SerializedName("country_name")
    val country: String,
    @SerializedName("color")
    val colors: List<CustomColor>,
    @SerializedName("id")
    val id: Int
)

enum class CustomColor{
    @SerializedName("1")
    WHITE,
    @SerializedName("2")
    BLUE,
    @SerializedName("3")
    RED
}