package com.example.examplestockapp.network

import com.example.examplestockapp.network.beans.BwibbuItem
import com.example.examplestockapp.network.beans.StockAvgItem
import com.example.examplestockapp.network.beans.StockDayItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface StockApi {
    @Headers("Accept: application/json")
    @GET("exchangeReport/BWIBBU_ALL")
    fun bwibbuAll() : Call<List<BwibbuItem>>

    @Headers("Accept: application/json")
    @GET("exchangeReport/STOCK_DAY_AVG_ALL")
    fun stockDayAvgAll(): Call<List<StockAvgItem>>

    @Headers("Accept: application/json")
    @GET("exchangeReport/STOCK_DAY_ALL")
    fun stockDayAll(): Call<List<StockDayItem>>
}