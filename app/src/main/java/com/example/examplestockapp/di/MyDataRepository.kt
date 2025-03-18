package com.example.examplestockapp.di

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.example.examplestockapp.network.beans.BwibbuItem
import com.example.examplestockapp.network.beans.StockAvgItem
import com.example.examplestockapp.network.beans.StockDayItem
import com.example.examplestockapp.ui.CardItem
import javax.inject.Inject

class MyDataRepository @Inject constructor() {
    val bwiListData: MutableLiveData<List<BwibbuItem>> = MutableLiveData<List<BwibbuItem>>()

    val stockAvgListData = MutableLiveData<List<StockAvgItem>>()

    val stockDayListData = MutableLiveData<List<StockDayItem>>()

    val cardListLiveData = MutableLiveData<List<CardItem>>()

    fun updateBwiList(receiveData: List<BwibbuItem>) {
        bwiListData.postValue(receiveData)
    }

    fun updateAvgList(receiveData: List<StockAvgItem>) {
        stockAvgListData.postValue(receiveData)
    }

    fun updateDayList(receiveData: List<StockDayItem>) {
        stockDayListData.postValue(receiveData)
    }


    fun findStockDayAvgData(code : String? , list : List<StockAvgItem>) : StockAvgItem?{
        if(TextUtils.isEmpty(code)){
            return null
        }

        list.forEach{
            if(code.contentEquals(it.code)){
                return it
            }
        }

        return null
    }


    fun findStockDayAllData(code : String? , list : List<StockDayItem>) : StockDayItem?{
        if(TextUtils.isEmpty(code)){
            return null
        }

        list.forEach{
            if(code.contentEquals(it.code)){
                return it
            }
        }

        return null
    }


    private fun createCardItem(stockDay : StockDayItem) : CardItem{
        return CardItem().also {
            it.code = stockDay.code
            it.name = stockDay.name
            it.openingPrice = stockDay.openingPrice
            it.closingPrice = stockDay.closingPrice
            it.tradeValue = stockDay.tradeValue
            it.tradeVolume = stockDay.tradeVolume
            it.highestPrice = stockDay.highestPrice
            it.lowestPrice = stockDay.lowestPrice
            it.change = stockDay.change
            it.transaction = stockDay.transaction
        }
    }
}