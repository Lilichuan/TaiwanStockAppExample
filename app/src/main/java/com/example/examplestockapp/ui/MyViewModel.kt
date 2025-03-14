package com.example.examplestockapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examplestockapp.di.MyDataRepository
import com.example.examplestockapp.network.StockApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val myDataRepository: MyDataRepository,
    private val stockApi: StockApi,
) : ViewModel()  {

    fun startQueryData(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val flow1 = flow {
                    emit(stockApi.bwibbuAll())
                }

                val flow2 = flow {
                    emit(stockApi.stockDayAvgAll())
                }

                val flow3 = flow {
                    emit(stockApi.stockDayAll())
                }

                combine(flow1, flow2, flow3){ data1, data2, data3 ->

                }
            }

        }
    }
}