package com.example.examplestockapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

                combine(flow1, flow2, flow3){ data1, data2, data3 ->
                    data2.body()?.let {
                        myDataRepository.updateAvgList(it)
                    }

                    data3.body()?.let {
                        networkStatus.postValue(NetworkStatus.ConnectSuccess)
                        val cardList = myDataRepository.mapToCardItems(it)
                        myDataRepository.cardListLiveData.postValue(cardList)
                    }

                    networkStatus.postValue(NetworkStatus.ConnectFailed)
                }
                    .flowOn(Dispatchers.IO)
                    .catch {
                        it.printStackTrace()
                    }
                    .collect{

                    }

            }

        }
    }
}