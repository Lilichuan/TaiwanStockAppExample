package com.example.examplestockapp.di

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.example.examplestockapp.data.SortType
import com.example.examplestockapp.network.beans.StockAvgItem
import com.example.examplestockapp.network.beans.StockDayItem
import com.example.examplestockapp.ui.CardItem
import javax.inject.Inject

class MyDataRepository @Inject constructor() {

    val cardListLiveData = MutableLiveData<List<CardItem>>()

    val sortType = MutableLiveData(SortType.SmallToBig)


    fun findStockDayAvgData(code: String?, list: List<StockAvgItem>): StockAvgItem? {
        if (TextUtils.isEmpty(code)) {
            return null
        }

        list.forEach {
            if (code.contentEquals(it.code)) {
                return it
            }
        }

        return null
    }


    fun findStockDayAllData(code: String?, list: List<StockDayItem>): StockDayItem? {
        if (TextUtils.isEmpty(code)) {
            return null
        }

        list.forEach {
            if (code.contentEquals(it.code)) {
                return it
            }
        }

        return null
    }

    fun smallToBig() {
        cardListLiveData.value?.let {
            val list = mutableListOf<CardItem>()
            list.addAll(it)
            list.sortWith(smallToBigComparator)
            cardListLiveData.postValue(list)
        }
    }

    fun bigToSmall() {
        cardListLiveData.value?.let {
            val list = mutableListOf<CardItem>()
            list.addAll(it)
            list.sortWith(bigToSmallComparator)
            cardListLiveData.postValue(list)
        }
    }

    val bigToSmallComparator = Comparator<CardItem> { a, b ->
        b.codeInt - a.codeInt
    }

    val smallToBigComparator = Comparator<CardItem> { a, b ->
        a.codeInt - b.codeInt
    }

    private fun createCardItem(stockDay: StockDayItem): CardItem {
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