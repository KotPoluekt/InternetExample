package com.tomato.internetexample.code

import android.graphics.Color
import com.google.gson.*
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

private const val JSON = """[
    
    {
        "country_name": "Russia",
        "color": "white, blue, red",
        "id": 1
    },
    {
        "country_name": "France",
        "color": "blue, white, red",
        "id": 2
    },
    {
        "country_name": "Germany",
        "color": "black, red, yellow",
        "id": 3
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

//private fun  getGson() = GsonBuilder().create()
private fun  getGson() = GsonBuilder()
    .registerTypeAdapter(CustomColor::class.java,ColorAdapter() )
    .create()

private data class Flag(
    @SerializedName("country_name")
    val country: String,
    @SerializedName("color")
    val colors: CustomColor,
    @SerializedName("id")
    val id: Int
)

private data class CustomColor(
    val colors: List<String>
)

private class ColorAdapter: JsonDeserializer<CustomColor>, JsonSerializer<CustomColor> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CustomColor {
        return CustomColor(colors = json.asString.split(","))
    }

    override fun serialize(
        src: CustomColor,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.colors.joinToString())
    }

}