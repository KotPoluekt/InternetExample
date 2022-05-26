package com.tomato.internetexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val list by lazy {findViewById<RecyclerView>(R.id.list)}

    val items = mutableListOf<MainItem>()
    val adapter = ListAdapter(items)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.adapter = adapter

        App.instance.api.getFilms()
            .enqueue(object: Callback<List<FilmModel>> {
                override fun onResponse(
                    call: Call<List<FilmModel>>,
                    response: Response<List<FilmModel>>
                ) {

                    items.clear()
                    if (response.isSuccessful) {
                        items.addAll(response.body()?.map { model ->
                            MainItem(model.id, model.title, model.image)
                        } ?: emptyList<MainItem>())
                    }

                    items.add(MainItem(100, "Some film", "http://www.dfdfdf.ru"))
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<List<FilmModel>>, t: Throwable) {
                    println("")
                }


            })


//
//        items.add(
//            MainItem(1, "Film1", "Some icon")
//        )
//        items.add(
//            MainItem(2, "Film2", "Some icon")
//        )
//        items.add(
//            MainItem(3, "Film3", "Some icon")
//        )
//
//        adapter.notifyDataSetChanged()
    }
}