package com.example.examplestockapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asFlow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.examplestockapp.R
import com.example.examplestockapp.data.SortType
import com.example.examplestockapp.ui.composeui.StockCardA
import com.example.examplestockapp.ui.composeui.StockDetailDialog
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainListFragment : Fragment(
) {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: MyViewModel by hiltNavGraphViewModels(R.id.nav_graph)

        viewModel.networkStatus.observe(viewLifecycleOwner) { status ->
            Toast.makeText(requireContext(), status.textId, Toast.LENGTH_SHORT).show()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.startQueryData()
            }
        }
        val composeView = ComposeView(requireContext())
        composeView.setContent {
            MyScoffold(viewModel)
        }
        return composeView
    }

    @Composable
    @ExperimentalMaterial3Api
    private fun MyScoffold(viewModel: MyViewModel) {
        val sheetState = rememberModalBottomSheetState()
        val scope = rememberCoroutineScope()
        var showBottomSheet by remember { mutableStateOf(false) }


        Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(stringResource(R.string.fragment_title))
                    },
                    actions = {
                        Text(
                            modifier = Modifier.clickable {
                                showBottomSheet = true
                            },
                            text = stringResource(R.string.menu_more)
                        )
                    }
                )
            },
        ) { innerPadding ->
            ListView(modifier = Modifier.padding(innerPadding) ,viewModel)
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState
                ) {
                    val selected = viewModel.myDataRepository.sortType.value
                    val newOrder =
                        if (selected == SortType.SmallToBig) SortType.BigToSmall else SortType.SmallToBig
                    Text(
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                showBottomSheet = false
                                viewModel.changeOrderType(newOrder)
                        },
                        text = stringResource(newOrder.textRes)
                    )
                }
            }
        }
    }

    @Composable
    private fun ListView(modifier : Modifier = Modifier,viewModel: MyViewModel) {
        val initValue = listOf<CardItem>()
        val list by viewModel.myDataRepository.cardListLiveData.asFlow()
            .collectAsStateWithLifecycle(initValue)
        LazyColumn(modifier = modifier) {
            items(list.size) { index ->
                val item = list[index]
                OneCard(item)
            }
        }
    }


    @Composable
    private fun OneCard(
        item: CardItem
    ) {
        val showDialog = remember { mutableStateOf(false) }

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
            tradeVolume = item.tradeVolume,
            onclickEvent = { showDialog.value = true }
        )
        HorizontalDivider(color = Color.LightGray, thickness = 1.dp)

        if (showDialog.value) {
            StockDetailDialog(
                name = item.name!!,
                peRatio = item.peRatio,
                pbRatio = item.pbRatio,
                dividendYield = item.dividendYield,
                onDismissRequest = { showDialog.value = false }
            )
        }
    }

}