package com.example.examplestockapp.network

import com.example.examplestockapp.network.beans.BwibbuResponse
import com.example.examplestockapp.network.beans.StockAvgResponse
import com.example.examplestockapp.network.beans.StockDayResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface StockApi {
    @Headers("Accept: application/json")
    @GET("exchangeReport/BWIBBU_ALL")
    fun bwibbuAll() : Call<BwibbuResponse>

    @Headers("Accept: application/json")
    @GET("exchangeReport/STOCK_DAY_AVG_ALL")
    fun stockDayAvgAll(): Call<StockAvgResponse>

    @Headers("Accept: application/json")
    @GET("exchangeReport/STOCK_DAY_ALL")
    fun stockDayAll(): Call<StockDayResponse>
}