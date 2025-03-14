package com.example.examplestockapp.di

import androidx.lifecycle.MutableLiveData
import com.example.examplestockapp.network.beans.BwibbuItem
import com.example.examplestockapp.network.beans.StockAvgItem
import com.example.examplestockapp.network.beans.StockDayItem
import javax.inject.Inject

class MyDataRepository @Inject constructor() {
    val bwiListData: MutableLiveData<List<BwibbuItem>> = MutableLiveData<List<BwibbuItem>>()

    val stockAvgListData: MutableLiveData<List<StockAvgItem>> =
        MutableLiveData<List<StockAvgItem>>()

    val stockDayListData: MutableLiveData<List<StockDayItem>> =
        MutableLiveData<List<StockDayItem>>()

    fun updateBwiList(receiveData: List<BwibbuItem>) {
        bwiListData.postValue(receiveData)
    }

    fun updateAvgList(receiveData: List<StockAvgItem>) {
        stockAvgListData.postValue(receiveData)
    }

    fun updateDayList(receiveData: List<StockDayItem>) {
        stockDayListData.postValue(receiveData)
    }

    fun findBwibbuItem(number: String): BwibbuItem? {
        bwiListData.value?.let {
            for (item in it) {
                if (number.contentEquals(item.name)) {
                    return item
                }
            }
        }

        return null
    }

    fun findStockAvgItemItem(number: String): StockAvgItem? {
        stockAvgListData.value?.let {
            for (item in it) {
                if (number.contentEquals(item.name)) {
                    return item
                }
            }
        }

        return null
    }

}