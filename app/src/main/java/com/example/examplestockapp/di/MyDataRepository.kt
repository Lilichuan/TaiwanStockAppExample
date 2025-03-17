package com.example.examplestockapp.di

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

    fun mapToCardItems(originalList : List<StockDayItem>) : List<CardItem>{
        val cardList = mutableListOf<CardItem>()

        for (stockDayItem in originalList){
            val cardItem = createCardItem(stockDayItem)
            findStockAvgItemItem(stockDayItem.code)?.let {
                cardItem.monthlyAveragePrice = it.monthlyAverage
            }

            cardList.add(cardItem)
        }
        return cardList
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