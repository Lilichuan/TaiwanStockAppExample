package com.example.examplestockapp.ui.composeui

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examplestockapp.R

@Composable
fun StockCardA(
    code: String,
    name: String,
    openingPrice: String,
    closingPrice: String,
    highestPrice: String,
    lowestPrice: String,
    change: String,
    monthlyAveragePrice: String,
    transaction: String,
    tradeVolume: String,
    tradeValue: String,
    onclickEvent: () -> Unit
) {

    Card(
        modifier = Modifier.clickable {
            onclickEvent()
        },
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {

            Text(modifier = Modifier.padding(5.dp)
                ,text = code)

            Text(modifier = Modifier.padding(5.dp),
                text = name,
                fontSize = 16.sp)

            val rowModifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()

            val smallModifier = Modifier.weight(1.0f)

            Row(modifier = rowModifier)
            {
                SmallTitle(smallModifier, title = R.string.start_price)
                ValueText(smallModifier, valueText = openingPrice)
                SmallTitle(smallModifier, title = R.string.end_price)
                ValueText(smallModifier, valueText = closingPrice)
            }

            Row(modifier = rowModifier) {
                SmallTitle(smallModifier, title = R.string.height_price)
                ValueText(smallModifier, valueText = highestPrice)
                SmallTitle(smallModifier, title = R.string.low_price)
                ValueText(smallModifier, valueText = lowestPrice)
            }

            Row(modifier = rowModifier) {
                SmallTitle(smallModifier, title = R.string.textA)
                ValueText(smallModifier, valueText = change)
                SmallTitle(smallModifier, title = R.string.average_month_price)
                ValueText(smallModifier, valueText = monthlyAveragePrice)
            }

            Row(modifier = rowModifier) {
                SmallTitle(smallModifier, title = R.string.deal_count)
                ValueText(smallModifier, valueText = transaction)
                SmallTitle(smallModifier, title = R.string.deal_abc)
                ValueText(smallModifier, valueText = tradeVolume)
                SmallTitle(smallModifier, title = R.string.deal_money)
                ValueText(smallModifier, valueText = tradeValue)
            }
        }
    }


}

@Composable
fun SmallTitle(
    modifier: Modifier = Modifier,
    @StringRes title: Int
) {
    Text(
        modifier = modifier,
        text = stringResource(title),
        textAlign = TextAlign.Right,
        fontSize = 13.sp
    )
}

@Composable
fun ValueText(
    modifier: Modifier = Modifier,
    valueText: String
) {
    Text(
        modifier = modifier.padding(5.dp),
        text = valueText,
        textAlign = TextAlign.Right,
        fontSize = 16.sp
    )
}

@Composable
@Preview
private fun PreviewStockCardA() {
    StockCardA(
        code = "123",
        name = "Name",
        openingPrice = "25.0",
        closingPrice = "32.1",
        highestPrice = "33.0",
        lowestPrice = "25.0",
        change = "20",
        monthlyAveragePrice = "20",
        transaction = "20",
        tradeVolume = "200",
        tradeValue = "200",
        {}
    )
}