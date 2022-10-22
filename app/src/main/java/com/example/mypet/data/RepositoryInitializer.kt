package com.example.mypet.data

import android.content.Context
import com.example.mypet.data.local_data_source.PetDao
import com.example.mypet.data.remote_data_source.PetApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RepositoryInitializer {
    private var api: PetApi? = null
    private var dao: PetDao? = null
    private lateinit var repository: RepositoryImpl

    fun getRepository(context: Context): RepositoryImpl {
        if (api == null){
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl("---")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            api = retrofit.create(PetApi::class.java)
        }
        if (dao == null){
         //   dao = PizzaDatabase.getInstance(context)?.dao()
        }
        if (api != null)
            repository = RepositoryImpl(api!!, dao!!)
        return repository
    }
}