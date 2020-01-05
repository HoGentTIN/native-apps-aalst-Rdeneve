package com.example.dmtool.shared

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

private const val BASE_URL = "http://10.0.2.2:56549/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// interceptor to enable logging for retrofit
private val interceptor = run {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.apply {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }
}

private val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory((MoshiConverterFactory.create(moshi)))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

interface DmToolApiService {
    @GET("Campaigns")
    fun getCampaignsAsync(): Deferred<List<NetworkCampaign>>

    @GET("Npcs/Campaign/{campaignId}")
    fun getNpcsForCampaignAsync(@Path("campaignId") campaignId: Long): Deferred<List<NetworkNpc>>

    @POST("Campaigns")
    fun postCampaign(@Body campaign: NetworkCampaign): Deferred<Any>

    @POST("Npcs")
    fun postNpc(@Body npc: NetworkNpc): Deferred<Any>
}

object DmToolApi {
    val retrofitService: DmToolApiService by lazy { retrofit.create(DmToolApiService::class.java)}
}