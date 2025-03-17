package com.example.examplestockapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asFlow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.examplestockapp.R
import com.example.examplestockapp.ui.composeui.StockCardA
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainListFragment : Fragment(
) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: MyViewModel by hiltNavGraphViewModels(R.id.nav_graph)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.startQueryData()
            }
        }
        val composeView = ComposeView(requireContext())
        composeView.setContent {
            ListView(viewModel)
        }
        return composeView
    }

    @Composable
    private fun ListView(viewModel: MyViewModel) {
        val initValue = listOf<CardItem>()
        val list by viewModel.myDataRepository.cardListLiveData.asFlow()
            .collectAsStateWithLifecycle(initValue)
        LazyColumn {
            items(list.size) { index ->
                OneCard(list[index])
            }
        }
    }


    @Composable
    private fun OneCard(
        item: CardItem,
        onClick: () -> Unit = {}
    ) {
        StockCardA(
            code = item.code,
            name = item.name,
            openingPrice = item.openingPrice,
            closingPrice = item.closingPrice,
            highestPrice = item.highestPrice,
            lowestPrice = item.lowestPrice,
            change = item.change,
            monthlyAveragePrice = item.monthlyAveragePrice,
            transaction = item.transaction,
            tradeValue = item.tradeValue,
            tradeVolume = item.tradeVolume
        )
    }

}