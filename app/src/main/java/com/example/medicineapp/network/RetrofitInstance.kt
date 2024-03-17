package com.example.medicineapp.network

import com.squareup.moshi.Moshi
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private const val BASE_URL = "https://run.mocky.io/v3/"
    private const val httpConnectTimeout: Long = 10
    private const val httpReadTimeout: Long = 10
    private const val httpWriteTimeout: Long = 10
    val api: ApiService by lazy {

        val httpClient = provideOkHttpClient()

        val moshi = Moshi.Builder()
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpClient)
            .build()
        retrofit.create(ApiService::class.java)
    }


    private fun provideOkHttpClient(): OkHttpClient {

        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1

        val builder: OkHttpClient.Builder = OkHttpClient().newBuilder()
        builder.dispatcher(dispatcher)


        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY


        return builder
            .connectTimeout(httpConnectTimeout, TimeUnit.SECONDS)
            .readTimeout(httpReadTimeout, TimeUnit.SECONDS)
            .writeTimeout(httpWriteTimeout, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }
}