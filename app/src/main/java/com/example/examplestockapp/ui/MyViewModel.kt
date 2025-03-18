package com.example.examplestockapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examplestockapp.data.SortType
import com.example.examplestockapp.di.MyDataRepository
import com.example.examplestockapp.network.NetworkStatus
import com.example.examplestockapp.network.StockApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    val myDataRepository: MyDataRepository,
    private val stockApi: StockApi,
) : ViewModel()  {

    init {
        myDataRepository.sortType.observeForever{
            when (it) {
                SortType.SmallToBig -> {
                    myDataRepository.smallToBig()
                }

                else -> {
                    myDataRepository.bigToSmall()
                }
            }
        }
    }

    val networkStatus = MutableLiveData(NetworkStatus.Offline)

    fun startQueryData(){
        networkStatus.postValue(NetworkStatus.Connecting)

        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val flow1 = flow {
                    emit(stockApi.bwibbuAll().execute())
                }

                val flow2 = flow {
                    emit(stockApi.stockDayAvgAll().execute())
                }

                val flow3 = flow {
                    emit(stockApi.stockDayAll().execute())
                }

                combine(flow1, flow2, flow3){ bwibbuData, stockDayAvgData, stockDayAll ->

                    val cardItemList = mutableListOf<CardItem>()

                    bwibbuData.body()?.forEach{ itemA ->
                        val cardItem = CardItem()
                        cardItem.code = itemA.code
                        cardItem.codeInt = itemA.code.toInt()
                        cardItem.name = itemA.name
                        cardItem.peRatio = itemA.peRatio
                        cardItem.pbRatio = itemA.pbRatio
                        cardItem.dividendYield = itemA.dividendYield

                        stockDayAvgData.body()?.let { listB ->
                            myDataRepository.findStockDayAvgData(cardItem.code, listB)?.let {
                                cardItem.monthlyAveragePrice = it.monthlyAverage
                                cardItem.closingPrice = it.closingPrice
                            }
                        }

                        stockDayAll.body()?.let { listC ->
                            myDataRepository.findStockDayAllData(cardItem.code, listC)?.let {
                                cardItem.openingPrice = it.openingPrice
                                cardItem.closingPrice = it.closingPrice
                                cardItem.tradeValue = it.tradeValue
                                cardItem.tradeVolume = it.tradeVolume
                                cardItem.highestPrice = it.highestPrice
                                cardItem.lowestPrice = it.lowestPrice
                                cardItem.change = it.change
                                cardItem.transaction = it.transaction
                            }
                        }
                        cardItemList.add(cardItem)
                    }

                    when (myDataRepository.sortType.value) {
                        SortType.SmallToBig -> {
                            cardItemList.sortWith(myDataRepository.smallToBigComparator)
                        }

                        else -> {
                            cardItemList.sortWith(myDataRepository.bigToSmallComparator)
                        }
                    }

                    return@combine cardItemList
                }
                    .flowOn(Dispatchers.IO)
                    .catch {
                        networkStatus.postValue(NetworkStatus.ConnectFailed)
                        it.printStackTrace()
                    }
                    .collect{
                        networkStatus.postValue(NetworkStatus.ConnectSuccess)
                        myDataRepository.cardListLiveData.postValue(it)
                    }

            }

        }
    }

    fun changeOrderType(sortType: SortType) {
        myDataRepository.sortType.postValue(sortType)
    }
}